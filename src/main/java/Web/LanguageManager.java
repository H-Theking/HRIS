/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Web;

import EJB.LanguagesFacade;
import Entities.Languages;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.component.UIParameter;
import javax.faces.event.AjaxBehaviorEvent;

/**
 *
 * @author Harvey
 */
@Named(value = "languageManager")
@SessionScoped
public class LanguageManager implements Serializable {

    @EJB
    LanguagesFacade languagesFacade;
    private String name;
    private String oldName;
    private Languages[] selectedLanguages;
    private List<Languages> languages;

    /**
     * Creates a new instance of Jobmanager
     */
    public LanguageManager() {
    }

    public void clearFields() {
        oldName = name = null;
    }

    public void addLanguage() {

        if (oldName == null) {
            System.out.println("Adding Language");
            languagesFacade.addLanguage(name);
        } else {
            System.out.println("Editing language");
            languagesFacade.editLanguage(oldName, name);
        }
    }

    public void deleteLanguages() {
        for (Languages selectedLanguage : selectedLanguages) {
            languagesFacade.remove(selectedLanguage);
        }
    }

    public List<Languages> getLanguages() {
        languages = languagesFacade.getLanguagess();
        return languages;
    }

    public void findLanguage(AjaxBehaviorEvent event) throws EJBException {
        UIParameter param = (UIParameter) event.getComponent().findComponent("langName");
        String language = param.getValue().toString();
        Languages find = languagesFacade.find(language);
        oldName = name = find.getName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOldName() {
        return oldName;
    }

    public void setOldName(String oldName) {
        this.oldName = oldName;
    }

    public Languages[] getSelectedLanguages() {
        return selectedLanguages;
    }

    public void setSelectedLanguages(Languages[] selectedLanguages) {
        this.selectedLanguages = selectedLanguages;
    }
}
