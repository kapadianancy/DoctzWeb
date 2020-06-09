/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cdi;

import beans.doctzBeanLocal;
import client.myadmin;
import client.myclient;
import client.mydoctor;
import client.myhospital;
import entity.*;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
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
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import servlets.test;
import sun.security.krb5.internal.APOptions;

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
    myadmin a;
    mydoctor d;
    myhospital h;
    private int patientId,doctorId,hospitalId;
    private AppointmentTb app;
    private PatientTb currpatient;
    private String dname,hname,adate,atime;
    private String username;
    
    Collection<AppointmentTb> all;
    Collection<AppointmentTb> adminall;
    Collection<AppointmentTb> docAppointment,hosAppointment;
    Collection<AppointmentTb> docPending,hosPending;
    Collection<AppointmentTb> PatientPending;
    GenericType<Collection<AppointmentTb>> gall;
    
    

     HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
     HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
       
    
    public bookingBean() {
      
        String token="";

        HttpSession session = request.getSession(false);
        username=session.getAttribute("username").toString();
        if(null != session.getAttribute("token"))
        {
          token = request.getSession().getAttribute("token").toString();
          c = new myclient(token); 
          a=new myadmin(token);
          d=new mydoctor(token);
          h=new myhospital(token);
        }
        else
        {
          c=new myclient();
          a=new myadmin();
          d=new mydoctor();
          h=new myhospital();
        }
         app=new AppointmentTb();
         currpatient=new PatientTb();
         all=new ArrayList<AppointmentTb>();
         docPending=new ArrayList<AppointmentTb>();
         hosPending=new ArrayList<AppointmentTb>();
         PatientPending=new ArrayList<AppointmentTb>();
         gall=new GenericType<Collection<AppointmentTb>>(){};
         
    }

    public Collection<AppointmentTb> getDocPending() {
        DoctorTb doc=ejb.getDoctorByEmail(this.username);
        docPending=ejb.getDoctorPendingAppoitment(doc.getDoctorId());
        return docPending;
    }

    public void setDocPending(Collection<AppointmentTb> docPending) {
        this.docPending = docPending;
    }

    public Collection<AppointmentTb> getHosPending() {
        HospitalTb hos=ejb.getHospitalByEmail(this.username);
        hosPending=ejb.getHospitalPendingAppoitment(hos.getHospitalId());
        return hosPending;
    }

    public void setHosPending(Collection<AppointmentTb> hosPending) {
        this.hosPending = hosPending;
    }
    
    public Collection<AppointmentTb> getPatientPending() {
        
        PatientTb p=ejb.getPatientByEmail(this.username);
        PatientPending=ejb.getPatientPendingAppoitment(p.getPatientId());
        return PatientPending;
    }

    public void setPatientPending(Collection<AppointmentTb> PatientPending) {
        this.PatientPending = PatientPending;
    }
    
   
    

    public Collection<AppointmentTb> getDocAppointment() {
        DoctorTb doc=new DoctorTb();
        doc=ejb.getDoctorByEmail(this.username);
        this.setDocAppointment(ejb.getAppointmentByDoctor(doc.getDoctorId()));
        return docAppointment;
    }

    public void setDocAppointment(Collection<AppointmentTb> docAppointment) {
        this.docAppointment = docAppointment;
    }

    public Collection<AppointmentTb> getHosAppointment()
    {
        HospitalTb hos=new HospitalTb();
        hos=ejb.getHospitalByEmail(this.username);
        this.setHosAppointment(ejb.getAppointmentByHospital(hos.getHospitalId()));
        return hosAppointment;
    }

    public void setHosAppointment(Collection<AppointmentTb> hosAppointment)
    {
        this.hosAppointment = hosAppointment;
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

    public Collection<AppointmentTb> getAll() {
        PatientTb p=ejb.getPatientByEmail(this.username);
        all=ejb.getAllPatientAppointment(p.getPatientId());
        return all;
    }

    public void setAll(Collection<AppointmentTb> all) {
        this.all = all;
    }

    public Collection<AppointmentTb> getAdminall() {
        res=a.getAllAppointment(Response.class);
        adminall=res.readEntity(gall);
        return adminall;
    }

    public void setAdminall(Collection<AppointmentTb> adminall) {
        this.adminall = adminall;
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
        //HttpSession session = request.getSession(false);
        currpatient=ejb.getPatientByEmail(this.username);
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
                //System.out.println("Time------"+t1);
               
                
            } catch (ParseException ex) {
                Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
            }
        
       
        session.setAttribute("doctorId",doctorId);
        session.setAttribute("hospitalId",hospitalId);
        session.setAttribute("date",date);
        
        
       // System.out.println("---------------"+session.getAttribute("time"));
         
       
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
        
         //System.out.println("---------------"+session.getAttribute("time"));            
    }
    public String getDisable(int sid)
    {
        int i=ejb.getTotalPatientByScheduleId(sid);
        //System.out.println("i--------------"+i);
        if(i==0)
        {
            return "true";
        }
        else
        {
            return "false";
        }
    }
    public String getColor(int sid)
    {
         int i=ejb.getTotalPatientByScheduleId(sid);
        //System.out.println("i--------------"+i);
        if(i==0)
        {
            return "grey";
        }
        else
        {
            return "#17a2b8";
        }
    }
    
    public String getStatus(int id)
    {
        String status="";
        AppointmentTb a=ejb.getAppointmentById(id);
        if(a.getStatus().equals("pending"))
        {
            status="bg-warning-light";
        }
        else if(a.getStatus().equals("cancel"))
        {
             status="bg-danger-light";
        }
        else if(a.getStatus().equals("complete"))
        {
            status="bg-success-light";
        }
        //System.out.println("cdi.bookingBean.getStatus()------"+status);
        return status;
        
    }
    public String cancelAppointment(int aid,int did,int hid,String date,String t)
    {
        SimpleDateFormat ft =new SimpleDateFormat ("hh:mm:ss");
        Time t1=null;
        java.sql.Date date1=null;
          
            try {
                t1=new Time(ft.parse(t).getTime());
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                date1 = Date.valueOf(date);
                
            } catch (ParseException ex) {
                Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
            }
        ejb.increaseTotalPatient(did, hid, date1, t1);
        res=d.cancelAppointment(Response.class, String.valueOf(aid));
        return "cancelAppointment.xhtml";
    }
    
}
