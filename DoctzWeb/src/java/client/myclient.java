/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

/**
 * Jersey REST client generated for REST resource:GenericResource [generic]<br>
 * USAGE:
 * <pre>
 *        myclient client = new myclient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author Admin
 */
public class myclient {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8001/DoctzWeb/webresources";

   
    
     public myclient(String token) {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        client.register(new RestFilter(token));
        webTarget = client.target(BASE_URI).path("generic");
    }
    
    public myclient()
    {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("generic");
    }

    public <T> T getDoctorOfHospital(Class<T> responseType, String hospitalId) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getDoctorOfHospital/{0}", new Object[]{hospitalId}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T viewAttachmentOfPatient(Class<T> responseType, String patientId) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("viewAttachmentOfPatient/{0}", new Object[]{patientId}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getDoctorByArea(Class<T> responseType, String areaName) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getDoctorByArea/{0}", new Object[]{areaName}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getAllState(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("getAllState");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T addUser(Class<T> responseType, String userName, String password, String email, String contact) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("addUser/{0}/{1}/{2}/{3}", new Object[]{userName, password, email, contact})).request().post(null, responseType);
    }

    public <T> T getHospitalByLowToHighFees(Class<T> responseType, String spcializationName) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getHospitalByLowToHighFees/{0}", new Object[]{spcializationName}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getHospitalByState(Class<T> responseType, String stateName) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getHospitalByState/{0}", new Object[]{stateName}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T emergency(Class<T> responseType, String specializaionId) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("emergency/{0}", new Object[]{specializaionId}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getAllReview(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("getAllReview");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getHospitalByArea(Class<T> responseType, String areaName) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getHospitalByArea/{0}", new Object[]{areaName}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getHospitalByName(Class<T> responseType, String hospitalName) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getHospitalByName/{0}", new Object[]{hospitalName}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T bookAppointment(Class<T> responseType, String doctorId, String patientId, String hospitalId, String date, String time, String amPm) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("bookAppointment/{0}/{1}/{2}/{3}/{4}/{5}", new Object[]{doctorId, patientId, hospitalId, date, time, amPm})).request().post(null, responseType);
    }

    public <T> T getFeesBySpeciality(Class<T> responseType, String specializaionId) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getFeesBySpeciality/{0}", new Object[]{specializaionId}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getAllCity(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("getAllCity");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T editPatientProfile(Class<T> responseType, String patientId, String patientName, String gender, String address, String age, String username, String email, String contact, String userId) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("editPatientProfile/{0}/{1}/{2}/{3}/{4}/{5}/{6}/{7}/{8}", new Object[]{patientId, patientName, gender, address, age, username, email, contact, userId})).request().post(null, responseType);
    }

    public <T> T getAllHospital(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("getAllHospital");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getSpecializationByParentId(Class<T> responseType, String parentSpecializationId) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getSpecializationByParentId/{0}", new Object[]{parentSpecializationId}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T addReview(Class<T> responseType, String patientId, String doctorId, String hospitalId, String review) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("addReview/{0}/{1}/{2}/{3}", new Object[]{patientId, doctorId, hospitalId, review})).request().post(null, responseType);
    }

    public <T> T cancelAppointment(Class<T> responseType, String appointmentId) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("cancelAppointment/{0}", new Object[]{appointmentId})).request().delete(responseType);
    }

    public <T> T addUserGroup(Class<T> responseType, String userId, String groupId) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("addUserGroup/{0}/{1}", new Object[]{userId, groupId})).request().post(null, responseType);
    }

    public <T> T getDoctorByExperience(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("getDoctorByExperience}");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getAllDoctor(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("getAllDoctor");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getHospitalByFeesAndSpecialization(Class<T> responseType, String specializaionId, String fromFees, String toFees) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getHospitalByFeesAndSpecialization/{0}/{1}/{2}", new Object[]{specializaionId, fromFees, toFees}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getHospitalByCity(Class<T> responseType, String cityName) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getHospitalByCity/{0}", new Object[]{cityName}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getHospitalByFees(Class<T> responseType, String name, String fromFees, String toFees) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getHospitalByFees/{0}/{1}/{2}", new Object[]{name, fromFees, toFees}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getHospitalBySpecialization(Class<T> responseType, String specializaionId) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getHospitalBySpecialization/{0}", new Object[]{specializaionId}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getDoctorBySpecialization(Class<T> responseType, String specializaionId) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getDoctorBySpecialization/{0}", new Object[]{specializaionId}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getAllSpecialization(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("getAllSpecialization");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T authenticateUser(Class<T> responseType, String email, String contact) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("authenticateUser/{0}/{1}", new Object[]{email, contact})).request().post(null, responseType);
    }

    public <T> T getHospitalByHighToLowFees(Class<T> responseType, String spcializationName) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getHospitalByHighToLowFees/{0}", new Object[]{spcializationName}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getDoctorByAvailability(Class<T> responseType, String date) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getDoctorByAvailability/{0}", new Object[]{date}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T patientRegistration(Class<T> responseType, String patientName, String gender, String address, String age, String username, String password, String email, String contact) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("patientRegistration/{0}/{1}/{2}/{3}/{4}/{5}/{6}/{7}", new Object[]{patientName, gender, address, age, username, password, email, contact})).request().post(null, responseType);
    }

    public <T> T getFeesByHospital(Class<T> responseType, String hospitalId) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getFeesByHospital/{0}", new Object[]{hospitalId}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getDoctorByName(Class<T> responseType, String doctorName) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getDoctorByName/{0}", new Object[]{doctorName}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getAllDoctorAppointment(Class<T> responseType, String hospitalId, String doctorId) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getAllDoctorAppointment/{0}/{1}", new Object[]{hospitalId, doctorId}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getAllPatientAppointment(Class<T> responseType, String patientId) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getAllPatientAppointment/{0}", new Object[]{patientId}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getAllArea(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("getAllArea");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void close() {
        client.close();
    }
    
}
