/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sobieszeklukasz.ejb;

import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import sobieszeklukasz.entity.Book;
import sobieszeklukasz.entity.Category;

/**
 *
 * @author XXX
 */
@Stateless
@LocalBean
public class CategoryManager {

    @PersistenceContext
    private EntityManager em;

    public List<Category> getAllCategories() {
        return em.createNamedQuery("Category.findAll").getResultList();
    }

    public void saveIfUnique(Category category) {

        Number count = (Number) em.createNamedQuery("Category.countByCategoryName")
                .setParameter("name", category.getName())
                .getSingleResult();

        if (count.intValue() == 0) {
            save(category);
        }
    }

    public void save(Category category) {
        em.persist(category);
    }
    
    public Category getI(Integer id){
        return em.find(Category.class, id);
    }
    
    public void delete(Category category){
        
         if(!em.contains(category)) {
            category = em.getReference(Category.class, category.getId());
        }
         em.remove(category);
    }
    
    
}
