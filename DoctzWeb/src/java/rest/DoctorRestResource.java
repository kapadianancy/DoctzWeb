/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import beans.doctzBeanLocal;
import entity.*;
import entity.HospitalTb;
import entity.PatientTb;
import entity.SpecializationTb;
import java.sql.Date;
import java.sql.Time;
import java.util.Collection;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author ADMIN
 */
@Path("doctorRest")
//@RequestScoped
@DeclareRoles({"admin","patient","doctor","hospital"})
public class DoctorRestResource {

    @EJB doctzBeanLocal ejb;
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of DoctorRestResource
     */
    public DoctorRestResource() {
    }

    //public Collection<PatientTb> getAllPatient()
    @GET
    @Path("/getAllPatient")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<PatientTb> getAllPatient()
    {
        return ejb.getAllPatient();
    }
      
//public Collection<HospitalTb> getAllHospital()
    @GET
    @Path("/getAllHospital")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<HospitalTb> getAllHospital()
    {
        return ejb.getAllHospital();
    }
    
//public Collection<DoctorTb> getAllDoctor()
    @GET
    @Path("/getAllDoctor")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<DoctorTb> getAllDoctor()
    {
        return ejb.getAllDoctor();
    }
    
//  public Collection<SpecializationTb> getAllSpecialization();
    @GET
    @Path("/getAllSpecialization")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<SpecializationTb> getAllSpecialization()
    {
        return ejb.getAllSpecialization();
    }

//public Collection<FeesTb> getFeesBySpecialityandHospital(int specializaionId,int hospitalId)  
    @GET
    @Path("/getFeesBySpecialityandHospital/{specializaionId}/{hospitalId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<FeesTb> getFeesBySpecialityandHospital(@PathParam("specializaionId") int specializaionId,@PathParam("hospitalId") int hospitalId)
    {
        return ejb.getFeesBySpecialityandHospital(specializaionId, hospitalId);
    }
 //public int bookAppointment(int doctorId,int patientId,int hospitalId,Date date,Time time)
    @RolesAllowed({"hospital","patient","doctor"})
    @POST
    @Path("/bookAppointment/{doctorId}/{patientId}/{hospitalId}/{date}/{time}")
    @Produces(MediaType.APPLICATION_JSON)
    public int bookAppointment(@PathParam("doctorId") int doctorId,@PathParam("patientId") int patientId,@PathParam("hospitalId") int hospitalId,@PathParam("date") Date date,@PathParam("time") Time time){
        return ejb.bookAppointment(doctorId, patientId, hospitalId, date, time);
    }
    
//public int cancelAppointment(int appointmentId)
    @RolesAllowed({"hospital","patient","doctor"})
    @DELETE
    @Path("/cancelAppointment/{appointmentId}")
    @Produces(MediaType.APPLICATION_JSON)
    public int cancelAppointment(@PathParam("appointmentId") int appointmentId) 
    {
        return ejb.cancelAppointment(appointmentId);
    }
    
//public Collection<AppointmentTb> getAllDoctorAppointment(int hospitalId,int doctorId)
    @GET
    @Path("/getAllDoctorAppointment/{hospitalId}/{doctorId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<AppointmentTb> getAllDoctorAppointment(@PathParam("hospitalId") int hospitalId,@PathParam("doctorId")int doctorId)
    {
        return ejb.getAllDoctorAppointment(hospitalId, doctorId);
    }
    
//public Collection<AppointmentTb> getAllPatientAppointment(int patientId)
    @GET
    @Path("/getAllPatientAppointment/{patientId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<AppointmentTb> getAllPatientAppointment(@PathParam("patientId") int patientId)
    {
        return ejb.getAllPatientAppointment(patientId);
    }
    
//public Collection<PatientTb> getPatientOfDoctor(int doctorId)
    @GET
    @Path("/getPatientOfDoctor/{doctorId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<PatientTb> getPatientOfDoctor(@PathParam("doctorId") int doctorId)
    {
        return ejb.getPatientOfDoctor(doctorId);
    }
    
//public Collection<PatientTb> getPatientOfHospital(int hospitalId)
    @GET
    @Path("/getPatientOfHospital/{hospitalId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<PatientTb> getPatientOfHospital(@PathParam("hospitalId") int hospitalId) 
    {
        return ejb.getPatientOfHospital(hospitalId);
    }
    
//public int editDoctorProfile(int doctorId,String doctorName,int specializaionId,String experience,String gender,String certificates,String education,String email,long contact,String username,int userId,String profile,String document)
   @RolesAllowed("doctor")
   @POST
   @Path("/editDoctorProfile/{doctorId}/{doctorName}/{specializaionId}/{experience}/{gender}/{certificates}/{education}/{email}/{contact}/{username}/{userId}/{profile}/{document}")
   @Produces(MediaType.APPLICATION_JSON)
   public int editDoctorProfile(@PathParam("doctorId") int doctorId,@PathParam("doctorName") String doctorName,@PathParam("specializaionId") int specializaionId,@PathParam("experience") String experience,@PathParam("gender") String gender,@PathParam("certificates") String certificates,@PathParam("education") String education,@PathParam("email") String email,@PathParam("contact") long contact,@PathParam("username") String username,@PathParam("userId") int userId,@PathParam("profile") String profile,@PathParam("document") String document)
   {
        return ejb.editDoctorProfile(doctorId, doctorName, specializaionId, experience, gender, certificates, education, email, contact, username, userId, profile, document);
   } 
   
//public long addSchedule(int hospitalId,int doctorId,Date date,Time fromTime,Time toTime,int numberOfPatients)
    @RolesAllowed("doctor")
    @POST
    @Path("/addSchedule/{hospitalId}/{doctorId}/{date}/{fromTime}/{toTime}/{numberOfPatients}")
    @Produces(MediaType.APPLICATION_JSON)
    public long addSchedule(@PathParam("hospitalId") int hospitalId,@PathParam("doctorId") int doctorId,@PathParam("date") Date date,@PathParam("fromTime") Time fromTime,@PathParam("toTime") Time toTime,@PathParam("numberOfPatients") int numberOfPatients)
    {
       return ejb.addSchedule(hospitalId, doctorId, date, fromTime, toTime, numberOfPatients);
    }
   
//public Collection<DoctorScheduleTb> getDoctorSchedule(int doctorId)
    @GET
    @Path("/getDoctorSchedule/{doctorId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<DoctorScheduleTb> getDoctorSchedule(@PathParam("doctorId") int doctorId)
    {
        return ejb.getDoctorSchedule(doctorId);
    }
    
//public int addAttachment(int doctorId,int patientId,String attachment)
    @RolesAllowed("doctor")
    @POST
    @Path("/addAttachment/{doctorId}/{patientId}/{attachment}")
    @Produces(MediaType.APPLICATION_JSON)
    public int addAttachment(@PathParam("doctorId") int doctorId,@PathParam("patientId") int patientId,@PathParam("attachment") String attachment)
    {
       return ejb.addAttachment(doctorId, patientId, attachment);
    }
    
//public Collection<DoctorAttachmentTb> viewAttachmentOfDoctor(int doctorId)
    @GET
    @Path("/viewAttachmentOfDoctor/{doctorId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<DoctorAttachmentTb> viewAttachmentOfDoctor(@PathParam("doctorId") int doctorId)
    {
        return ejb.viewAttachmentOfDoctor(doctorId);
    }
    
//public Collection<DoctorAttachmentTb> viewAttachmentOfPatient(int patientId)
    @GET
    @Path("/viewAttachmentOfPatient/{patientId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<DoctorAttachmentTb> viewAttachmentOfPatient(@PathParam("patientId") int patientId)
    {
        return ejb.viewAttachmentOfPatient(patientId);
    }
    
//public DoctorTb getDoctorById(int doctorId)
    @GET
    @Path("/getDoctorById/{doctorId}")
    @Produces(MediaType.APPLICATION_JSON)
    public DoctorTb getDoctorById(@PathParam("doctorId") int doctorId)
    {
        return ejb.getDoctorById(doctorId);
    }
    
//public void changePassword(String email,String password)
    @GET
    @Path("/changePassword/{email}/{password}")
    @Produces(MediaType.APPLICATION_JSON)
    public void changePassword(@PathParam("email") String email,@PathParam("password") String password)
    {
        ejb.changePassword(email, password);
    }
    
//public Collection<DoctorScheduleTb> getScheduleByHospitalAndDoctorId(int hid,int did)
    @GET
    @Path("/getScheduleByHospitalAndDoctorId/{hid}/{did}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<DoctorScheduleTb> getScheduleByHospitalAndDoctorId(@PathParam("hid") int hid,@PathParam("did") int did)
    {
        return ejb.getScheduleByHospitalAndDoctorId(hid, did);
    } 
    
//public Collection<DoctorTb> getScheduleByHospital(int hid)
    @GET
    @Path("/getScheduleByHospital/{hid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<DoctorTb> getScheduleByHospital(@PathParam("hid") int hid)
    {
        return ejb.getScheduleByHospital(hid);
    }
}
