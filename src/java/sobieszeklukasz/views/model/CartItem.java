/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sobieszeklukasz.views.model;

import sobieszeklukasz.entity.Book;

/**
 *
 * @author XXX
 */
public class CartItem {

    private Book book;
    private int quantity;

    public CartItem(Book book) {
        this.book = book;
        this.quantity = 1;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getQuantity() {
        return quantity;
    }
    
    public int incrementQuantity() {
        return quantity++;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
