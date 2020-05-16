/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cdi;

import beans.doctzBeanLocal;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;

/**
 *
 * @author ADMIN
 */
@Named(value = "countBean")
@Dependent
public class countBean {

    @EJB doctzBeanLocal ejb;
    long doctors,hospitals,patients,appointments;

    public long getDoctors() {
        return ejb.getTotalDoctors();
    }

    public void setDoctors(long doctors) {
        this.doctors = doctors;
    }

    public long getHospitals() {
        return ejb.getTotalHospitals();
    }

    public void setHospitals(long hospitals) {
        this.hospitals = hospitals;
    }

    public long getPatients() {
        return ejb.getTotalPatients();
    }

    public void setPatients(long patients) {
        this.patients = patients;
    }

    public long getAppointments() {
        return ejb.getTotalAppointments();
    }

    public void setAppointments(long appointments) {
        this.appointments =appointments;
    }
    public countBean() {
    }
    
}
