/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cdi;

import beans.doctzBeanLocal;
import client.myclient;
import entity.HospitalTb;
import java.util.ArrayList;
import java.util.Collection;
import javax.ejb.EJB;
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
 * @author Admin
 */
@Named(value = "locationBean")
@RequestScoped
public class locationBean {

    Response res;
    myclient c;
    @EJB doctzBeanLocal ejb;
   private String lati;
   private String longi;
   private Collection<HospitalTb> hospitals;
    GenericType<Collection<HospitalTb>> ghos;
    private Collection<HospitalTb> nearHospitals;
    private int status;
   
    public locationBean() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        String token="";

          HttpSession session = request.getSession(false);
        if(null != session.getAttribute("token"))
        {
          token = request.getSession().getAttribute("token").toString();
          System.out.println("Token="+token);
        
            c = new myclient(token);
          
        }
        else
        {
          c=new myclient();
         
        }
       
        hospitals=new ArrayList<HospitalTb>();
        ghos=new GenericType<Collection<HospitalTb>>(){};
        nearHospitals=new ArrayList<HospitalTb>();
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    public Collection<HospitalTb> getNearHospitals() {
        return nearHospitals;
    }

    public void setNearHospitals(Collection<HospitalTb> nearHospitals) {
        this.nearHospitals = nearHospitals;
    }
    
    
    
    public void display(String lat,String lon)
    {
        System.out.println("in method----------------");
        System.out.println("lati----------"+lat+"\nlongi-------"+lon);
        this.setStatus(1);
        
        res=c.getAllHospital(Response.class);
        this.setHospitals(res.readEntity(ghos));
        System.out.println(this.getHospitals());
        for(HospitalTb h:this.getHospitals())
        {
            double dist=this.distance(Double.parseDouble(lat),Double.parseDouble(lon) , h.getLatitude(),h.getLongitude(),"K");
            System.out.println(h.getHospitalName()+"----"+dist);
            if(dist<=4.0) // in range of 4 km
            {
                this.nearHospitals.add(h);
            }
        }
        System.out.println(this.getNearHospitals());
    }
    
     public double distance(double lat1,double lon1,double lat2,double lon2,String unit)
     {
                            // alert(lat1);

                double radlat1 = Math.PI * lat1/180;
                double radlat2 = Math.PI * lat2/180;
                double theta = lon1-lon2;
                double radtheta = Math.PI * theta/180;
                double dist = Math.sin(radlat1) * Math.sin(radlat2) + Math.cos(radlat1) * Math.cos(radlat2) * Math.cos(radtheta);
                if (dist > 1) {
                    dist = 1;
                }
                dist = Math.acos(dist);
                dist = dist * 180/Math.PI;
                dist = dist * 60 * 1.1515;
                if (unit=="K") { dist = dist * 1.609344; }
                if (unit=="N") { dist = dist * 0.8684; }
                //alert(dist);
                 return dist;
     }
    
    
}
