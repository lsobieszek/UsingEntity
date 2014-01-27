    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sobieszeklukasz.views;

import java.io.Serializable;
import java.util.List;
import sobieszeklukasz.entity.Book;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import sobieszeklukasz.ejb.BookManager;

/**
 *
 * @author XXX
 */
@ManagedBean
@RequestScoped
public class ListBooksPageBean implements Serializable {

    @EJB
    private BookManager bookManager;

    public List<Book> getBooks() {
        return bookManager.getAllBooks();
    }
    
    public void delete(Book book){
        bookManager.dalete(book);
    }
}
