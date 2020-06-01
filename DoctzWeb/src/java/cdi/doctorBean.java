/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cdi;

import beans.doctzBeanLocal;
import client.myadmin;
import client.myclient;
import entity.DoctorTb;
import entity.SpecializationTb;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
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
@Named(value = "doctorBean")
@RequestScoped
public class doctorBean {

    @EJB doctzBeanLocal ejb;
    myclient c;
    Response res;
    
    Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
    
    GenericType<Collection<DoctorTb>> gdoc;
   
    
    private int hosId;
    private int id;
    private String name;
    private int sid;
    private String exp;
    private String gender;
    private int uid;
    private String certi;
    private String edu;
    private String profile;
    private int isActive;
    private String date;
    
    String spec,hos;
   
   
    private Collection<DoctorTb> alldocs;
    private Collection<DoctorTb> searchDocs,serachGenderDocs;
    private String ajaxvalue="";
    
   
    
    private DoctorTb doctor;

    public String getAjaxvalue() {
        return ajaxvalue;
    }

    public void setAjaxvalue(String ajaxvalue) {
        this.ajaxvalue = ajaxvalue;
    }
    
    

    public doctorBean() {
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
         
        
        gdoc=new GenericType<Collection<DoctorTb>>(){};
        alldocs=new ArrayList<DoctorTb>();
        searchDocs=new ArrayList<DoctorTb>();
        
       
       
    }
    public void ajax(String str)
    {
       ajaxvalue="hello "+str;
    }



     
      public int getHosId() {

        hosId=Integer.parseInt(params.get("hos"));
        return hosId;
    }

    public void setHosId(int hosId) {
        this.hosId = hosId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    
    
    public Collection<DoctorTb> getSearchDocs() 
    {
       hos=params.get("hos");
       if(hos != null)
       {
           res=c.getDoctorOfHospital(Response.class, hos);
           searchDocs=res.readEntity(gdoc);
       }
       
       HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
       HttpSession session = request.getSession(true);
       
       if(null != params.get("spec"))
       {
           session.setAttribute("spec", params.get("spec"));
       }
       spec=params.get("spec");
      
        
       //System.out.println("cdi.doctorBean.getSearchDocs()-------------"+spec);
       if(spec != null)
       {
            
            if(spec.equals("all"))
            {
                res=c.getAllDoctor(Response.class);
                searchDocs=res.readEntity(gdoc);

            }
            else
            {
                //System.out.println(spec);
                searchDocs=ejb.getDoctorBySpecializationName(spec);
            }
       }
      
        return searchDocs;
    }

    public void setSearchDocs(Collection<DoctorTb> searchDocs) {
        this.searchDocs = searchDocs;
    }

    public Collection<DoctorTb> getSerachGenderDocs() {
        
        
        return serachGenderDocs;
    }

    public void setSerachGenderDocs(Collection<DoctorTb> serachGenderDocs) {
        this.serachGenderDocs = serachGenderDocs;
    }

    
    
    public void getDoctorByGender(String str)
    {
        
        this.serachGenderDocs=ejb.getDoctorByGender(str);
        this.setSearchDocs(serachGenderDocs);
       
//        for(DoctorTb d:this.searchDocs)
//        {
//            System.out.println(d.getDoctorName()+" "+d.getGender());
//        }
       
    }
    
    public void getDoctorByExperience()
    { 
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
      
        HttpSession session = request.getSession(true);
        spec=session.getAttribute("spec").toString();
       // System.out.println("spec----------"+spec);
        if(this.ajaxvalue.equals("Experience"))
        {
            if(spec != null)
            {
           
                if(spec.equals("all"))
                {
                     res=c.getDoctorByExperience(Response.class);
                     this.setSearchDocs(res.readEntity(gdoc));
                }
                else
                {
                   this.setSearchDocs(ejb.getDoctorByExperienceAndSpec(spec));  
                }
            }
          
           
        }
        
        else if(this.ajaxvalue.equals("Availability"))
        { 
           if(spec != null)
            {
           
                if(spec.equals("all"))
                {
                    this.setSearchDocs(ejb.getDoctorByAvailabilityOfBooking()); 
                }
                else
                {
                   this.setSearchDocs(ejb.getDoctorByAvailabilityOfBookingAndSpec(spec));  
                }
            }
           
        }
        else
        {
            res=c.getAllDoctor(Response.class);
            this.setSearchDocs(res.readEntity(gdoc));
        }
       
       
    }
    
    public void getDoctorBySpecializaton(int sid)
    {
        res=c.getDoctorBySpecialization(Response.class, String.valueOf(sid));
        this.setSearchDocs(res.readEntity(gdoc));
       
    }
    
    public void getDoctorByAvailability()
    {
       java.sql.Date d = java.sql.Date.valueOf(this.date);
       // System.out.println("Date--->"+this.getDate());
        res=c.getDoctorByAvailability(Response.class, d.toString());
        this.setSearchDocs(res.readEntity(gdoc));
//         for(DoctorTb d1:this.searchDocs)
//        {
//            System.out.println(d1.getDoctorName()+" "+d1.getGender());
//        }
              
    }

    public DoctorTb getDoctor()
    {
        
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession session = request.getSession(true);
       // int did=4;
       if(null != params.get("did"))
       {
           session.setAttribute("did", params.get("did"));
       }
       int did=Integer.parseInt(session.getAttribute("did").toString());
       // int did=Integer.parseInt(params.get("did"));
        
        session.setAttribute("did",did);
        doctor=ejb.getDoctorById(did);
        
        return doctor;
    }

   
    
    public void setDoctor(DoctorTb doctor) {
        
        this.doctor = doctor;
    }
    
    
    
    
     public Collection<DoctorTb> getAlldocs() {
    
        
        res=c.getAllDoctor(Response.class);
        alldocs=res.readEntity(gdoc);
         
        return alldocs;
    
    }

    public void setAlldocs(Collection<DoctorTb> alldocs) {
        this.alldocs = alldocs;
    }
    
 
    public Response getRes() {
        return res;
    }

    public void setRes(Response res) {
        this.res = res;
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

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getCerti() {
        return certi;
    }

    public void setCerti(String certi) {
        this.certi = certi;
    }

    public String getEdu() {
        return edu;
    }

    public void setEdu(String edu) {
        this.edu = edu;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

   
}
