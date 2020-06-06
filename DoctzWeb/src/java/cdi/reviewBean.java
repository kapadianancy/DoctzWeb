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
    Collection<ReviewTb> docReview;
    private String username;
//    private PatientTb p=new PatientTb(); 
    
    
    Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
    
    public reviewBean() {
        all=new ArrayList<ReviewTb>();
        grev=new GenericType<Collection<ReviewTb>>(){};
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        String token="";

        HttpSession session = request.getSession(false);
        username=session.getAttribute("username").toString();
        
        if(null != session.getAttribute("token"))
        {
          token = request.getSession().getAttribute("token").toString();
          System.out.println("Token="+token);
        
//            String token1 = request.getHeader("Authorization").substring("Bearer ".length());
//            System.out.println("Token="+token1);
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
              
             System.err.println(email+"-----------");
         }
         else
         {
             email="";
         }
       docReview=new ArrayList<ReviewTb>();
        
    }

    public String getEmail() {
        
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

//    public PatientTb getP() {
//        System.out.println(email);
//        p=ejb.getPatientByEmail(email);
//        System.err.println(p);
//        return p;
//    }
//
//    public void setP(PatientTb p) {
//        this.p = p;
//    }

    public Collection<ReviewTb> getDocReview() {
        DoctorTb d=new DoctorTb();
        d=ejb.getDoctorByEmail(this.username);
        docReview=ejb.getReviewByDoctorId(d.getDoctorId());
        return docReview;
    }

    public void setDocReview(Collection<ReviewTb> docReview) {
        this.docReview = docReview;
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
           // System.out.println(did);
            PatientTb p=ejb.getPatientByEmail(email);
            res=c.addReview(Response.class, String.valueOf(p.getPatientId()), String.valueOf(session.getAttribute("did").toString()), "0", this.review);
            return "doctorProfile.xhtml?faces-redirect=true&did="+session.getAttribute("did").toString();
        }
        else
        {
            return "login.xhtml?faces-redirect=true";
        }
        
    }
    
    
    
}
