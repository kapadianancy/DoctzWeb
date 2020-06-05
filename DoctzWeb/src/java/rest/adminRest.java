/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import beans.doctzBeanLocal;
import entity.*;
import java.util.Collection;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
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
@Path("adminRest")
//@RequestScoped
@DeclareRoles({"admin","patient","doctor","hospital"})
public class adminRest {

    @EJB doctzBeanLocal ejb;
    @Context
    private UriInfo context;

  
    public adminRest() {
    }

    //public int addState(String stateName);
    @RolesAllowed("admin")
    @POST
    @Path("/addState/{stateName}")
    @Produces(MediaType.APPLICATION_JSON)
    public int addState(@PathParam("stateName") String stateName)
    {
        return ejb.addState(stateName);
    }
    
    //public int updateState(int stateId,String stateName);
    @RolesAllowed("admin")
    @POST
    @Path("/updateState/{stateId}/{stateName}")
    @Produces(MediaType.APPLICATION_JSON)
    public int updateState(@PathParam("stateId") int stateId,@PathParam("stateName") String stateName)
    {
        return ejb.updateState(stateId, stateName);
    }
    
    //public int deleteState(int stateId);
    @RolesAllowed("admin")
    @DELETE
    @Path("/deleteState/{stateId}")
    @Produces(MediaType.APPLICATION_JSON)
    public int deleteState(@PathParam("stateId") int stateId)
    {
        return ejb.deleteState(stateId);
    }

    //  public Collection<StateTb> getAllState();
    @RolesAllowed("admin")
    @GET
    @Path("/getAllState")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<StateTb> getAllState() {
        return ejb.getAllState();
    }
    
    // public int addCity(String cityName,int stateId);
    @RolesAllowed("admin")
    @POST
    @Path("/addCity/{cityName}/{stateId}")
    @Produces(MediaType.APPLICATION_JSON)
    public int addCity(@PathParam("cityName") String cityName,@PathParam("stateId") int stateId)
    {
        return ejb.addCity(cityName, stateId);
    }
    
    //public int updateCity(int cityId,String cityName,int stateId);
    @RolesAllowed("admin")
    @POST
    @Path("/updateCity/{cityId}/{cityName}/{stateId}")
    @Produces(MediaType.APPLICATION_JSON)
    public int updateCity(@PathParam("cityId") int cityId,@PathParam("cityName") String cityName,@PathParam("stateId") int stateId)
    {
        return ejb.updateCity(cityId, cityName, stateId);
    }
    
    //public int deleteCity(int cityId);
    @RolesAllowed("admin")
    @DELETE
    @Path("/deleteCity/{cityId}")
    @Produces(MediaType.APPLICATION_JSON)
    public int deleteCity(@PathParam("cityId") int cityId)
    {
        return ejb.deleteCity(cityId);
    }
    
    //public Collection<CityTb> getAllCity(); 
    //@RolesAllowed("admin")
    @GET
    @Path("/getAllCity")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<CityTb> getAllCity()
    {
        return ejb.getAllCity();
    }
    
    //public int addArea(String areaName,int cityId,int pincode,double latitude,double longitude);
    @RolesAllowed("admin")
    @POST
    @Path("/addArea/{areaName}/{cityId}/{pincode}/{latitude}/{longitude}")
    @Produces(MediaType.APPLICATION_JSON)
    public int addArea(@PathParam("areaName") String areaName,@PathParam("cityId") int cityId,@PathParam("pincode") int pincode,@PathParam("latitude") double latitude,@PathParam("longitude") double longitude)
    {
        return ejb.addArea(areaName, cityId, pincode);
    }
    
    //public int updateArea(int areaId,String areaName,int cityId,int pincode,double latitude,double longitude);
    @RolesAllowed("admin")
    @POST
    @Path("/updateArea/{areaId}/{areaName}/{cityId}/{pincode}/{latitude}/{longitude}")
    @Produces(MediaType.APPLICATION_JSON)
    public int updateArea(@PathParam("areaId") int areaId,@PathParam("areaName") String areaName,@PathParam("cityId") int cityId,@PathParam("pincode") int pincode,@PathParam("latitude") double latitude,@PathParam("longitude") double longitude)
    {
        return ejb.updateArea(areaId, areaName, cityId, pincode);
    }
    
