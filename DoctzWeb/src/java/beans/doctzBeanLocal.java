/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entity.*;
import java.sql.Time;
import java.util.Collection;
import java.util.Date;
import javax.ejb.Local;


/**
 *
 * @author Admin
 */
@Local
public interface doctzBeanLocal {
    // ---------------------------------state_tb---------------------------
        public int addState(String stateName);

        public int updateState(int stateId,String stateName);

        public int deleteState(int stateId);

        public Collection<StateTb> getAllState();
      
    //-----------------------------------city_tb----------------------

        public int addCity(String cityName,int stateId);

        public int updateCity(int cityId,String cityName,int stateId);

        public int deleteCity(int cityId);

        public Collection<CityTb> getAllCity();    
        
        
// ---------------------------------area_tb-----------------------

        public int addArea(String areaName,int cityId,int pincode,double latitude,double longitude);

        public int updateArea(int areaId,String areaName,int cityId,int pincode,double latitude,double longitude);

        public int deleteArea(int areaId);

        public Collection<AreaTb> getAllArea();
        
//-------------------------------Registration-------------------

        public int addUser(String userName,String password,String email,long contact);

        public int addUserGroup(int userId,int groupId);
        
        public int authenticateUser(String email,long contact);
        
        public int hospitalRegistration(String hospitalName,String address,int areaId,int cityId,int pincode,double latitude,double longitude,Time openingTime,Time closingTime,String logo,String documents,String email,long contact);
        
        public int doctorRegistration(String doctorName,int specializaionId,String experience,String gender,String certificates,String education,String email,long contact);
        
        public int patientRegistration(String patientName,String gender,String address,int age,String username,String password,String email,long contact);

        
        public Collection<PatientTb> getAllPatient();
        
// ---------------------------------------verify_hospital------------------------------

        public Collection<HospitalTb> getAllHospital();

        public int verifyHospital(int hospitalId);

// ---------------------------- verify_doctor -----------------------

        public Collection<DoctorTb> getAllDoctor();

        public int verifyDoctor(int doctorId);


// -----------------------------------specializaion_tb----------------------------------------
        public int addSpecialization(String name,String description,String image);

        public int updateSpecialization(int specializaionId, String name,String description,String image);

        public int deleteSpecialization(int specializaionId);

        public Collection<SpecializationTb> getAllSpecialization();
        
        public SpecializationTb getSpecializationById(int specializaionId);
        
        public Collection<SpecializationTb> getSpecializationByParentId(int parentSpecializationId);
        
// ------------------------------------manage fees----------------------

        public int addFees(int hospitalId,int specializaionId,int fees);
        
        public int updateFees(int feesId,int hospitalId,int specializaionId,int fees);
        
        public Collection<FeesTb> getFeesByHospital(int hospitalId);
        
        public Collection<FeesTb> getFeesBySpeciality(int specializaionId);
        
        public int deleteFees(int feesId);
        
//  --------------------------review-----------------------

        public int addReview(int patientId,int doctorId,int hospitalId,String review);

        public Collection<ReviewTb> getAllReview();
        
        
        
// ----------------------------manage appointment ---------------------

        public int bookAppointment(int doctorId,int patientId,int hospitalId,Date date,Time time,String amPm);

        public int cancelAppointment(int appointmentId);

        public Collection<AppointmentTb> getAllDoctorAppointment(int hospitalId,int doctorId);

// ---------------------------- view appointment --------------------

        public Collection<AppointmentTb> getAllAppointment();

// -----------------------------------------appointment tracking--------------------------

        public Collection<AppointmentTb> getAllPatientAppointment(int patientId);
     
// ----------------- view doctor's Patient---------------------

        public Collection<PatientTb> getPatientOfDoctor(int doctorId);

// -----------------------view hospital's patient ---------------------------------

        public Collection<PatientTb> getPatientOfHospital(int hospitalId);
        
// ---------------------------------------------search--------------------------------------------------------------
        
        public Collection<HospitalTb> getHospitalByArea(String areaName);

        public Collection<DoctorTb> getDoctorByArea(String areaName);

        public Collection<HospitalTb> getHospitalBySpecialization(int specializaionId);
        
        public Collection<DoctorTb> getDoctorOfHospital(int hospitalId);
        
        public Collection<DoctorTb> getDoctorBySpecialization(int specializaionId);
        
        public Collection<HospitalTb> getHospitalByName(String hospitalName);
        
        public Collection<DoctorTb> getDoctorByName(String doctorName);
        
        public Collection<HospitalTb> getHospitalByFeesAndSpecialization(int specializaionId,int fromFees,int toFees);
        
        public Collection<HospitalTb> emergency(int specializaionId);

        public Collection<HospitalTb> getHospitalByCity(String cityName);

        public Collection<HospitalTb> getHospitalByState(String stateName);
        
        public Collection<DoctorTb> getDoctorByExperience();

        public Collection<HospitalTb> getHospitalByLowToHighFees(String spcializationName);

        public Collection<HospitalTb> getHospitalByHighToLowFees(String spcializationName);
        
        public Collection<HospitalTb> getHospitalByFees(String name,int fromFees,int toFees);
        
        public Collection<DoctorTb> getDoctorByAvailability(Date date);
        
        public Collection<HospitalTb> getHospitalByAreaAndSpecializationName(String areaName,String specializationName);
        
// ------------------------------------edit profile-------------------------------------------------
        
        public int editHospitalProfile(int hospitalId,String hospitalName,String address,int areaId,int cityId,int pincode,Time openingTime,Time closingTime,String logo,String email,long contact,String username,int userId);
        
        public int editDoctorProfile(int doctorId,String doctorName,int specializaionId,String experience,String gender,String certificates,String education,String email,long contact,String username,int uerId);
        
        public int editPatientProfile(int patientId,String patientName,String gender,String address,int age,String username,String email,long contact,int userId);

// -------------------------------------------add schedule------------------------------------------------
        
        public long addSchedule(int hospitalId,int doctorId,Date date,Time fromTime,Time toTime,int numberOfPatients);
   
        
//---------------------------------Doctor Attachment --------------------------------------------------------------------------------

        public int addAttachment(int doctorId,int patientId,String attachment);

        public Collection<DoctorAttachmentTb> viewAttachmentOfDoctor(int doctorId);

        public Collection<DoctorAttachmentTb> viewAttachmentOfPatient(int patientId);
        
        public Collection<DoctorTb> getDoctorBySpecializationName (String specializationName);
        
 //----------------------Get Total Number of entity----------------------------------------------------------------------
        
        public long getTotalDoctors();
        
        public long getTotalHospitals();
        
        public long getTotalAppointments();
        
        public long getTotalPatients();
        
       public PatientTb getPatientByEmail(String email);
       
       public Collection<DoctorTb> getDoctorByGender(String gender);
       
       public void changePassword(String email,String password);
}
