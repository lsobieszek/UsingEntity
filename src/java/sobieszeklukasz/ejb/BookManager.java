/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sobieszeklukasz.ejb;

import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import sobieszeklukasz.entity.Book;
import sobieszeklukasz.entity.Category;

@Stateless
@LocalBean
public class BookManager {

    @PersistenceContext
    private EntityManager em;

    public List<Book> getAllBooks() {
        return em.createNamedQuery("Book.findAll").getResultList();
    }

    public Book update(Book book, List<Integer> categorieIds) {
        List<Category> categories = getCategories(categorieIds);
        book.setCategories(categories);
        
        return em.merge(book);
    }

    public void save(Book book, List<Integer> categorieIds) {
        List<Category> categories = getCategories(categorieIds);
        book.setCategories(categories);

        save(book);
        
    }

    public void save(Book book) {
        em.persist(book);
    }

    private List<Category> getCategories(List<Integer> categorieIds) {
        if(categorieIds == null || categorieIds.isEmpty()) {
            return Collections.emptyList();
        }
        
        return em.createQuery("SELECT c FROM Category c WHERE c.id IN :ids")
                .setParameter("ids", categorieIds)
                .getResultList();
    }

    public Book getBook(Integer id) {
        return em.find(Book.class, id);
        
    }

    public void dalete(Book book){
        
        if(!em.contains(book)) {
//            book = em.getReference(Book.class, book.getId());
            book = em.find(Book.class, book.getId());
        }
        
        em.remove(book);
    }
}
