/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "doctor_attachment_tb")
//@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DoctorAttachmentTb.findAll", query = "SELECT d FROM DoctorAttachmentTb d"),
    @NamedQuery(name = "DoctorAttachmentTb.findByDoctorId", query = "SELECT d FROM DoctorAttachmentTb d WHERE d.doctorId.doctorId = :doctorId"),

   @NamedQuery(name = "DoctorAttachmentTb.findByPatientId", query = "SELECT d FROM DoctorAttachmentTb d WHERE d.patientId.patientId = :patientId"),
    @NamedQuery(name = "DoctorAttachmentTb.findByAttachmentId", query = "SELECT d FROM DoctorAttachmentTb d WHERE d.attachmentId = :attachmentId")})
public class DoctorAttachmentTb implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "attachmentId")
    private Integer attachmentId;
    @Basic(optional = false)
    @Lob
    @Column(name = "attachment")
    private String attachment;
    @JoinColumn(name = "doctorId", referencedColumnName = "doctorId")
    @ManyToOne(optional = false)
    private DoctorTb doctorId;
    @JoinColumn(name = "patientId", referencedColumnName = "patientId")
    @ManyToOne(optional = false)
    private PatientTb patientId;

    public DoctorAttachmentTb() {
    }

    public DoctorAttachmentTb(Integer attachmentId) {
        this.attachmentId = attachmentId;
    }

    public DoctorAttachmentTb(Integer attachmentId, String attachment) {
        this.attachmentId = attachmentId;
        this.attachment = attachment;
    }

    public Integer getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(Integer attachmentId) {
        this.attachmentId = attachmentId;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public DoctorTb getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(DoctorTb doctorId) {
        this.doctorId = doctorId;
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
        hash += (attachmentId != null ? attachmentId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DoctorAttachmentTb)) {
            return false;
        }
        DoctorAttachmentTb other = (DoctorAttachmentTb) object;
        if ((this.attachmentId == null && other.attachmentId != null) || (this.attachmentId != null && !this.attachmentId.equals(other.attachmentId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.DoctorAttachmentTb[ attachmentId=" + attachmentId + " ]";
    }
    
}
