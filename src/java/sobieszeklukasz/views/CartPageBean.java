/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sobieszeklukasz.views;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import sobieszeklukasz.entity.Book;
import sobieszeklukasz.views.model.CartItem;


@ManagedBean
@SessionScoped
public class CartPageBean implements Serializable {

    private Map<Integer, CartItem> items = new HashMap<>();

    public void add(Book book) {
        final CartItem item = items.get(book.getId());

        if (item == null) {
            items.put(book.getId(), new CartItem(book));
        } else {
            item.incrementQuantity();
        }
    }

    public void remove(CartItem item) {
        items.remove(item.getBook().getId());
    }

    public List<CartItem> getItems() {
        return new ArrayList<>(items.values());
    }

    public double getTotal() {
        final Iterator<Map.Entry<Integer, CartItem>> it = items.entrySet().iterator();
        double total = 0;
        
        while(it.hasNext()) {
            final Map.Entry<Integer, CartItem> entry = it.next();
            final CartItem item = entry.getValue();
            
            total += item.getBook().getPrice() * item.getQuantity();
        }
        
        return total;
    }
}
