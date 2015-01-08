/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import javax.inject.Named;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Harvey
 */
@Named(value = "interfaceBeans")
@SessionScoped
public class InterfaceBeans {
    
    
    private Boolean hdf;
    /**
     * Creates a new instance of InterfaceBeans
     */
    public InterfaceBeans() {
    }
    
}
