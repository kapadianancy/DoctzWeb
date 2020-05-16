/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cdi;

import client.*;
import entity.AreaTb;
import entity.StateTb;
import java.util.ArrayList;
import java.util.Collection;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

/**
 *
 * @author ADMIN
 */
@Named(value = "stateBean")
@RequestScoped
public class stateBean {

    myadmin a;
    Response res;
    GenericType<Collection<StateTb>> gstate;
    Collection<StateTb> allstates;
    
    private int id;
    private String stateName;
    
    public stateBean() {
         HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        String token="";

          HttpSession session = request.getSession(false);
        if(null != session.getAttribute("token"))
        {
          token = request.getSession().getAttribute("token").toString();
          System.out.println("Token="+token);
        
//            String token1 = request.getHeader("Authorization").substring("Bearer ".length());
//            System.out.println("Token="+token1);
            a = new myadmin(token);
          
        }
        else
        {
          a=new myadmin();
        }
        //a=new myadmin();
        gstate=new GenericType<Collection<StateTb>>(){};
        allstates=new ArrayList<StateTb>();
    }

    public Collection<StateTb> getAllstates() {
        res=a.getAllState(Response.class);
        allstates=res.readEntity(gstate);
        return allstates;
    }

    public void setAllstates(Collection<StateTb> allstates) {
        this.allstates = allstates;
    }
    

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }
    
}
