/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sobieszeklukasz.views;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import sobieszeklukasz.ejb.CategoryManager;
import sobieszeklukasz.entity.Category;

/**
 *
 * @author XXX
 */
@ManagedBean
@RequestScoped
public class NewCategoryPageBean {
    
    @EJB
    private CategoryManager categoryManager;

    private Category category;
   
    @PostConstruct
    public void init(){
        category = new Category();
    }
    
    public Category getCategory(){
        return category;
    }
    
    public String save(){
        categoryManager.saveIfUnique(category);
        return "/newCategory.xhtml?faces-redirect=true";
    } 
}
