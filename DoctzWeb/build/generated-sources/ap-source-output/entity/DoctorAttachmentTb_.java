package entity;

import entity.DoctorTb;
import entity.PatientTb;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-05-16T11:33:09")
@StaticMetamodel(DoctorAttachmentTb.class)
public class DoctorAttachmentTb_ { 

    public static volatile SingularAttribute<DoctorAttachmentTb, String> attachment;
    public static volatile SingularAttribute<DoctorAttachmentTb, DoctorTb> doctorId;
    public static volatile SingularAttribute<DoctorAttachmentTb, PatientTb> patientId;
    public static volatile SingularAttribute<DoctorAttachmentTb, Integer> attachmentId;

}