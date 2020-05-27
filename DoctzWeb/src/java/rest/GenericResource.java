/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import beans.doctzBeanLocal;
import entity.*;
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
@Path("generic")
//@RequestScoped
@DeclareRoles({"admin","patient","doctor","hospital"})
public class GenericResource {

    @EJB doctzBeanLocal ejb;
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GenericResource
     */
    public GenericResource() {
    }
    
//  public Collection<StateTb> getAllState();
   // @RolesAllowed({"admin","patient"})
    @GET
    @Path("/getAllState")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<StateTb> getAllState() {
        return ejb.getAllState();
    }

//  public Collection<CityTb> getAllCity(); 
   // @RolesAllowed({"admin","patient"})
    @GET
    @Path("/getAllCity")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<CityTb> getAllCity()
    {
        return ejb.getAllCity();
    }
    
//  public Collection<AreaTb> getAllArea();
   // @RolesAllowed({"admin","patient"})
    @GET
    @Path("/getAllArea")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<AreaTb> getAllArea()
    {
        return ejb.getAllArea();
    }
    
//  public int addUser(String userName,String password,String email,long contact);
    @RolesAllowed({"admin","patient"})
    @POST
    @Path("/addUser/{userName}/{password}/{email}/{contact}")
    @Produces(MediaType.APPLICATION_JSON)
    public int addUser(@PathParam("userName") String userName,@PathParam("password") String password,@PathParam("email") String email,@PathParam("contact") long contact)
    {
        return ejb.addUser(userName, password, email, contact);
    }
    
//  public int addUserGroup(int userId,int groupId);
    @RolesAllowed({"admin","patient"})
    @POST
    @Path("/addUserGroup/{userId}/{groupId}")
    @Produces(MediaType.APPLICATION_JSON)
    public int addUserGroup(@PathParam("userId") int userId,@PathParam("groupId") int groupId)
    {
        return ejb.addUserGroup(userId, groupId);
    }
   
   
//  public int authenticateUser(String email,long contact);
    @RolesAllowed({"admin","patient"})
    @POST
    @Path("/authenticateUser/{email}/{contact}")
    @Produces(MediaType.APPLICATION_JSON)
    public int authenticateUser(@PathParam("email") String email,@PathParam("contact") long contact)
    {
        return ejb.authenticateUser(email, contact);
    }
    
    
//  public int patientRegistration(String patientName,String gender,String address,int age,String username,String password,String email,long contact);
    //@RolesAllowed({"admin","patient"})
    @POST
    @Path("/patientRegistration/{patientName}/{gender}/{address}/{age}/{username}/{password}/{email}/{contact}")
    @Produces(MediaType.APPLICATION_JSON)
    public int patientRegistration(@PathParam("patientName") String patientName,@PathParam("gender") String gender,@PathParam("address") String address,@PathParam("age") int age,@PathParam("username") String username,@PathParam("password") String password,@PathParam("email") String email,@PathParam("contact") long contact)
    {
        
        return ejb.patientRegistration(patientName, gender, address, age, username, password, email, contact);
    }
    
//  public Collection<SpecializationTb> getAllSpecialization();
    //@RolesAllowed({"admin","patient"})
    @GET
    @Path("/getAllSpecialization")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<SpecializationTb> getAllSpecialization()
    {
        return ejb.getAllSpecialization();
    }
    
//  public Collection<SpecializationTb> getSpecializationByParentId(int parentSpecializationId);
    @RolesAllowed({"admin","patient"})
    @GET
    @Path("/getSpecializationByParentId/{parentSpecializationId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<SpecializationTb> getSpecializationByParentId(@PathParam("parentSpecializationId") int parentSpecializationId)
    {
        return ejb.getSpecializationByParentId(parentSpecializationId);
    }
    
    
//  public Collection<FeesTb> getFeesByHospital(int hospitalId);
  //  @RolesAllowed({"admin","patient"})
    @GET
    @Path("/getFeesByHospital/{hospitalId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<FeesTb> getFeesByHospital(@PathParam("hospitalId") int hospitalId)
    {
        return ejb.getFeesByHospital(hospitalId);
    }
    
//  public Collection<FeesTb> getFeesBySpeciality(int specializaionId);  
  //  @RolesAllowed({"admin","patient"})
    @GET
    @Path("/getFeesBySpeciality/{specializaionId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<FeesTb> getFeesBySpeciality(@PathParam("specializaionId") int specializaionId)
    {
        return ejb.getFeesBySpeciality(specializaionId);
    }
    
//  public int addReview(int patientId,int doctorId,int hospitalId,String review);
    //@RolesAllowed("patient")
    @POST
    @Path("/addReview/{patientId}/{doctorId}/{hospitalId}/{review}")
    @Produces(MediaType.APPLICATION_JSON)
    public int addReview(@PathParam("patientId") int patientId,@PathParam("doctorId") int doctorId,@PathParam("hospitalId") int hospitalId,@PathParam("review") String review)
    {
        return ejb.addReview(patientId, doctorId, hospitalId, review);
    }
    
//  public Collection<ReviewTb> getAllReview();
   // @RolesAllowed({"admin","patient"})
    @GET
    @Path("/getAllReview")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<ReviewTb> getAllReview(){
        return ejb.getAllReview();
    }
    
//  public int bookAppointment(int doctorId,int patientId,int hospitalId,Date date,Time time,String amPm);
    @RolesAllowed("patient")
    @POST
    @Path("/bookAppointment/{doctorId}/{patientId}/{hospitalId}/{date}/{time}")
    @Produces(MediaType.APPLICATION_JSON)
    public int bookAppointment(@PathParam("doctorId") int doctorId,@PathParam("patientId") int patientId,@PathParam("hospitalId") int hospitalId,@PathParam("date") Date date,@PathParam("time") Time time){
        return ejb.bookAppointment(doctorId, patientId, hospitalId, date, time);
    }
    
//  public int cancelAppointment(int appointmentId);
    @RolesAllowed({"admin","patient"})
    @DELETE
    @Path("/cancelAppointment/{appointmentId}")
    @Produces(MediaType.APPLICATION_JSON)
    public int cancelAppointment(@PathParam("appointmentId") int appointmentId) 
    {
        return ejb.cancelAppointment(appointmentId);
    }
    
    
//  public Collection<AppointmentTb> getAllDoctorAppointment(int hospitalId,int doctorId);
    @RolesAllowed({"admin","patient"})
    @GET
    @Path("/getAllDoctorAppointment/{hospitalId}/{doctorId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<AppointmentTb> getAllDoctorAppointment(@PathParam("hospitalId") int hospitalId,@PathParam("doctorId") int doctorId)
    {
        return ejb.getAllDoctorAppointment(hospitalId, doctorId);
    }
    
//  public Collection<AppointmentTb> getAllPatientAppointment(int patientId);
    @RolesAllowed({"admin","patient"})
    @GET
    @Path("/getAllPatientAppointment/{patientId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<AppointmentTb> getAllPatientAppointment(@PathParam("patientId") int patientId)
    {
        return ejb.getAllPatientAppointment(patientId);
        
    }
    
//  public Collection<HospitalTb> getHospitalByArea(String areaName);
    //@RolesAllowed({"admin","patient"})
    @GET
    @Path("/getHospitalByArea/{areaName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<HospitalTb> getHospitalByArea(@PathParam("areaName") String areaName)
    {
        return ejb.getHospitalByArea(areaName);
        
    }
    
//  public Collection<DoctorTb> getDoctorByArea(String areaName);
   // @RolesAllowed({"admin","patient"})
    @GET
    @Path("/getDoctorByArea/{areaName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<DoctorTb> getDoctorByArea(@PathParam("areaName") String areaName)
    {
        return ejb.getDoctorByArea(areaName);
        
    }
    
//  public Collection<HospitalTb> getHospitalBySpecialization(int specializaionId);
   // @RolesAllowed({"admin","patient"})
    @GET
    @Path("/getHospitalBySpecialization/{specializaionId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<HospitalTb> getHospitalBySpecialization(@PathParam("specializaionId") int specializaionId)
    {
        return ejb.getHospitalBySpecialization(specializaionId);  
    }
    
//  public Collection<DoctorTb> getDoctorOfHospital(int hospitalId);
    //@RolesAllowed({"admin","patient"})
    @GET
    @Path("/getDoctorOfHospital/{hospitalId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<DoctorTb> getDoctorOfHospital(@PathParam("hospitalId") int hospitalId)
    {
        return ejb.getDoctorOfHospital(hospitalId);
        
    }
    
//  public Collection<DoctorTb> getDoctorBySpecialization(int specializaionId);
   // @RolesAllowed({"admin","patient"})
    @GET
    @Path("/getDoctorBySpecialization/{specializaionId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<DoctorTb> getDoctorBySpecialization(@PathParam("specializaionId") int specializaionId)
    {
        return ejb.getDoctorBySpecialization(specializaionId);
        
    }
    
//  public Collection<HospitalTb> getHospitalByName(String hospitalName);
   // @RolesAllowed({"admin","patient"})
    @GET
    @Path("/getHospitalByName/{hospitalName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<HospitalTb> getHospitalByName(@PathParam("hospitalName") String hospitalName)
    {
        return ejb.getHospitalByName(hospitalName); 
    }
   
//  public Collection<DoctorTb> getDoctorByName(String doctorName);
   // @RolesAllowed({"admin","patient"})
    @GET
    @Path("/getDoctorByName/{doctorName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<DoctorTb> getDoctorByName(@PathParam("doctorName") String doctorName)
    {
        return ejb.getDoctorByName(doctorName); 
    }
    
//  public Collection<HospitalTb> getHospitalByFeesAndSpecialization(int specializaionId,int fromFees,int toFees);
   // @RolesAllowed({"admin","patient"})
    @GET
    @Path("/getHospitalByFeesAndSpecialization/{specializaionId}/{fromFees}/{toFees}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<HospitalTb> getHospitalByFeesAndSpecialization(@PathParam("specializaionId") int specializaionId,@PathParam("fromFees") int fromFees,@PathParam("toFees") int toFees)
    {
        return ejb.getHospitalByFeesAndSpecialization(specializaionId, fromFees, toFees);
    }
    
//  public Collection<HospitalTb> emergency(int specializaionId);
  //  @RolesAllowed({"admin","patient"})
    @GET
    @Path("/emergency/{specializaionId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<HospitalTb> emergency(@PathParam("specializaionId") int specializaionId)
    {
        return ejb.emergency(specializaionId);
    }
    
//  public Collection<HospitalTb> getHospitalByCity(String cityName);
  //  @RolesAllowed({"admin","patient"})
    @GET
    @Path("/getHospitalByCity/{cityName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<HospitalTb> getHospitalByCity(@PathParam("cityName") String cityName)
    {
        return ejb.getHospitalByCity(cityName);
    }
    
//  public Collection<HospitalTb> getHospitalByState(String stateName);
  //  @RolesAllowed({"admin","patient"})
    @GET
    @Path("/getHospitalByState/{stateName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<HospitalTb> getHospitalByState(@PathParam("stateName") String stateName)
    {
        return ejb.getHospitalByState(stateName);
    }
    
//  public Collection<DoctorTb> getDoctorByExperience();
  //  @RolesAllowed({"admin","patient"})
    @GET
    @Path("/getDoctorByExperience}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<DoctorTb> getDoctorByExperience()
    {
        return ejb.getDoctorByExperience();
    }
    
//  public Collection<HospitalTb> getHospitalByLowToHighFees(String spcializationName);
  //  @RolesAllowed({"admin","patient"})
    @GET
    @Path("/getHospitalByLowToHighFees/{spcializationName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<HospitalTb> getHospitalByLowToHighFees(@PathParam("spcializationName") String spcializationName)
    {
        return ejb.getHospitalByLowToHighFees(spcializationName);
    }
    
//  public Collection<HospitalTb> getHospitalByHighToLowFees(String spcializationName);
  //  @RolesAllowed({"admin","patient"})
    @GET
    @Path("/getHospitalByHighToLowFees/{spcializationName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<HospitalTb> getHospitalByHighToLowFees(@PathParam("spcializationName") String spcializationName)
    {
        return ejb.getHospitalByHighToLowFees(spcializationName);
    }
    
//  public Collection<HospitalTb> getHospitalByFees(String name,int fromFees,int toFees);
  //  @RolesAllowed({"admin","patient"})
    @GET
    @Path("/getHospitalByFees/{name}/{fromFees}/{toFees}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<HospitalTb> getHospitalByFees(@PathParam("name") String name,@PathParam("fromFees") int fromFees,@PathParam("toFees") int toFees)
    {
        return ejb.getHospitalByFees(name, fromFees, toFees);
    }
    
//  public Collection<DoctorTb> getDoctorByAvailability(Date date);
  //  @RolesAllowed({"admin","patient"})
    @GET
    @Path("/getDoctorByAvailability/{date}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<DoctorTb> getDoctorByAvailability(@PathParam("date") Date date)
    {
        return ejb.getDoctorByAvailability(date);
    }
    
//  public int editPatientProfile(int patientId,String patientName,String gender,String address,int age,String username,String email,long contact,int userId);
    @RolesAllowed({"admin","patient"})
    @POST
    @Path("/editPatientProfile/{patientId}/{patientName}/{gender}/{address}/{age}/{username}/{email}/{contact}/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public int editPatientProfile(@PathParam("patientId") int patientId,@PathParam("patientName")  String patientName,@PathParam("gender") String gender,@PathParam("address") String address,@PathParam("age") int age,@PathParam("username") String username,@PathParam("email") String email,@PathParam("contact") long contact,@PathParam("userId") int userId)
    {
        return ejb.editPatientProfile(patientId, patientName, gender, address, age, username, email, contact, userId);
    }

//  public Collection<DoctorAttachmentTb> viewAttachmentOfPatient(int patientId);
    @RolesAllowed({"admin","patient"})
    @GET
    @Path("/viewAttachmentOfPatient/{patientId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<DoctorAttachmentTb> viewAttachmentOfPatient(@PathParam("patientId") int patientId)
    {
        return ejb.viewAttachmentOfPatient(patientId);
    }
    
 //  public Collection<DoctorTb> getAllDoctor();  
    //@RolesAllowed({"admin","patient"})
    @GET
    @Path("/getAllDoctor")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<DoctorTb> getAllDoctor()
    {
        return ejb.getAllDoctor();
    }
    
 // public Collection<HospitalTb> getAllHospital();
  //  @RolesAllowed({"admin","patient"})
    @GET
    @Path("/getAllHospital")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<HospitalTb> getAllHospital()
    {
        return ejb.getAllHospital();
    }
    
    
    
    
}
