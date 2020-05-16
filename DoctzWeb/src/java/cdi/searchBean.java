/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cdi;

import client.myclient;
import entity.DoctorTb;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Admin
 */
@Named(value = "searchBean")
@RequestScoped
public class searchBean {

    private String area="";
    private String spec="";
    

    public searchBean() {
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }
    
    
    public String search()
    {
        
        if(! this.getArea().equals("") && ! this.getSpec().equals(""))
        {
            return "hospitals.xhtml?faces-redirect=true&area="+this.getArea()+"&spec="+this.getSpec();
        }
        if(! this.getArea().equals(""))
        {
            return "hospitals.xhtml?faces-redirect=true&area="+this.getArea()+"&spec=null";
        }
        if(! this.getSpec().equals(""))
        {
            return "searchDoctors.xhtml?faces-redirect=true&spec="+this.getSpec();
        }
        
            return "index.xhtml";
        
    }
    
   
}