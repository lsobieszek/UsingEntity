/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sobieszeklukasz.views;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import lsobieszek.exceptions.AccessDeniedException;
import sobieszeklukasz.ejb.BookManager;
import sobieszeklukasz.ejb.CategoryManager;
import sobieszeklukasz.entity.Book;
import sobieszeklukasz.entity.Category;

/**
 *
 * @author XXX
 */
@ManagedBean
@RequestScoped
public class NewBookPageBean implements Serializable {

    @EJB
    private BookManager bookManager;

    @EJB
    private CategoryManager categoryManager;

    @ManagedProperty(value = "#{param.id}")
    private Integer id;

    private Book book;
    private List<Integer> categories;

    @PostConstruct
    protected void init() {

        if (id != null) {
            book = bookManager.getBook(id);
            categories = getCategoriesIds(book.getCategories());
        } else {
            book = new Book();
            categories = new ArrayList<>();
        }
    }

    public String save() {
        
        if (!FacesContext.getCurrentInstance().getExternalContext().isUserInRole("administrators")) {
            throw new AccessDeniedException("Brak dostepu");
        }
        
        if (id != null) {
            bookManager.update(book, categories);
        } else {
            bookManager.save(book, categories);
        }

        final FacesContext ctx = FacesContext.getCurrentInstance();
        ctx.addMessage(null, new FacesMessage("Książka została zapisana"));

        return "/listBooks.xhtml?faces-redirect=true";
    }

    public Book getBook() {
        return book;
    }

    public List<Integer> getCategories() {
        return categories;
    }

    public void setCategories(List<Integer> categories) {
        this.categories = categories;
    }

    public List<Category> getAllCategories() {
        return categoryManager.getAllCategories();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    private List<Integer> getCategoriesIds(List<Category> categories) {
        List<Integer> ids = new ArrayList<>();

        for (Category category : categories) {
            ids.add(category.getId());
        }

        return ids;
    }

}
