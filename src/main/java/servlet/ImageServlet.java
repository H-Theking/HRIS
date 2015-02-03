/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import EJB.EmployeeImagesFacade;
import Entities.EmployeeImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Harvey
 */
public class ImageServlet extends HttpServlet {

    @EJB
    EmployeeImagesFacade eif;
    private static final int DEFAULT_BUFFER_SIZE = 4096; // 10KB.;

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String imageId = request.getParameter("ImageId");
        EmployeeImage image = eif.find(imageId);
        if (image == null) {
            return;
        }
        // Init servlet response.
        response.reset();
        response.setBufferSize(DEFAULT_BUFFER_SIZE);
        response.setContentType(image.getImageType());
        response.setHeader("Content-Length", String.valueOf(image.getImage().length));
        response.setHeader("Content-Disposition",
                "attachment; filename=\"" + image.getImageName() + "\"");
        // Prepare streams.  
        BufferedInputStream input = null;
        BufferedOutputStream output = null;
        try {
            // Open streams. 
            input = new BufferedInputStream(new ByteArrayInputStream(
                    image.getImage()), DEFAULT_BUFFER_SIZE);
            output = new BufferedOutputStream(response.getOutputStream(),
                    DEFAULT_BUFFER_SIZE);
            // Write file contents to response.  
            byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
            int length;
            while ((length = input.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }
        } finally {
            // Gently close streams.  
            close(output);
            close(input);
        }
    }

    private static void close(Closeable resource) {
        if (resource != null) {
            try {
                resource.close();
            } catch (IOException e) {
                // Do your thing with the exception. Print it, log it or mail it 
                Logger.getGlobal().log(Level.WARNING, e.getMessage());
            }
        }
    }
}
