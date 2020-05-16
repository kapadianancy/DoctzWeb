/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.json.bind.annotation.JsonbTransient;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "patient_tb")
//@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PatientTb.findAll", query = "SELECT p FROM PatientTb p where p.isActive=1"),
    @NamedQuery(name = "PatientTb.findByPatientId", query = "SELECT p FROM PatientTb p WHERE p.patientId = :patientId"),
    @NamedQuery(name = "PatientTb.findByPatientName", query = "SELECT p FROM PatientTb p WHERE p.patientName = :patientName"),
    @NamedQuery(name = "PatientTb.findByGender", query = "SELECT p FROM PatientTb p WHERE p.gender = :gender"),
    @NamedQuery(name = "PatientTb.findByAge", query = "SELECT p FROM PatientTb p WHERE p.age = :age"),
    @NamedQuery(name = "PatientTb.findByEmail", query = "SELECT p FROM PatientTb p WHERE p.userId.email = :email"),
    @NamedQuery(name = "PatientTb.getTotalPatients", query = "SELECT count(p.patientId) FROM PatientTb p WHERE p.isActive = 1"),
    @NamedQuery(name = "PatientTb.findByIsActive", query = "SELECT p FROM PatientTb p WHERE p.isActive = :isActive")})
public class PatientTb implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "patientId")
    private Collection<DoctorAttachmentTb> doctorAttachmentTbCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "patientId")
    private Integer patientId;
    @Basic(optional = false)
    @Column(name = "patientName")
    private String patientName;
    @Basic(optional = false)
    @Column(name = "gender")
    private String gender;
    @Basic(optional = false)
    @Lob
    @Column(name = "address")
    private String address;
    @Basic(optional = false)
    @Column(name = "age")
    private int age;
    @Basic(optional = false)
    @Column(name = "isActive")
    private int isActive;
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    @ManyToOne(optional = false)
    private UserTb userId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "patientId")
    private Collection<ReviewTb> reviewTbCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "patientId")
    private Collection<AppointmentTb> appointmentTbCollection;

    public PatientTb() {
    }

    public PatientTb(Integer patientId) {
        this.patientId = patientId;
    }

    public PatientTb(Integer patientId, String patientName, String gender, String address, int age, int isActive) {
        this.patientId = patientId;
        this.patientName = patientName;
        this.gender = gender;
        this.address = address;
        this.age = age;
        this.isActive = isActive;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public UserTb getUserId() {
        return userId;
    }

    public void setUserId(UserTb userId) {
        this.userId = userId;
    }

    @JsonbTransient
    public Collection<ReviewTb> getReviewTbCollection() {
        return reviewTbCollection;
    }

    public void setReviewTbCollection(Collection<ReviewTb> reviewTbCollection) {
        this.reviewTbCollection = reviewTbCollection;
    }

    @JsonbTransient
    public Collection<AppointmentTb> getAppointmentTbCollection() {
        return appointmentTbCollection;
    }

    public void setAppointmentTbCollection(Collection<AppointmentTb> appointmentTbCollection) {
        this.appointmentTbCollection = appointmentTbCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (patientId != null ? patientId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PatientTb)) {
            return false;
        }
        PatientTb other = (PatientTb) object;
        if ((this.patientId == null && other.patientId != null) || (this.patientId != null && !this.patientId.equals(other.patientId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.PatientTb[ patientId=" + patientId + " ]";
    }

    @JsonbTransient
    public Collection<DoctorAttachmentTb> getDoctorAttachmentTbCollection() {
        return doctorAttachmentTbCollection;
    }

    public void setDoctorAttachmentTbCollection(Collection<DoctorAttachmentTb> doctorAttachmentTbCollection) {
        this.doctorAttachmentTbCollection = doctorAttachmentTbCollection;
    }
    
}
