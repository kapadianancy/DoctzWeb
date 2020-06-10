/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cdi;

import beans.doctzBeanLocal;
import client.myclient;
import client.mydoctor;
import entity.DoctorTb;
import entity.HospitalTb;
import entity.PatientTb;
import entity.ReviewTb;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
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
@Named(value = "reviewBean")
@RequestScoped
public class reviewBean {
    
    @EJB doctzBeanLocal ejb;
    Response res;
    myclient c;
    mydoctor d;
    String email;
    
    
    
    private int rid,pid,hid,did;
    private String review;
    
    Collection<ReviewTb> all;
    GenericType<Collection<ReviewTb>> grev;
    Collection<ReviewTb> docReview,hosReview,hreview;
    private String username,hname;
//    private PatientTb p=new PatientTb(); 
     HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
     HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
       
    
    Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
    
    public reviewBean() {
        all=new ArrayList<ReviewTb>();
        grev=new GenericType<Collection<ReviewTb>>(){};
        String token="";

        HttpSession session = request.getSession(false);
        
        if(null != session.getAttribute("token"))
        {
          token = request.getSession().getAttribute("token").toString();
        //  System.out.println("Token="+token);
        
           // a = new myadmin(token);
            c = new myclient(token);
            d=new mydoctor(token);
          
        }
        else
        {
          c=new myclient();
          d=new mydoctor();
          //a=new myadmin();
        }
        if(null != session.getAttribute("username"))
         {
             email=session.getAttribute("username").toString();
         }
         else
         {
             email="";
         }
       docReview=new ArrayList<ReviewTb>();
       hosReview=new ArrayList<ReviewTb>();
       hreview=new ArrayList<ReviewTb>();
        
    }

    public String getEmail() {
        
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHname() {
        return hname;
    }

    public void setHname(String hname) {
        this.hname = hname;
    }


    public Collection<ReviewTb> getDocReview() {
        HttpSession session = request.getSession(false);
        username=session.getAttribute("username").toString();
        DoctorTb d=new DoctorTb();
        d=ejb.getDoctorByEmail(this.username);
        docReview=ejb.getReviewByDoctorId(d.getDoctorId());
        return docReview;
    }

    public void setDocReview(Collection<ReviewTb> docReview) {
        this.docReview = docReview;
    }

    public Collection<ReviewTb> getHosReview() {
        HttpSession session = request.getSession(false);
        username=session.getAttribute("username").toString();
        HospitalTb hos=new HospitalTb();
        hos=ejb.getHospitalByEmail(this.username);
        hosReview=ejb.getReviewByHospitalId(hos.getHospitalId());

        return hosReview;
    }

    public void setHosReview(Collection<ReviewTb> hosReview) {
        this.hosReview = hosReview;
    }

    public Collection<ReviewTb> getHreview() {
        HttpSession session = request.getSession(false);
        if(null != params.get("hos"))
        {
            session.setAttribute("hos", params.get("hos"));
        }
        int id=Integer.parseInt(session.getAttribute("hos").toString());
        HospitalTb hospital=new HospitalTb();
        hospital=ejb.getHospitalById(id);
        this.hname=hospital.getHospitalName();
        hreview=ejb.getReviewByHospitalId(hospital.getHospitalId());
        return hreview;
    }

    public void setHreview(Collection<ReviewTb> hreview) {
        this.hreview = hreview;
    }
    
    
    
    

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getHid() {
        return hid;
    }

    public void setHid(int hid) {
        this.hid = hid;
    }

    public int getDid() {
        
        return did;
    }

    public void setDid(int did) {
        this.did = did;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Collection<ReviewTb> getAll() {
        this.did=Integer.parseInt(params.get("did"));
        
        all=ejb.getReviewByDoctorId(did);
        return all;
    }

    public void setAll(Collection<ReviewTb> all) {
        
        this.all = all;
    }
    
    public String addReview()
    {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession session = request.getSession(true);
 
        //System.out.println(session.getAttribute("did").toString()+"------------");
        if(! email.equals(""))
        {
               
            PatientTb p=ejb.getPatientByEmail(email);
            res=c.addReview(Response.class, String.valueOf(p.getPatientId()), String.valueOf(session.getAttribute("did").toString()), "0", this.review);
            return "doctorProfile.xhtml?faces-redirect=true&did="+session.getAttribute("did").toString();
        }
        else
        {
            return "login.xhtml?faces-redirect=true";
        }
        
    }
    
     public String addHosReview()
    {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession session = request.getSession(true);
        //System.out.println(session.getAttribute("did").toString()+"------------");
        if(! email.equals(""))
        {
           int id=Integer.parseInt(session.getAttribute("hos").toString());
           
            PatientTb p=ejb.getPatientByEmail(email);
            res=c.addReview(Response.class, String.valueOf(p.getPatientId()),"0",String.valueOf(id), this.review);
            return "hosReview.xhtml?faces-redirect=true&hos="+id;
        }
        else
        {
            return "login.xhtml?faces-redirect=true";
        }
        
    }
    
}
