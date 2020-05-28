/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cdi;

import beans.doctzBeanLocal;
import client.myadmin;
import client.myclient;
import entity.HospitalTb;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
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
 * @author Admin
 */
@Named(value = "hospitalBean")
@RequestScoped
public class hospitalBean {
    
    @EJB doctzBeanLocal ejb;
    Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
    
    myclient c;
    Response res;
    
    GenericType<Collection<HospitalTb>> ghos;
    Collection<HospitalTb> allhos;
    
    
        
    private int id;
    private String name;
    private String address;
    private int aid;
    private int cid;
    private int pin;
    private double lati;
    private double longi;
    private Time otime;
    private Time ctime;
    private String logo;
    private String doc;
    private int uid;
    private int isActive;
    String area="";
    String spec="";
       
   
    
    public hospitalBean() {
        
        //c=new myclient();
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
           // a = new myadmin(token);
            c = new myclient(token);
          
        }
        else
        {
          c=new myclient();
          //a=new myadmin();
        }
         
        
        ghos=new GenericType<Collection<HospitalTb>>(){};
        allhos=new ArrayList<HospitalTb>(); 
        
    }


    public Collection<HospitalTb> getAllhos() {
        if(! params.isEmpty())
        {
            if(! params.get("area").equals(""))
            {
                if(! params.get("spec").equals("null"))
                {
                    spec=params.get("spec");
                }
                else
                {
                    spec="";
                }
                area=params.get("area");

            }
           
        }
        else
        {  
            area="";
            spec="";
        }
        
       System.out.println("area : "+area+"spec : "+spec);
       
        if(! area.equals("") && ! spec.equals(""))
        {
            allhos=ejb.getHospitalByAreaAndSpecializationName(area, spec);
        }
        else if(! area.equals("") && spec.equals(""))
        {
            res=c.getHospitalByArea(Response.class, area);
            allhos=res.readEntity(ghos);
          //  System.out.println(allhos);
        }
        else
        {
            res=c.getAllHospital(Response.class);
            allhos=res.readEntity(ghos);
        }
//        
//        res=c.getAllHospital(Response.class);
//        allhos=res.readEntity(ghos);
        return allhos;
    }

    public void setAllhos(Collection<HospitalTb> allhos) {
        this.allhos = allhos;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public double getLati() {
        return lati;
    }

    public void setLati(double lati) {
        this.lati = lati;
    }

    public double getLongi() {
        return longi;
    }

    public void setLongi(double longi) {
        this.longi = longi;
    }

    public Time getOtime() {
        return otime;
    }

    public void setOtime(Time otime) {
        this.otime = otime;
    }

    public Time getCtime() {
        return ctime;
    }

    public void setCtime(Time ctime) {
        this.ctime = ctime;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getDoc() {
        return doc;
    }

    public void setDoc(String doc) {
        this.doc = doc;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }
    
    
    
}
