/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sobieszeklukasz.views;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author XXX
 */
@ManagedBean
public class TemplatePageBean {

    public String logout() {

        HttpSession session = (HttpSession) FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .getSession(false);

        if(session != null) {
            session.invalidate();
        }
        
        return "/index.html?faces-redirect=true";
    }
}
