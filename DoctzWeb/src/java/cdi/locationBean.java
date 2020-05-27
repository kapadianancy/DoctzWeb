/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cdi;

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

   private String lati;
   private String longi;
    public locationBean() {
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
    public void display(String s1,String s2,String s3,String s4)
    {
        System.out.println("in method----------------");
        System.out.println("lati----------"+s1+"\nlongi-------"+s2);
        System.out.println("newlati----------"+s3+"\nnewlongi-------"+s4);
        
    }
    
}
