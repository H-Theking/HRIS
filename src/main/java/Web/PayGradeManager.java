/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Web;

import EJB.PayGradeFacade;
import Entities.PayGrade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.component.UIParameter;
import javax.faces.event.AjaxBehaviorEvent;

/**
 *
 * @author Harvey
 */
@Named(value = "payGradeManager")
@SessionScoped
public class PayGradeManager implements Serializable {

    @EJB
    private PayGradeFacade payGradeFacade;

    private String name;
    private float min;
    private float max;
    private String currency;
    private String oldName;
    private PayGrade[] selectedPayGrades;

    /**
     * Creates a new instance of PayGradeManager
     */
    public PayGradeManager() {
    }

    public void clearFields() {
        name = currency = oldName = null;
        min = max = 0;
    }

    public void addPayGrade() {
        if (oldName == null) {
            payGradeFacade.addPayGrade(name, min, max, currency);
            Logger.getGlobal().log(Level.INFO, "Created pay grade level {0}",
                    new Object[]{name});
        } else {
            payGradeFacade.editPayGrade(oldName, name, min, max, currency);
            Logger.getGlobal().log(Level.INFO, "Edited paygrade level {0}",
                    new Object[]{oldName});
        }
        clearFields();
    }

    public List<PayGrade> getPayGrades() {
        return payGradeFacade.findAll();
    }

    public void findPayGrade(AjaxBehaviorEvent event) {
        UIParameter parameter = (UIParameter) event.getComponent().findComponent("paygrade");
        PayGrade find = payGradeFacade.find(parameter.getValue().toString());
        oldName = name = find.getName();
        min = find.getMinimum();
        max = find.getMaximum();
        currency = find.getCurrency();
    }

    public void removePayGrade() {
        payGradeFacade.deletePayGrade(name);
    }

    /**
     * Get the value of oldName
     *
     * @return the value of oldName
     */
    public String getOldName() {
        return oldName;
    }

    /**
     * Set the value of oldName
     *
     * @param oldName new value of oldName
     */
    public void setOldName(String oldName) {
        this.oldName = oldName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getMin() {
        return min;
    }

    public void setMin(float min) {
        this.min = min;
    }

    public float getMax() {
        return max;
    }

    public void setMax(float max) {
        this.max = max;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    /**
     * Get the value of selectedPayGrades
     *
     * @return the value of selectedPayGrades
     */
    public PayGrade[] getSelectedPayGrades() {
        return selectedPayGrades;
    }

    /**
     * Set the value of selectedPayGrades
     *
     * @param selectedPayGrades new value of selectedPayGrades
     */
    public void setSelectedPayGrades(PayGrade[] selectedPayGrades) {
        this.selectedPayGrades = selectedPayGrades;
    }

}