    //public int deleteArea(int areaId);
    @RolesAllowed("admin")
    @DELETE
    @Path("/deleteArea/{areaId}")
    @Produces(MediaType.APPLICATION_JSON)
    public int deleteArea(@PathParam("areaId") int areaId)
    {
        return ejb.deleteArea(areaId);
    }
    
    //public Collection<AreaTb> getAllArea();
    @RolesAllowed("admin")
    @GET
    @Path("/getAllArea")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<AreaTb> getAllArea()
    {
        return ejb.getAllArea();
    }

    //public Collection<HospitalTb> getAllHospital();
    @RolesAllowed("admin")
    @GET
    @Path("/getAllHospital")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<HospitalTb> getAllHospital()
    {
        return ejb.getAllHospital();
    }
    
    //public int verifyHospital(int hospitalId);
    @RolesAllowed("admin")
    @POST
    @Path("/verifyHospital/{hospitalId}/{username}/{password}")
    @Produces(MediaType.APPLICATION_JSON)
    public int verifyHospital(@PathParam("hospitalId") int hospitalId,@PathParam("username") String username,@PathParam("passwprd") String password)
    {
        return ejb.verifyHospital(hospitalId,username,password);
    }
    
    //public Collection<DoctorTb> getAllDoctor();
    @RolesAllowed("admin")
    @GET
    @Path("/getAllDoctor")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<DoctorTb> getAllDoctor()
    {
        return ejb.getAllDoctor();
    }
    
    //public int verifyDoctor(int doctorId);
    @RolesAllowed("admin")
    @POST
    @Path("/verifyDoctor/{doctorId}/{username}/{password}")
    @Produces(MediaType.APPLICATION_JSON)
    public int verifyDoctor(@PathParam("doctorId") int doctorId,@PathParam("username") String username,@PathParam("passwprd") String password)
    {
        return ejb.verifyDoctor(doctorId,username,password);
    }
    
    //public int addSpecialization(String name,String description,String image);
    @RolesAllowed("admin")
    @POST
    @Path("/addSpecialization/{name}/{description}/{image}")
    @Produces(MediaType.APPLICATION_JSON)
    public int addSpecialization(@PathParam("name") String name,@PathParam("description") String description,@PathParam("image") String image)
    {
        return ejb.addSpecialization(name, description, image);
    }
    
    //public int updateSpecialization(int specializaionId, String name,String description,String image);
    @RolesAllowed("admin")
    @POST
    @Path("/updateSpecialization/{specializaionId}/{name}/{description}/{image}")
    @Produces(MediaType.APPLICATION_JSON)
    public int updateSpecialization(@PathParam("specializaionId") int specializaionId,@PathParam("name") String name,@PathParam("description") String description,@PathParam("image") String image)
    {
        System.out.println(name);
        return ejb.updateSpecialization(specializaionId, name, description, image);
    }
    
    //public int deleteSpecialization(int specializaionId);
    @RolesAllowed("admin")
    @DELETE
    @Path("/deleteSpecialization/{specializaionId}")
    @Produces(MediaType.APPLICATION_JSON)
    public int deleteSpecialization(@PathParam("specializaionId") int specializaionId)
    {
        return ejb.deleteSpecialization(specializaionId);
    }
    
    //public Collection<SpecializationTb> getAllSpecialization();
    @RolesAllowed("admin")
    @GET
    @Path("/getAllSpecialization")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<SpecializationTb> getAllSpecialization()
    {
        return ejb.getAllSpecialization();
    }
    
    //public SpecializationTb getSpecializationById(int specializaionId);
    @RolesAllowed("admin")
    @GET
    @Path("/getSpecializationById/{specializaionId}")
    @Produces(MediaType.APPLICATION_JSON)
    public SpecializationTb getSpecializationById(@PathParam("specializaionId") int specializaionId)
    {
        return ejb.getSpecializationById(specializaionId);
    }
    
    //public Collection<AppointmentTb> getAllAppointment();
    @RolesAllowed("admin")
    @GET
    @Path("/getAllAppointment")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<AppointmentTb> getAllAppointment()
    {
        return ejb.getAllAppointment();
    }
    
}
