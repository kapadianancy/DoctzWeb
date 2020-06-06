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
@Path("hospitalRest")
//@RequestScoped
@DeclareRoles({"admin","patient","doctor","hospital"})
public class HospitalRestResource {

    @EJB doctzBeanLocal ejb;
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of HospitalRestResource
     */
    
    public HospitalRestResource() {
    }

    
//  public Collection<CityTb> getAllCity(); 
    @GET
    @Path("/getAllCity")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<CityTb> getAllCity()
    {
        return ejb.getAllCity();
    }
    
//  public Collection<AreaTb> getAllArea();
    @GET
    @Path("/getAllArea")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<AreaTb> getAllArea()
    {
        return ejb.getAllArea();
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
    
//public int addFees(int hospitalId,int specializaionId,int fees)
    @RolesAllowed("hospital")
    @POST
    @Path("/addFees/{hospitalId}/{specializaionId}/{fees}")
    @Produces(MediaType.APPLICATION_JSON)
    public int addFees(@PathParam("hospitalId") int hospitalId,@PathParam("specializaionId") int specializaionId,@PathParam("fees") int fees)
    {
        return ejb.addFees(hospitalId, specializaionId, fees);
    }
    
//public int updateFees(int feesId,int hospitalId,int specializaionId,int fees)
    @RolesAllowed("hospital")
    @POST
    @Path("/updateFees/{feesId}/{hospitalId}/{specializaionId}/{fees}")
    @Produces(MediaType.APPLICATION_JSON)
    public int updateFees(@PathParam("feesId") int feesId,@PathParam("hospitalId") int hospitalId,@PathParam("specializaionId") int specializaionId,@PathParam("fees") int fees)
    {
        return ejb.updateFees(feesId, hospitalId, specializaionId, fees);
    }
    
    
//public Collection<FeesTb> getFeesByHospital(int hospitalId)  
    @GET
    @Path("/getFeesByHospital/{hospitalId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<FeesTb> getFeesByHospital(@PathParam("hospitalId") int hospitalId)  
    {
        return ejb.getFeesByHospital(hospitalId);
    }
    
//public Collection<FeesTb> getFeesBySpeciality(int specializaionId) 
    @GET
    @Path("/getFeesBySpeciality/{specializaionId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<FeesTb> getFeesBySpeciality(@PathParam("specializaionId") int specializaionId)  
    {
        return ejb.getFeesBySpeciality(specializaionId);
    }
    
//public Collection<FeesTb> getFeesBySpecialityandHospital(int specializaionId,int hospitalId)  
    @GET
    @Path("/getFeesBySpecialityandHospital/{specializaionId}/{hospitalId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<FeesTb> getFeesBySpecialityandHospital(@PathParam("specializaionId") int specializaionId,@PathParam("hospitalId") int hospitalId)
    {
        return ejb.getFeesBySpecialityandHospital(specializaionId, hospitalId);
    }
    
//public int deleteFees(int feesId)
    @RolesAllowed("hospital")
    @DELETE
    @Path("/deleteFees/{feesId}")
    @Produces(MediaType.APPLICATION_JSON)
    public int deleteFees(@PathParam("feesId") int feesId)
    {
        return ejb.deleteFees(feesId);
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
    
//public Collection<AppointmentTb> getAllAppointment()
    @GET
    @Path("/getAllAppointment")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<AppointmentTb> getAllAppointment() 
    {
        return ejb.getAllAppointment();
    }
    
//public Collection<PatientTb> getPatientOfHospital(int hospitalId)
    @GET
    @Path("/getPatientOfHospital/{hospitalId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<PatientTb> getPatientOfHospital(@PathParam("hospitalId") int hospitalId) 
    {
        return ejb.getPatientOfHospital(hospitalId);
    }
    
//public Collection<DoctorTb> getDoctorOfHospital(int hospitalId)
    @GET
    @Path("/getDoctorOfHospital/{hospitalId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<DoctorTb> getDoctorOfHospital(@PathParam("hospitalId") int hospitalId) 
    {
        return ejb.getDoctorOfHospital(hospitalId);
    }
    
//public int editHospitalProfile(int hospitalId,String hospitalName,String address,int areaId,int cityId,int pincode,double latitude,double longitude,String maplink,Time openingTime,Time closingTime,String logo,String email,long contact,String username,int userId)
    @RolesAllowed("hospital")
    @POST
    @Path("/editHospitalProfile/{hospitalId}/{hospitalName}/{address}/{areaId}/{cityId}/{pincode}/{latitude}/{longitude}/{openingTime}/{closingTime}/{logo}/{email}/{contact}/{username}/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public int editHospitalProfile(@PathParam("hospitalId") int hospitalId,@PathParam("hospitalName") String hospitalName,@PathParam("address") String address,@PathParam("areaId") int areaId,@PathParam("cityId") int cityId,@PathParam("pincode") int pincode,@PathParam("latitude") double latitude,@PathParam("longitude") double longitude,@PathParam("openingTime") Time openingTime,@PathParam("closingTime") Time closingTime,@PathParam("logo") String logo,@PathParam("email") String email,@PathParam("contact") long contact,@PathParam("username") String username,@PathParam("userId") int userId)
    {
       return ejb.editHospitalProfile(hospitalId, hospitalName, address, areaId, cityId, pincode, latitude, longitude, openingTime, closingTime, logo, email, contact, username, userId);
    }

//public long addSchedule(int hospitalId,int doctorId,Date date,Time fromTime,Time toTime,int numberOfPatients)
    @RolesAllowed("hospital")
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
   
//public Collection<DoctorTb> getDoctorBySpecializationName (String specializationName)
    @GET
    @Path("/getDoctorBySpecializationName/{specializationName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<DoctorTb> getDoctorBySpecializationName (@PathParam("specializationName") String specializationName)
    {
        return ejb.getDoctorBySpecializationName(specializationName);
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
    
//public void decreaseTotalPatient(int did,int hid,Date date,Time time)
    @GET
    @Path("/decreaseTotalPatient/{did}/{hid}/{date}/{time}")
    @Produces(MediaType.APPLICATION_JSON)
    public void decreaseTotalPatient(@PathParam("did") int did,@PathParam("hid") int hid,@PathParam("date") Date date,@PathParam("time") Time time)
    {
        ejb.decreaseTotalPatient(did, hid, date, time);
    }
    
    
//public void changePassword(String email,String password)
    @GET
    @Path("/changePassword/{email}/{password}")
    @Produces(MediaType.APPLICATION_JSON)
    public void changePassword(@PathParam("email") String email,@PathParam("password") String password)
    {
        ejb.changePassword(email, password);
    }
    
 // public HospitalTb getHospitalById(int hospitalId)
    @GET
    @Path("/getHospitalById/{hospitalId}")
    @Produces(MediaType.APPLICATION_JSON)
    public HospitalTb getHospitalById(@PathParam("hospitalId") int hospitalId)
    {
        return ejb.getHospitalById(hospitalId);
    }
    
}
