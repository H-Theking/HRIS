/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CustomComponent;

/**
 *
 * @author Harvey
 */
import java.io.IOException;
import javax.faces.component.*;
import javax.faces.context.FacesContext;

/*
 * @author Brendan Healey (Oversteer)
 */

@FacesComponent("uk.co.myco.component.UIDisablePanel")
public class UIDisablePanel extends UIComponentBase {

    private enum PropertyKeys {
        disabled;
    }

    public UIDisablePanel() {
        setRendererType(null);
    }

    @Override
    public void encodeBegin(FacesContext context) throws IOException {

        boolean toDisable = isDisabled();
        processDisablePanel(this, toDisable);
        //super.encodeBegin(context);
    }

    public void processDisablePanel(UIComponent root, boolean toDisable) {

        /*
         * The key point here is that a child component of <x:disablePanel> may
         * already be disabled, in which case we don't want to enable it if the
         * <x:disablePanel disabled= attribute is set to true.
         */

        for (UIComponent c : root.getChildren()) {
            if (c instanceof UIInput || c instanceof UICommand) {
                if(toDisable) { // <x:disablePanel disabled="true">
                    Boolean curState = (Boolean) c.getAttributes().get("disabled");
                    if(curState == null || curState == false) {
                        c.getAttributes().put("UIPanelDisableFlag", true);
                        c.getAttributes().put("disabled", true);
                    }
                }
                else { // <x:disablePanel disabled="false">
                    if(c.getAttributes().get("UIPanelDisableFlag") != null) {
                        c.getAttributes().remove("UIPanelDisableFlag");
                        c.getAttributes().put("disabled", false);
                    }
                }
            }

            if (c.getChildCount() > 0) {
                processDisablePanel(c, toDisable);
            }
        }

    }

    @Override
    public String getFamily() {
        // Got to override it but it doesn't get called.
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean isDisabled() {
        return (boolean) getStateHelper().eval(PropertyKeys.disabled, false);
    }

    public void setDisabled(boolean disabled) {
        getStateHelper().put(PropertyKeys.disabled, disabled);
    }
}