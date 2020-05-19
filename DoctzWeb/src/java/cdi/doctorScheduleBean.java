/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cdi;

import beans.doctzBeanLocal;
import entity.DoctorScheduleTb;
import entity.DoctorTb;
import entity.HospitalTb;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.ws.rs.core.GenericType;


/**
 *
 * @author Admin
 */
@Named(value = "doctorScheduleBean")
@RequestScoped
public class doctorScheduleBean {

    @EJB doctzBeanLocal ejb;
    
    private int sid,hid,did;
    Date date;
    Time fromTime,toTime;
    int patients;
    
    Collection<HospitalTb> hos;
    
    Collection<DoctorScheduleTb> all;
    GenericType<Collection<DoctorScheduleTb>> gall;
    
    Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
    
    
    public doctorScheduleBean() {
        gall=new GenericType<Collection<DoctorScheduleTb>>(){};
        all=new ArrayList<DoctorScheduleTb>();
        hos=new ArrayList<HospitalTb>();
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

    public Time getFromTime() {
        return fromTime;
    }

    public void setFromTime(Time fromTime) {
        this.fromTime = fromTime;
    }

    public Time getToTime() {
        return toTime;
    }

    public void setToTime(Time toTime) {
        this.toTime = toTime;
    }

    public int getPatients() {
        return patients;
    }

    public void setPatients(int patients) {
        this.patients = patients;
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
    
    
    
    
}
