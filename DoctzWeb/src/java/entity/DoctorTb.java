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
@Table(name = "doctor_tb")
//@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DoctorTb.findAll", query = "SELECT d FROM DoctorTb d"),
    @NamedQuery(name = "DoctorTb.findByDoctorId", query = "SELECT d FROM DoctorTb d WHERE d.doctorId = :doctorId"),
    @NamedQuery(name = "DoctorTb.findByDoctorName", query = "SELECT d FROM DoctorTb d WHERE d.doctorName = :doctorName"),
    @NamedQuery(name = "DoctorTb.findByExperience", query = "SELECT d FROM DoctorTb d WHERE d.experience = :experience"),
    @NamedQuery(name = "DoctorTb.findByGender", query = "SELECT d FROM DoctorTb d WHERE d.gender = :gender"),
    @NamedQuery(name = "DoctorTb.findByEducation", query = "SELECT d FROM DoctorTb d WHERE d.education = :education"),
    @NamedQuery(name = "DoctorTb.getDoctorByExperience", query = "SELECT d FROM DoctorTb d where d.isActive=1 ORDER BY d.experience DESC "),
    @NamedQuery(name = "DoctorTb.getDoctorByExperienceAndSpec", query = "SELECT d FROM DoctorTb d where d.isActive=1 and d.specializationId.name =:spec ORDER BY d.experience DESC"),
    @NamedQuery(name = "DoctorTb.findBySpecializationName", query = "SELECT d FROM DoctorTb d WHERE d.specializationId.name = :specializationName and d.isActive=1"),
    @NamedQuery(name = "DoctorTb.findBySpecialization", query = "SELECT d FROM DoctorTb d WHERE d.specializationId = :specializationId"),
    @NamedQuery(name = "DoctorTb.getTotalDoctors", query = "SELECT count(d.doctorId) FROM DoctorTb d WHERE d.isActive = 1"),
    @NamedQuery(name = "DoctorTb.findByIsActive", query = "SELECT d FROM DoctorTb d WHERE d.isActive = :isActive")})
public class DoctorTb implements Serializable {
    
     @OneToMany(cascade = CascadeType.ALL, mappedBy = "doctorId")
    private Collection<DoctorAttachmentTb> doctorAttachmentTbCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "doctorId")
    private Integer doctorId;
    @Basic(optional = false)
    @Column(name = "doctorName")
    private String doctorName;
    @Basic(optional = false)
    @Column(name = "experience")
    private String experience;
    @Basic(optional = false)
    @Column(name = "gender")
    private String gender;
    @Basic(optional = false)
    @Lob
    @Column(name = "certificates")
    private String certificates;
    @Basic(optional = false)
    @Column(name = "education")
    private String education;
    @Basic(optional = false)
    @Lob
    @Column(name = "profile")
    private String profile;
    @Basic(optional = false)
    @Column(name = "isActive")
    private int isActive;
    @JoinColumn(name = "specializationId", referencedColumnName = "specializationId")
    @ManyToOne(optional = false)
    private SpecializationTb specializationId;
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    @ManyToOne(optional = false)
    private UserTb userId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "doctorId")
    private Collection<DoctorScheduleTb> doctorScheduleTbCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "doctorId")
    private Collection<ReviewTb> reviewTbCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "doctorId")
    private Collection<AppointmentTb> appointmentTbCollection;

    public DoctorTb() {
    }

    public DoctorTb(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public DoctorTb(Integer doctorId, String doctorName, String experience, String gender, String certificates, String education, String profile, int isActive) {
        this.doctorId = doctorId;
        this.doctorName = doctorName;
        this.experience = experience;
        this.gender = gender;
        this.certificates = certificates;
        this.education = education;
        this.profile = profile;
        this.isActive = isActive;
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCertificates() {
        return certificates;
    }

    public void setCertificates(String certificates) {
        this.certificates = certificates;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public SpecializationTb getSpecializationId() {
        return specializationId;
    }

    public void setSpecializationId(SpecializationTb specializationId) {
        this.specializationId = specializationId;
    }

    public UserTb getUserId() {
        return userId;
    }

    public void setUserId(UserTb userId) {
        this.userId = userId;
    }
    
    @JsonbTransient
    public Collection<DoctorScheduleTb> getDoctorScheduleTbCollection() {
        return doctorScheduleTbCollection;
    }

    public void setDoctorScheduleTbCollection(Collection<DoctorScheduleTb> doctorScheduleTbCollection) {
        this.doctorScheduleTbCollection = doctorScheduleTbCollection;
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
        hash += (doctorId != null ? doctorId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DoctorTb)) {
            return false;
        }
        DoctorTb other = (DoctorTb) object;
        if ((this.doctorId == null && other.doctorId != null) || (this.doctorId != null && !this.doctorId.equals(other.doctorId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.DoctorTb[ doctorId=" + doctorId + " ]";
    }
    
     @JsonbTransient
    public Collection<DoctorAttachmentTb> getDoctorAttachmentTbCollection() {
        return doctorAttachmentTbCollection;
    }

    public void setDoctorAttachmentTbCollection(Collection<DoctorAttachmentTb> doctorAttachmentTbCollection) {
        this.doctorAttachmentTbCollection = doctorAttachmentTbCollection;
    }
    
}
