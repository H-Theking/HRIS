/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Web;

import EJB.EmployeeImagesFacade;
import Entities.EmployeeImage;
import static Web.UserManager.logger;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.logging.Level;
import javax.ejb.EJB;
import javax.faces.component.UIParameter;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Harvey
 */
@Named(value = "imageManager")
@SessionScoped
public class ImageManager implements Serializable {

    @EJB
    private EmployeeImagesFacade employeeImagesFacade;
    private UploadedFile uploadedFile;
    private String id;
    private EmployeeImage ei;
    private DefaultStreamedContent content;

    /**
     * Creates a new instance of ImageManager
     */
    public ImageManager() {

    }

    public void addImage() {
        try {
            String fileName = FilenameUtils.getName((uploadedFile.getFileName()));
            if (null == fileName) {
                return;
            }
            logger.log(Level.INFO, "Uploaded file extension",
                    FilenameUtils.getExtension(fileName));
            logger.log(Level.INFO, "Uploaded filename", uploadedFile.getFileName());
            String contentType = FacesContext
                    .getCurrentInstance()
                    .getExternalContext()
                    .getMimeType(fileName);
            byte[] bytes = IOUtils.toByteArray(uploadedFile.getInputstream());//IOUtils.toByteArray(uploadedFile.getInputstream());
            employeeImagesFacade.setImage(id, bytes, fileName, contentType);
            logger.log(Level.INFO, "Uploaded image file {0} of type {0}",
                    new Object[]{fileName, contentType});
            //set image
            ByteArrayInputStream img = new ByteArrayInputStream(ei.getImage());
            content = new DefaultStreamedContent(img, uploadedFile.getContentType());
        } catch (Exception e) {
            // Always log stacktraces (with a real logger).
            logger.log(Level.SEVERE, "Uploading file {0} failed", uploadedFile.getFileName());
            logger.log(Level.WARNING, e.getMessage());
        }
    }

    public void getEmployee(AjaxBehaviorEvent event) {
        UIParameter parameter = (UIParameter) event.getComponent().findComponent("worker");
        id = parameter.getValue().toString();
        ei = employeeImagesFacade.find(id);
    }

    public UploadedFile getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(UploadedFile uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public EmployeeImage getEi() {
        return ei;
    }

    public void setEi(EmployeeImage ei) {
        this.ei = ei;
    }

    public DefaultStreamedContent getContent() {
        return content;
    }

    public void setContent(DefaultStreamedContent content) {
        this.content = content;
    }

}
