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
@Table(name = "doctor_schedule_tb")
//@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DoctorScheduleTb.findAll", query = "SELECT d FROM DoctorScheduleTb d"),
    @NamedQuery(name = "DoctorScheduleTb.findCount", query = "SELECT count(d.scheduleId) FROM DoctorScheduleTb d"),
    @NamedQuery(name = "DoctorScheduleTb.verifySchedule", query = "SELECT count(d.scheduleId) FROM DoctorScheduleTb d where d.doctorId.doctorId=:did and d.date=:d and (d.toTime<=:f1 or d.fromTime>=:f1) and (d.fromTime>= :t1 or d.toTime<= :t1)"),
    @NamedQuery(name = "DoctorScheduleTb.findByScheduleId", query = "SELECT d FROM DoctorScheduleTb d WHERE d.scheduleId = :scheduleId"),
    @NamedQuery(name = "DoctorScheduleTb.findByDate", query = "SELECT d FROM DoctorScheduleTb d WHERE d.date = :date"),
    @NamedQuery(name = "DoctorScheduleTb.findByFromTime", query = "SELECT d FROM DoctorScheduleTb d WHERE d.fromTime = :fromTime"),
    @NamedQuery(name = "DoctorScheduleTb.findByToTime", query = "SELECT d FROM DoctorScheduleTb d WHERE d.toTime = :toTime"),
    @NamedQuery(name = "DoctorScheduleTb.findByNumberOfPatient", query = "SELECT d FROM DoctorScheduleTb d WHERE d.numberOfPatient = :numberOfPatient"),
    @NamedQuery(name = "DoctorScheduleTb.findDoctorByHospital",query="SELECT DISTINCT d.doctorId FROM DoctorScheduleTb d WHERE d.hospitalId = :hospitalId"),
    @NamedQuery(name = "DoctorScheduleTb.findHospitalByDoctor",query="SELECT DISTINCT d.hospitalId FROM DoctorScheduleTb d WHERE d.doctorId.doctorId = :doctorId"),
    @NamedQuery(name = "DoctorScheduleTb.findScheduleByDoctorAndDate",query="SELECT d FROM DoctorScheduleTb d WHERE d.doctorId.doctorId = :doctorId and d.date=:date and d.isActive=1"),
    @NamedQuery(name = "DoctorScheduleTb.findScheduleByHospitalAndDoctor",query="SELECT d FROM DoctorScheduleTb d WHERE d.hospitalId.hospitalId = :hospitalId and d.doctorId.doctorId= :doctorId"),
    @NamedQuery(name = "DoctorScheduleTb.findScheduleByHospital",query="SELECT DISTINCT d.doctorId FROM DoctorScheduleTb d WHERE d.hospitalId.hospitalId = :hospitalId"),
    @NamedQuery(name = "DoctorScheduleTb.findByIsActive", query = "SELECT d FROM DoctorScheduleTb d WHERE d.isActive = :isActive")})
public class DoctorScheduleTb implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "scheduleId")
    private Integer scheduleId;
    @Basic(optional = false)
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Basic(optional = false)
    @Column(name = "fromTime")
    @Temporal(TemporalType.TIME)
    private Date fromTime;
    @Basic(optional = false)
    @Column(name = "toTime")
    @Temporal(TemporalType.TIME)
    private Date toTime;
    @Basic(optional = false)
    @Column(name = "numberOfPatient")
    private int numberOfPatient;
    @Basic(optional = false)
    @Column(name = "isActive")
    private int isActive;
    @JoinColumn(name = "hospitalId", referencedColumnName = "hospitalId")
    @ManyToOne(optional = false)
    private HospitalTb hospitalId;
    @JoinColumn(name = "doctorId", referencedColumnName = "doctorId")
    @ManyToOne(optional = false)
    private DoctorTb doctorId;

    public DoctorScheduleTb() {
    }

    public DoctorScheduleTb(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }

    public DoctorScheduleTb(Integer scheduleId, Date date, Date fromTime, Date toTime, int numberOfPatient, int isActive) {
        this.scheduleId = scheduleId;
        this.date = date;
        this.fromTime = fromTime;
        this.toTime = toTime;
        this.numberOfPatient = numberOfPatient;
        this.isActive = isActive;
    }

    public Integer getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getFromTime() {
        return fromTime;
    }

    public void setFromTime(Date fromTime) {
        this.fromTime = fromTime;
    }

    public Date getToTime() {
        return toTime;
    }

    public void setToTime(Date toTime) {
        this.toTime = toTime;
    }

    public int getNumberOfPatient() {
        return numberOfPatient;
    }

    public void setNumberOfPatient(int numberOfPatient) {
        this.numberOfPatient = numberOfPatient;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public HospitalTb getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(HospitalTb hospitalId) {
        this.hospitalId = hospitalId;
    }

    public DoctorTb getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(DoctorTb doctorId) {
        this.doctorId = doctorId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (scheduleId != null ? scheduleId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DoctorScheduleTb)) {
            return false;
        }
        DoctorScheduleTb other = (DoctorScheduleTb) object;
        if ((this.scheduleId == null && other.scheduleId != null) || (this.scheduleId != null && !this.scheduleId.equals(other.scheduleId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.DoctorScheduleTb[ scheduleId=" + scheduleId + " ]";
    }
    
}
