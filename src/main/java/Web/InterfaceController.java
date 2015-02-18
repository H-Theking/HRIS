/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Web;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.event.ActionEvent;

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
    protected String space = "false";
    protected String add = "false";
    protected String modify = "false";
    protected String buttons = "true";

    public void reset() {
        space = add = modify = saveButtonRender = "false";
        buttons = editButtonRender = "true";
        disabled = true;
    }

    public void toggleAddModify(ActionEvent event) {
        String source = event.getComponent().getClass().toString();
        if (source.contains("CommandButton")) {
            this.add = "true";
            this.modify = "false";
        } else {
            this.add = "false";
            this.modify = "true";
        }
        buttons = "false";
        space = "true";
    }

    public void unrender() {
        this.add = "false";
        this.modify = "false";
        this.space = "false";
        this.buttons = "true";
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

    public String getAdd() {
        return add;
    }

    public void setAdd(String add) {
        this.add = add;
    }

    public String getModify() {
        return modify;
    }

    public void setModify(String modify) {
        this.modify = modify;
    }

    public String getButtons() {
        return buttons;
    }

    public void setButtons(String buttons) {
        this.buttons = buttons;
    }

    public String getSpace() {
        return space;
    }

    public void setSpace(String space) {
        this.space = space;
    }

}
