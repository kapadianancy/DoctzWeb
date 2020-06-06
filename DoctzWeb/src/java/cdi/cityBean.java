/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cdi;

import client.myadmin;
import entity.*;
import java.util.ArrayList;
import java.util.Collection;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
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
@Named(value = "cityBean")
@RequestScoped
public class cityBean {

    myadmin a;
    Response res;
    GenericType<Collection<CityTb>> gcity;
    Collection<CityTb> allcity;
    
    private int id,stateId;
    private String cityName;
    public cityBean() {
        //a=new myadmin();
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
        
        gcity=new GenericType<Collection<CityTb>>(){};
        allcity=new ArrayList<CityTb>();
    }

    public Collection<CityTb> getAllcity() {
        res=a.getAllCity(Response.class);
        allcity=res.readEntity(gcity);
        return allcity;
    }

    public void setAllcity(Collection<CityTb> allcity) {
        this.allcity = allcity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStateId() {
        return stateId;
    }

    public void setStateId(int stateId) {
        this.stateId = stateId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
    
    public String displayArea(int cid)
    {
        System.err.println("city----------"+cid);
        return "area.xhtml?faces-redirect=true";
    }
    
    public String addCity()
    {
        
        res=a.addCity(Response.class,this.cityName,String.valueOf(this.stateId));
        return "city.xhtml";
    }
     public String deleteCity(int cid)
    {
        res=a.deleteCity(Response.class, String.valueOf(cid));
        return "city.xhtml";
    }
    
}
