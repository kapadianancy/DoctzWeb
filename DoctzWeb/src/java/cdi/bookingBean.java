/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cdi;

import beans.doctzBeanLocal;
import client.myclient;
import entity.*;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Response;
import servlets.test;

/**
 *
 * @author ADMIN
 */
@Named(value = "bookingBean")
@RequestScoped
public class bookingBean {

    @EJB doctzBeanLocal ejb;
    Response res;
    myclient c;
    private int patientId,doctorId,hospitalId;
    private AppointmentTb app;
    private PatientTb currpatient;
    private String dname,hname,adate,atime;

     HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
     HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
       
    
    public bookingBean() {
      
        String token="";

        HttpSession session = request.getSession(false);
        if(null != session.getAttribute("token"))
        {
          token = request.getSession().getAttribute("token").toString();
          c = new myclient(token); 
        }
        else
        {
          c=new myclient();
        }
         app=new AppointmentTb();
         currpatient=new PatientTb();
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public int getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(int hospitalId) {
        this.hospitalId = hospitalId;
    }

    public AppointmentTb getApp() {
        return app;
    }

    public void setApp(AppointmentTb app) {
        this.app = app;
    }

    public PatientTb getCurrpatient() {
         HttpSession session = request.getSession(true);
        currpatient=ejb.getPatientByEmail(session.getAttribute("username").toString());
        return currpatient;
    }

    public void setCurrpatient(PatientTb currpatient) {
        this.currpatient = currpatient;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getHname() {
        return hname;
    }

    public void setHname(String hname) {
        this.hname = hname;
    }

    public String getAdate() {
        return adate;
    }

    public void setAdate(String adate) {
        this.adate = adate;
    }

    public String getAtime() {
        return atime;
    }

    public void setAtime(String atime) {
        this.atime = atime;
    }
    
    
    
    public String bookAppointment(int doctorId,int hospitalId,String date,String t)
    {
         HttpSession session = request.getSession(true);
        
        SimpleDateFormat ft =new SimpleDateFormat ("hh:mm:ss");
        Time t1=null;
          
            try {
                t1=new Time(ft.parse(t).getTime());
                session.setAttribute("time",t1);
               
                
            } catch (ParseException ex) {
                Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
            }
        
       
        session.setAttribute("doctorId",doctorId);
        session.setAttribute("hospitalId",hospitalId);
        session.setAttribute("date",date);
        
        
        //System.out.println(session.getAttribute("time"));
         
       
        if(null != session.getAttribute("username"))
        {
           
            session.setAttribute("flag", 0);
            res=c.bookAppointment(Response.class, session.getAttribute("doctorId").toString(),this.getCurrpatient().getPatientId().toString(), session.getAttribute("hospitalId").toString() , session.getAttribute("date").toString(),session.getAttribute("time").toString() );
            ejb.decreaseTotalPatient(Integer.parseInt(session.getAttribute("doctorId").toString()),Integer.parseInt(session.getAttribute("hospitalId").toString()) , Date.valueOf(session.getAttribute("date").toString()),Time.valueOf(session.getAttribute("time").toString()));
            
            return "bookingSuccess.xhtml?faces-redirect=true";
        }
        else
        {
            session.setAttribute("flag", 1);
            return "login.xhtml?faces-redirect=true";
        }
          
        
    }
    
  
    public void display()
    {
        HttpSession session = request.getSession(true);
        DoctorTb d1=new DoctorTb();
        d1=ejb.getDoctorById(Integer.parseInt(session.getAttribute("doctorId").toString()));
        this.setDname(d1.getDoctorName());
        this.setAdate(session.getAttribute("date").toString());
        this.setAtime(session.getAttribute("time").toString());
                    
    }
    
}
