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
 * Jersey REST client generated for REST resource:DoctorRestResource
 * [doctorRest]<br>
 * USAGE:
 * <pre>
 *        mydoctor client = new mydoctor();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author ADMIN
 */
public class mydoctor {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8001/DoctzWeb/webresources";

     public mydoctor(String token) {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        client.register(new RestFilter(token));
        webTarget = client.target(BASE_URI).path("doctorRest");
    }
    
    public mydoctor() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("doctorRest");
    }

    public <T> T cancelAppointment(Class<T> responseType, String appointmentId) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("cancelAppointment/{0}", new Object[]{appointmentId})).request().delete(responseType);
    }

    public <T> T viewAttachmentOfPatient(Class<T> responseType, String patientId) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("viewAttachmentOfPatient/{0}", new Object[]{patientId}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T editDoctorProfile(Class<T> responseType, String doctorId, String doctorName, String specializaionId, String experience, String gender, String certificates, String education, String email, String contact, String username, String userId, String profile) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("editDoctorProfile/{0}/{1}/{2}/{3}/{4}/{5}/{6}/{7}/{8}/{9}/{10}/{11}", new Object[]{doctorId, doctorName, specializaionId, experience, gender, certificates, education, email, contact, username, userId, profile})).request().post(null, responseType);
    }

    public <T> T getAllPatient(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("getAllPatient");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getAllDoctor(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("getAllDoctor");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getPatientOfHospital(Class<T> responseType, String hospitalId) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getPatientOfHospital/{0}", new Object[]{hospitalId}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getFeesBySpecialityandHospital(Class<T> responseType, String specializaionId, String hospitalId) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getFeesBySpecialityandHospital/{0}/{1}", new Object[]{specializaionId, hospitalId}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T addSchedule(Class<T> responseType, String hospitalId, String doctorId, String date, String fromTime, String toTime, String numberOfPatients) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("addSchedule/{0}/{1}/{2}/{3}/{4}/{5}", new Object[]{hospitalId, doctorId, date, fromTime, toTime, numberOfPatients})).request().post(null, responseType);
    }

    public <T> T addAttachment(Class<T> responseType, String doctorId, String patientId, String attachment) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("addAttachment/{0}/{1}/{2}", new Object[]{doctorId, patientId, attachment})).request().post(null, responseType);
    }

    public <T> T bookAppointment(Class<T> responseType, String doctorId, String patientId, String hospitalId, String date, String time) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("bookAppointment/{0}/{1}/{2}/{3}/{4}", new Object[]{doctorId, patientId, hospitalId, date, time})).request().post(null, responseType);
    }

    public <T> T changePassword(Class<T> responseType, String email, String password) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("changePassword/{0}/{1}", new Object[]{email, password}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getAllSpecialization(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("getAllSpecialization");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getDoctorById(Class<T> responseType, String doctorId) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getDoctorById/{0}", new Object[]{doctorId}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getScheduleByHospitalAndDoctorId(Class<T> responseType, String hid, String did) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getScheduleByHospitalAndDoctorId/{0}/{1}", new Object[]{hid, did}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getPatientOfDoctor(Class<T> responseType, String doctorId) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getPatientOfDoctor/{0}", new Object[]{doctorId}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getAllDoctorAppointment(Class<T> responseType, String hospitalId, String doctorId) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getAllDoctorAppointment/{0}/{1}", new Object[]{hospitalId, doctorId}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T viewAttachmentOfDoctor(Class<T> responseType, String doctorId) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("viewAttachmentOfDoctor/{0}", new Object[]{doctorId}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getAllPatientAppointment(Class<T> responseType, String patientId) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getAllPatientAppointment/{0}", new Object[]{patientId}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getAllHospital(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("getAllHospital");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getDoctorSchedule(Class<T> responseType, String doctorId) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getDoctorSchedule/{0}", new Object[]{doctorId}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getScheduleByHospital(Class<T> responseType, String hid) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getScheduleByHospital/{0}", new Object[]{hid}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void close() {
        client.close();
    }
    
}
