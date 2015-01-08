/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 *
 * @author Harvey
 */
@Named(value = "interfaceController")
@SessionScoped
public class InterfaceController implements Serializable {

    protected String addEmpCheckValue = "false";

    /**
     * Creates a new instance of InterfaceController
     */
    public InterfaceController() {
    }

    /**
     * *****************************************************
     *
     * AddEmployeePage
     *
     * @return
     */
    public String getAddEmpCheckValue() {
        return addEmpCheckValue;
    }

    public void setAddEmpCheckValue(String addEmpCheckValue) {
        this.addEmpCheckValue = addEmpCheckValue;
        System.out.println(this.addEmpCheckValue);
    }

    /**
     * *****************************************************
     * Employment status page
     */
    protected String rendered = "true";
    protected String notRendered = "false";

    public void toggle() {
        this.notRendered = this.notRendered.equals("false") ? "true" : "false";
        this.rendered = this.rendered.equals("false") ? "true" : "false";
        System.out.println("rendered: " + rendered);
        System.out.println("notrendered " + notRendered);
    }

    public String getRendered() {
        return rendered;
    }

    public void setRendered(String rendered) {
        this.rendered = rendered;
    }

    public String getNotRendered() {
        return notRendered;
    }

    public void setNotRendered(String notRendered) {
        this.notRendered = notRendered;
    }

    /**
     * **
     * Enables and disables the input components of the organisation page. It is
     * executed on every "modify" or "Save" button click
     */
    protected Boolean disabled = true;
    protected String editButtonRender = "true";
    protected String saveButtonRender = "false";

    public void disable() {
        this.saveButtonRender = this.disabled.toString();
        this.disabled = !this.disabled;
        this.editButtonRender = this.disabled.toString();
        System.out.println("values changed");
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    public String getEditButtonRender() {
        return editButtonRender;
    }

    public void setEditButtonRender(String editButtonRender) {
        this.editButtonRender = editButtonRender;
    }

    public String getSaveButtonRender() {
        return saveButtonRender;
    }

    public void setSaveButtonRender(String saveButtonRender) {
        this.saveButtonRender = saveButtonRender;
    }

}
