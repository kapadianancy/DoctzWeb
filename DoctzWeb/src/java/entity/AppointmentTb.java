/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "appointment_tb")
////@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AppointmentTb.findAll", query = "SELECT a FROM AppointmentTb a WHERE a.isActive=1"),
    @NamedQuery(name = "AppointmentTb.findByAppointmentId", query = "SELECT a FROM AppointmentTb a WHERE a.appointmentId = :appointmentId"),
    @NamedQuery(name = "AppointmentTb.findByDate", query = "SELECT a FROM AppointmentTb a WHERE a.date = :date"),
    @NamedQuery(name = "AppointmentTb.findByTime", query = "SELECT a FROM AppointmentTb a WHERE a.time = :time"),
    
    @NamedQuery(name = "AppointmentTb.findByStatus", query = "SELECT a FROM AppointmentTb a WHERE a.status = :status"),
    @NamedQuery(name = "AppointmentTb.findByDoctorId", query = "SELECT a FROM AppointmentTb a WHERE a.doctorId = :doctorId and a.hospitalId =:hospitalId and a.isActive=1"),
    @NamedQuery(name = "AppointmentTb.findPatientOfDoctor", query = "SELECT distinct(a.patientId) FROM AppointmentTb a WHERE a.doctorId = :doctorId"),
    @NamedQuery(name = "AppointmentTb.findPatientOfHospital", query = "SELECT distinct(a.patientId) FROM AppointmentTb a WHERE a.hospitalId = :hospitalId"),
    @NamedQuery(name = "AppointmentTb.findByPatientId", query = "SELECT a FROM AppointmentTb a WHERE a.patientId = :patientId and a.isActive=1"),
    @NamedQuery(name = "AppointmentTb.getTotalAppointments", query = "SELECT count(a.appointmentId) FROM AppointmentTb a"),
    @NamedQuery(name = "AppointmentTb.findByIsActive", query = "SELECT a FROM AppointmentTb a WHERE a.isActive = :isActive")})
public class AppointmentTb implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "appointmentId")
    private Integer appointmentId;
    @Basic(optional = false)
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Basic(optional = false)
    @Column(name = "time")
    @Temporal(TemporalType.TIME)
    private Date time;
   
    @Basic(optional = false)
    @Lob
    @Column(name = "invoice")
    private String invoice;
    @Basic(optional = false)
    @Column(name = "status")
    private String status;
    @Basic(optional = false)
    @Column(name = "isActive")
    private int isActive;
    @JoinColumn(name = "doctorId", referencedColumnName = "doctorId")
    @ManyToOne(optional = false)
    private DoctorTb doctorId;
    @JoinColumn(name = "hospitalId", referencedColumnName = "hospitalId")
    @ManyToOne(optional = false)
    private HospitalTb hospitalId;
    @JoinColumn(name = "patientId", referencedColumnName = "patientId")
    @ManyToOne(optional = false)
    private PatientTb patientId;

    public AppointmentTb() {
    }

    public AppointmentTb(Integer appointmentId) {
        this.appointmentId = appointmentId;
    }

    public AppointmentTb(Integer appointmentId, Date date, Date time, String invoice, String status, int isActive) {
        this.appointmentId = appointmentId;
        this.date = date;
        this.time = time;
       
        this.invoice = invoice;
        this.status = status;
        this.isActive = isActive;
    }

    public Integer getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Integer appointmentId) {
        this.appointmentId = appointmentId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

   

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public DoctorTb getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(DoctorTb doctorId) {
        this.doctorId = doctorId;
    }

    public HospitalTb getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(HospitalTb hospitalId) {
        this.hospitalId = hospitalId;
    }

    public PatientTb getPatientId() {
        return patientId;
    }

    public void setPatientId(PatientTb patientId) {
        this.patientId = patientId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (appointmentId != null ? appointmentId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AppointmentTb)) {
            return false;
        }
        AppointmentTb other = (AppointmentTb) object;
        if ((this.appointmentId == null && other.appointmentId != null) || (this.appointmentId != null && !this.appointmentId.equals(other.appointmentId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.AppointmentTb[ appointmentId=" + appointmentId + " ]";
    }
    
}
