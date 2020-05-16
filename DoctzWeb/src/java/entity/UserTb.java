/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ADMIN
 */
@Entity
@Table(name = "user_tb")
//@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserTb.findAll", query = "SELECT u FROM UserTb u"),
    @NamedQuery(name = "UserTb.findByUserId", query = "SELECT u FROM UserTb u WHERE u.userId = :userId"),
    @NamedQuery(name = "UserTb.findByUserName", query = "SELECT u FROM UserTb u WHERE u.userName = :userName"),
    @NamedQuery(name = "UserTb.findByPassword", query = "SELECT u FROM UserTb u WHERE u.password = :password"),
    @NamedQuery(name = "UserTb.findByEmail", query = "SELECT u FROM UserTb u WHERE u.email = :email"),
    @NamedQuery(name = "UserTb.findByContact", query = "SELECT u FROM UserTb u WHERE u.contact = :contact")})
public class UserTb implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "userId")
    private Integer userId;
    @Basic(optional = false)
    @Column(name = "userName")
    private String userName;
    @Basic(optional = false)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @Column(name = "contact")
    private long contact;
    
     @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Collection<DoctorTb> doctorTbCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Collection<PatientTb> patientTbCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Collection<UsergroupTb> usergroupTbCollection;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
//    private Collection<AppointmentTb> appointmentTbCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Collection<HospitalTb> hospitalTbCollection;

    public UserTb() {
    }

    public UserTb(Integer userId) {
        this.userId = userId;
    }

    public UserTb(Integer userId, String userName, String password, String email, long contact) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.contact = contact;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getContact() {
        return contact;
    }

    public void setContact(long contact) {
        this.contact = contact;
    }
    
    @JsonbTransient
    public Collection<DoctorTb> getDoctorTbCollection() {
        return doctorTbCollection;
    }

    public void setDoctorTbCollection(Collection<DoctorTb> doctorTbCollection) {
        this.doctorTbCollection = doctorTbCollection;
    }

    @JsonbTransient
    public Collection<PatientTb> getPatientTbCollection() {
        return patientTbCollection;
    }

    public void setPatientTbCollection(Collection<PatientTb> patientTbCollection) {
        this.patientTbCollection = patientTbCollection;
    }

    @JsonbTransient
    public Collection<UsergroupTb> getUsergroupTbCollection() {
        return usergroupTbCollection;
    }

    public void setUsergroupTbCollection(Collection<UsergroupTb> usergroupTbCollection) {
        this.usergroupTbCollection = usergroupTbCollection;
    }

// @JsonbTransient
//    public Collection<AppointmentTb> getAppointmentTbCollection() {
//        return appointmentTbCollection;
//    }
//
//    public void setAppointmentTbCollection(Collection<AppointmentTb> appointmentTbCollection) {
//        this.appointmentTbCollection = appointmentTbCollection;
//    }

    @JsonbTransient
    public Collection<HospitalTb> getHospitalTbCollection() {
        return hospitalTbCollection;
    }

    public void setHospitalTbCollection(Collection<HospitalTb> hospitalTbCollection) {
        this.hospitalTbCollection = hospitalTbCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserTb)) {
            return false;
        }
        UserTb other = (UserTb) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.UserTb[ userId=" + userId + " ]";
    }
    
}
