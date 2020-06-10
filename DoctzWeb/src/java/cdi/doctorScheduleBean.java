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
import entity.DoctorScheduleTb;
import entity.DoctorTb;
import entity.FeesTb;
import entity.HospitalTb;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import servlets.test;


/**
 *
 * @author Admin
 */
@Named(value = "doctorScheduleBean")
@RequestScoped
public class doctorScheduleBean {

    @EJB doctzBeanLocal ejb;
    Response res;
    mydoctor d;
    myhospital h;
    
    private int sid,hid,did;
    private Date date;
    private String fromTime,toTime,msgError;
    private int patients;
    private String username;
    
    Collection<HospitalTb> hos;
    Collection<FeesTb> fees;
    Collection<DoctorTb> hall;
    
    Collection<DoctorScheduleTb> all;
    Collection<DoctorScheduleTb> docSchedule,hosDocSchedule;
    GenericType<Collection<DoctorScheduleTb>> gall;
    
    GenericType<DoctorTb> gdoc;
    DoctorTb doc;
    
    Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
     HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
     HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
       
    
    
    public doctorScheduleBean() {
        String token="";

        HttpSession session = request.getSession(false);
        
        if(null != session.getAttribute("token"))
        {
          token = request.getSession().getAttribute("token").toString();
//          c = new myclient(token); 
//          a=new myadmin(token);
          d=new mydoctor(token);
          h=new myhospital(token);
        }
        else
        {
//          c=new myclient();
//          a=new myadmin();
          d=new mydoctor();
          h=new myhospital();
        }
        gall=new GenericType<Collection<DoctorScheduleTb>>(){};
        all=new ArrayList<DoctorScheduleTb>();
        hall=new ArrayList<DoctorTb>();
        hos=new ArrayList<HospitalTb>();
        fees=new ArrayList<FeesTb>();
        gdoc=new GenericType<DoctorTb>(){};
        docSchedule=new ArrayList<DoctorScheduleTb>();
        hosDocSchedule=new ArrayList<DoctorScheduleTb>();
        doc=new DoctorTb();
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getFromTime() {
        return fromTime;
    }

    public void setFromTime(String fromTime) {
        this.fromTime = fromTime;
    }

    public String getToTime() {
        return toTime;
    }

    public void setToTime(String toTime) {
        this.toTime = toTime;
    }

    public int getPatients() {
        return patients;
    }

    public void setPatients(int patients) {
        this.patients = patients;
    }

    public Collection<DoctorTb> getHall() {
        return hall;
    }

    public void setHall(Collection<DoctorTb> hall) {
        this.hall = hall;
    }

    public String getMsgError() {
        return msgError;
    }

    public void setMsgError(String msgError) {
        this.msgError = msgError;
    }
    
    
    

    public Collection<DoctorScheduleTb> getAll() {
        return all;
    }
    
    public Collection<DoctorScheduleTb> getSchedule(int hid)
    {
        int did=Integer.parseInt(params.get("did"));
        this.setAll(ejb.getScheduleByHospitalAndDoctorId(hid, did));
        
        return all;
    }

    public Collection<DoctorScheduleTb> getDocSchedule() {
        HttpSession session = request.getSession(false);
        username=session.getAttribute("username").toString();
        doc=ejb.getDoctorByEmail(this.username);
        //System.out.println("doc---------"+doc);
        res=d.getDoctorSchedule(Response.class,String.valueOf(doc.getDoctorId()));
        this.setDocSchedule(res.readEntity(gall));
        //System.out.println("schedule---------"+docSchedule);
        return docSchedule;
    }
    
    public String getScheduleByDocAndHos(int did)
    {
        return "schedule.xhtml?faces-redirect=true&did="+did;
    }

    public Collection<DoctorScheduleTb> getHosDocSchedule() {
         HttpSession session = request.getSession(false);
        username=session.getAttribute("username").toString();
        HospitalTb hid=ejb.getHospitalByEmail(this.username);
        String d=params.get("did");
        res=h.getScheduleByHospitalAndDoctorId(Response.class, String.valueOf(hid.getHospitalId()),d);
        this.setHosDocSchedule(res.readEntity(gall));
        return hosDocSchedule;
    }

    public void setHosDocSchedule(Collection<DoctorScheduleTb> hosDocSchedule) {
        this.hosDocSchedule = hosDocSchedule;
    }

    
    
    public void setDocSchedule(Collection<DoctorScheduleTb> doctorSchedule) {
        this.docSchedule= doctorSchedule;
    }
    
    
    
    public Collection<DoctorScheduleTb> getScheduleByDoctorAndDate(int did,String date)
    {
        java.sql.Date d1 = java.sql.Date.valueOf(date);
        //System.out.println(did+" "+d1);
        this.setAll(ejb.getScheduleByDoctorAndDate(did, d1));
        return all;
    }
    
    public Collection<DoctorScheduleTb> getScheduleByDoctorAndHospitalAndDate(int did,String date)
    {
       HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession session = request.getSession(true);
       if(null != params.get("hos"))
       {
           session.setAttribute("hid", params.get("hos"));
       }
       int hid=Integer.parseInt(session.getAttribute("hid").toString());
        java.sql.Date d1 = java.sql.Date.valueOf(date);
        //System.out.println(did+" "+d1);
        this.setAll(ejb.getScheduleByDoctorAndHospitalAndDate(did,hid, d1));
        return all;
    }

    public Collection<DoctorTb> getScheduleByHospital()
    {
        int hospital=Integer.parseInt(params.get("hos"));
        this.setHall(ejb.getScheduleByHospital(hospital));
        return hall;
    }
    
    public void setAll(Collection<DoctorScheduleTb> all) {
        
        this.all = all;
    }

    public Collection<HospitalTb> getHos() {
        int did=Integer.parseInt(params.get("did"));
        hos=ejb.getHospitalByDoctorId(did);
        return hos;
    }

    public void setHos(Collection<HospitalTb> hos) {
        this.hos = hos;
    }
    
    public Collection<FeesTb> getFeesBySpecialityandHospital(int spe,int hos)
    {
       
        fees=ejb.getFeesBySpecialityandHospital(spe, hos); 
        return fees;
    }

    public Collection<FeesTb> getFees() {
        return fees;
    }

    public void setFees(Collection<FeesTb> fees) {
        this.fees = fees;
    }
    
    public String addSchedule()
    {
        HttpSession session = request.getSession(false);
        username=session.getAttribute("username").toString();
        HospitalTb hid=ejb.getHospitalByEmail(this.username);
        SimpleDateFormat ft =new SimpleDateFormat ("hh:mm:ss");
        java.sql.Time t1=null;
        java.sql.Time t2=null;
        
            try {
                t1=new Time(ft.parse(this.fromTime).getTime());
                t2=new Time(ft.parse(this.toTime).getTime());
                         
            } catch (ParseException ex) {
                Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
            }   
        //res=h.addSchedule(Response.class, String.valueOf(hid.getHospitalId()),String.valueOf(this.did),d,String.valueOf(t1),String.valueOf(t2),String.valueOf(this.patients));
       int status=ejb.addSchedule(hid.getHospitalId(), this.did,this.date, t1, t2, this.patients);
        
       // System.out.println("Status------------------------->>>>>>>>>>>>>"+status);
        if(status == 1)
        {
            return "doctors.xhtml?faces-redirect=true";
        }
        else
        {
            this.msgError="Doctor is not available for this time period.";
            return "addDoctorSchedule.xhtml";
        }
        
        
    }
   
}
