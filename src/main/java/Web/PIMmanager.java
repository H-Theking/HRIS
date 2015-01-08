/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Web;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 *
 * @author Harvey
 */
@Named(value = "pIMmanager")
@SessionScoped
public class PIMmanager implements Serializable {

    /**
     * Creates a new instance of PIMmanager
     */
    public PIMmanager() {
    }
    
    public void editPersonalDetails(){
        
    }
    
}
