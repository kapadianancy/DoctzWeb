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
 * Jersey REST client generated for REST resource:HospitalRestResource
 * [hospitalRest]<br>
 * USAGE:
 * <pre>
 *        myhospital client = new myhospital();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author ADMIN
 */
public class myhospital {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8001/DoctzWeb/webresources";
    
    public myhospital(String token) {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        client.register(new RestFilter(token));
        webTarget = client.target(BASE_URI).path("hospitalRest");
    }

    public myhospital() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("hospitalRest");
    }

    public <T> T updateFees(Class<T> responseType, String feesId, String hospitalId, String specializaionId, String fees) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("updateFees/{0}/{1}/{2}/{3}", new Object[]{feesId, hospitalId, specializaionId, fees})).request().post(null, responseType);
    }

    public <T> T getDoctorOfHospital(Class<T> responseType, String hospitalId) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getDoctorOfHospital/{0}", new Object[]{hospitalId}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T addSchedule(Class<T> responseType, String hospitalId, String doctorId, String date, String fromTime, String toTime, String numberOfPatients) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("addSchedule/{0}/{1}/{2}/{3}/{4}/{5}", new Object[]{hospitalId, doctorId, date, fromTime, toTime, numberOfPatients})).request().post(null, responseType);
    }

    public <T> T bookAppointment(Class<T> responseType, String doctorId, String patientId, String hospitalId, String date, String time) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("bookAppointment/{0}/{1}/{2}/{3}/{4}", new Object[]{doctorId, patientId, hospitalId, date, time})).request().post(null, responseType);
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

    public <T> T editHospitalProfile(Class<T> responseType, String hospitalId, String hospitalName, String address, String areaId, String cityId, String pincode, String latitude, String longitude,String openingTime, String closingTime, String logo, String email, String contact, String username, String userId) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("editHospitalProfile/{0}/{1}/{2}/{3}/{4}/{5}/{6}/{7}/{8}/{9}/{10}/{11}/{12}/{13}/{14}", new Object[]{hospitalId, hospitalName, address, areaId, cityId, pincode, latitude, longitude, openingTime, closingTime, logo, email, contact, username, userId})).request().post(null, responseType);
    }

    public <T> T getScheduleByHospitalAndDoctorId(Class<T> responseType, String hid, String did) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getScheduleByHospitalAndDoctorId/{0}/{1}", new Object[]{hid, did}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getHospitalById(Class<T> responseType, String hospitalId) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getHospitalById/{0}", new Object[]{hospitalId}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getAllHospital(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("getAllHospital");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T deleteFees(Class<T> responseType, String feesId) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("deleteFees/{0}", new Object[]{feesId})).request().delete(responseType);
    }

    public <T> T cancelAppointment(Class<T> responseType, String appointmentId) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("cancelAppointment/{0}", new Object[]{appointmentId})).request().delete(responseType);
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

    public <T> T decreaseTotalPatient(Class<T> responseType, String did, String hid, String date, String time) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("decreaseTotalPatient/{0}/{1}/{2}/{3}", new Object[]{did, hid, date, time}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getFeesBySpecialityandHospital(Class<T> responseType, String specializaionId, String hospitalId) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getFeesBySpecialityandHospital/{0}/{1}", new Object[]{specializaionId, hospitalId}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getAllAppointment(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("getAllAppointment");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T addFees(Class<T> responseType, String hospitalId, String specializaionId, String fees) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("addFees/{0}/{1}/{2}", new Object[]{hospitalId, specializaionId, fees})).request().post(null, responseType);
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

    public <T> T getDoctorBySpecializationName(Class<T> responseType, String specializationName) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getDoctorBySpecializationName/{0}", new Object[]{specializationName}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getFeesByHospital(Class<T> responseType, String hospitalId) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getFeesByHospital/{0}", new Object[]{hospitalId}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getAllDoctorAppointment(Class<T> responseType, String hospitalId, String doctorId) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getAllDoctorAppointment/{0}/{1}", new Object[]{hospitalId, doctorId}));
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

    public <T> T getAllArea(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("getAllArea");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void close() {
        client.close();
    }
    
}
