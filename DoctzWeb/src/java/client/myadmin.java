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
 * Jersey REST client generated for REST resource:adminRest [adminRest]<br>
 * USAGE:
 * <pre>
 *        myadmin client = new myadmin();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author Admin
 */
public class myadmin {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8001/DoctzWeb/webresources";

    public myadmin(String token) {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        client.register(new RestFilter(token));
        webTarget = client.target(BASE_URI).path("adminRest");
    }
    
    public myadmin()
    {
      client = javax.ws.rs.client.ClientBuilder.newClient();
      webTarget = client.target(BASE_URI).path("adminRest");
    }

    public <T> T updateState(Class<T> responseType, String stateId, String stateName) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("updateState/{0}/{1}", new Object[]{stateId, stateName})).request().post(null, responseType);
    }

    public <T> T updateCity(Class<T> responseType, String cityId, String cityName, String stateId) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("updateCity/{0}/{1}/{2}", new Object[]{cityId, cityName, stateId})).request().post(null, responseType);
    }

    public <T> T verifyHospital(Class<T> responseType, String hospitalId) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("verifyHospital/{0}", new Object[]{hospitalId})).request().post(null, responseType);
    }

    public <T> T updateArea(Class<T> responseType, String areaId, String areaName, String cityId, String pincode, String latitude, String longitude) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("updateArea/{0}/{1}/{2}/{3}/{4}/{5}", new Object[]{areaId, areaName, cityId, pincode, latitude, longitude})).request().post(null, responseType);
    }

    public <T> T getAllDoctor(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("getAllDoctor");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getAllState(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("getAllState");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getSpecializationById(Class<T> responseType, String specializaionId) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getSpecializationById/{0}", new Object[]{specializaionId}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T updateSpecialization(Class<T> responseType, String specializaionId, String name, String description, String image) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("updateSpecialization/{0}/{1}/{2}/{3}", new Object[]{specializaionId, name, description, image})).request().post(null, responseType);
    }

    public <T> T addSpecialization(Class<T> responseType, String name, String description, String image) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("addSpecialization/{0}/{1}/{2}", new Object[]{name, description, image})).request().post(null, responseType);
    }

    public <T> T getAllAppointment(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("getAllAppointment");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T addCity(Class<T> responseType, String cityName, String stateId) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("addCity/{0}/{1}", new Object[]{cityName, stateId})).request().post(null, responseType);
    }

    public <T> T addArea(Class<T> responseType, String areaName, String cityId, String pincode, String latitude, String longitude) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("addArea/{0}/{1}/{2}/{3}/{4}", new Object[]{areaName, cityId, pincode, latitude, longitude})).request().post(null, responseType);
    }

    public <T> T getAllCity(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("getAllCity");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T deleteCity(Class<T> responseType, String cityId) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("deleteCity/{0}", new Object[]{cityId})).request().delete(responseType);
    }

    public <T> T getAllSpecialization(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("getAllSpecialization");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T deleteSpecialization(Class<T> responseType, String specializaionId) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("deleteSpecialization/{0}", new Object[]{specializaionId})).request().delete(responseType);
    }

    public <T> T verifyDoctor(Class<T> responseType, String doctorId) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("verifyDoctor/{0}", new Object[]{doctorId})).request().post(null, responseType);
    }

    public <T> T deleteArea(Class<T> responseType, String areaId) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("deleteArea/{0}", new Object[]{areaId})).request().delete(responseType);
    }

    public <T> T getAllHospital(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("getAllHospital");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T addState(Class<T> responseType, String stateName) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("addState/{0}", new Object[]{stateName})).request().post(null, responseType);
    }

    public <T> T getAllArea(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("getAllArea");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T deleteState(Class<T> responseType, String stateId) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("deleteState/{0}", new Object[]{stateId})).request().delete(responseType);
    }

    public void close() {
        client.close();
    }
    
}
