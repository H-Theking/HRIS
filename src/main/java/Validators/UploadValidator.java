/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import org.apache.commons.io.FilenameUtils;
import org.primefaces.model.DefaultUploadedFile;

/**
 *
 * @author Harvey
 */
@FacesValidator(value = "fileValidator")
public class UploadValidator implements Validator {

    private static final String[] ALLowed_Extensions = {"jpg", "jpeg", "gif",
        "png"};
    private static final long MAX_FILE_SIZE = 4194304L; // 4MB.

    @Override
    public void validate(FacesContext fc, UIComponent uic, Object o) throws ValidatorException {
        DefaultUploadedFile file = (DefaultUploadedFile) o;
        if (file == null) {
            return;
        }
        if (file.getSize() > MAX_FILE_SIZE) {
            throw new ValidatorException(new FacesMessage(
                    FacesMessage.SEVERITY_ERROR,
                    "La taille du fichier est superieur a 4MB", null));
        }
        Boolean AllowedExtension = isExtensionAllowed(FilenameUtils
                .getExtension(file.getFileName()));
        /**
         * ------ [ If extesion is not Allowed ] -----
         */
        if (!AllowedExtension) {
            throw new ValidatorException(new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, "Image Extension not Allowed",
                    null));
        }
    }

    private static Boolean isExtensionAllowed(String ImageExt) {
        for (int i = 0; i < ALLowed_Extensions.length; i++) {
            if (ImageExt.toLowerCase().equals(ALLowed_Extensions[i])) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }
}
