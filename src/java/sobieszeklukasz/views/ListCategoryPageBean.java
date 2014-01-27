/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sobieszeklukasz.views;

import java.util.List;
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
public class ListCategoryPageBean {

    @EJB
    CategoryManager categoryManager;
    
    public List<Category> getCategories(){
        return categoryManager.getAllCategories();
    }
            
    public void delete(Category category){
       categoryManager.delete(category);
    }
            
}
