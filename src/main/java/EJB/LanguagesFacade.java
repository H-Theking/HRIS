/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import Entities.Languages;
import java.util.List;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Harvey
 */
@Stateless
public class LanguagesFacade extends AbstractFacade<Languages> {

    @PersistenceContext(unitName = "SampleAppPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LanguagesFacade() {
        super(Languages.class);
    }

    public List<Languages> getLanguagess() {
        List<Languages> resultList = (List<Languages>) em.createNamedQuery("findAllLanguages").getResultList();
        return resultList;
    }

    public void addLanguage(String name)
            throws EJBException {
        Languages language = new Languages(name);
        em.persist(language);
    }

    public void editLanguage(String oldName, String newName)
            throws EJBException {
        em.createQuery("UPDATE Languages j SET j.name = '" + newName
                + "' WHERE j.name = '" + oldName + "'").executeUpdate();
    }

    public void deleteLanguage(String languagesTitle) {
        Languages find = em.find(Languages.class, languagesTitle);
        em.remove(find);
    }

    public Languages findLanguage(String name) {
        return em.find(Languages.class, name);
    }

}
