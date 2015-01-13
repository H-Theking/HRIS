/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import Entities.Skill;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Harvey
 */
@Stateless
public class SkillFacade extends AbstractFacade<Skill> {
    @PersistenceContext(unitName = "SampleAppPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SkillFacade() {
        super(Skill.class);
    }
    
    public void addSkill(String skill, String description) {
        em.persist(new Skill(skill, description));
    }
    
    public void editskill(String oName, String nName, String description) {
        em.createQuery("UPDATE Skill s SET s.name = '" + nName
                + "', s.description = '" + description +
                "' WHERE s.name = '" + oName + "'")
                .executeUpdate();
    }
}
