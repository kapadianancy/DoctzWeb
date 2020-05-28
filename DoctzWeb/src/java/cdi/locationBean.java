/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cdi;

import beans.doctzBeanLocal;
import entity.HospitalTb;
import java.util.ArrayList;
import java.util.Collection;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Admin
 */
@Named(value = "locationBean")
@RequestScoped
public class locationBean {

    @EJB doctzBeanLocal ejb;
   private String lati;
   private String longi;
   private Collection<HospitalTb> hospitals;
    public locationBean() {
        hospitals=new ArrayList<HospitalTb>();
    }

    public String getLati() {
        return lati;
    }

    public void setLati(String lati) {
        this.lati = lati;
    }

    public String getLongi() {
        return longi;
    }

    public void setLongi(String longi) {
        this.longi = longi;
    }

    public Collection<HospitalTb> getHospitals() {
        return hospitals;
    }

    public void setHospitals(Collection<HospitalTb> hospitals) {
        this.hospitals = hospitals;
    }
    
    
    public void display(String s1,String s2,String s3,String s4)
    {
        System.out.println("in method----------------");
        System.out.println("lati----------"+s1+"\nlongi-------"+s2);
        System.out.println("newlati----------"+s3+"\nnewlongi-------"+s4);
        this.setHospitals(ejb.nearMeHospital(Double.parseDouble(s1), Double.parseDouble(s2), Double.parseDouble(s3), Double.parseDouble(s4)));
        System.out.println(this.getHospitals());
    }
    
    
    
}
