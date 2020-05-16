/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.doctzBeanLocal;
import client.myclient;
import entity.*;
import java.sql.Date;
import java.util.*;
import java.text.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Time;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Admin
 */
public class test extends HttpServlet {

   @EJB doctzBeanLocal ejb;
   myclient c1=new myclient();
   Response res;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet test</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1> doctz test</h1>");
//            int status=ejb.addState("rajasthan");
//            if(status==1)
//            {
//                out.println("<script>alert('inserted');</script>");
//            }
//            else
//            {
//                out.println("<script>alert('not inserted');</script>");
//            }
            
//            int status=ejb.updateState(4, "abc");
//            if(status==1)
//            {
//                out.println("<script>alert('updated');</script>");
//            }
//            else
//            {
//                out.println("<script>alert('not updated');</script>");
//            }
//            int status=ejb.deleteState(4);
//            if(status==1)
//            {
//                out.println("<script>alert('deleted');</script>");
//            }
//            else
//            {
//                out.println("<script>alert('not deleted');</script>");
//            }
            
//            Collection<StateTb> states=ejb.getAllState();
//            for(StateTb s:states)
//            {
//                out.println(s.getStateName()+" "+s.getIsActive()+"<br>");
//            }
                
// ------------------------------------------city------------------------------------------------------------------------

//          int status=ejb.addCity("surat", 1);
//            if(status==1)
//            {
//                out.println("<script>alert('inserted');</script>");
//            }
//            else
//            {
//                out.println("<script>alert('not inserted');</script>");
//            }
            

//            int status=ejb.deleteCity(1);
//            if(status==1)
//            {
//                out.println("<script>alert('deleted');</script>");
//            }
//            else
//            {
//                out.println("<script>alert('not deleted');</script>");
//            }

//            int status=ejb.updateCity(2, "baroda", 1);
//            if(status==1)
//            {
//                out.println("<script>alert('updated');</script>");
//            }
//            else
//            {
//                out.println("<script>alert('not updated');</script>");
//            }
//            
//
//            Collection<CityTb> city=ejb.getAllCity();
//            for(CityTb s:city)
//            {
//                out.println(s.getStateId().getStateName()+" "+s.getCityName()+"<br>");
//            }

//---------------------------------------------------Area------------------------------------------------------------

//            int status=ejb.addArea("pal",1,395009,21.1998441,72.7434155);
//            if(status==1)
//            {
//                out.println("<script>alert('inserted');</script>");
//            }
//            else
//            {
//                out.println("<script>alert('not inserted');</script>");
//            }

//            int status=ejb.updateArea(2, "pal gam", 1,395009,21.1998441,72.7434155);
//            if(status==1)
//            {
//                out.println("<script>alert('updated');</script>");
//            }
//            else
//            {
//                out.println("<script>alert('not updated');</script>");
//            }

//            int status=ejb.deleteArea(2);
//            if(status==1)
//            {
//                out.println("<script>alert('deleted');</script>");
//            }
//            else
//            {
//                out.println("<script>alert('not deleted');</script>");
//            }
//           
//
//            Collection<AreaTb> area=ejb.getAllArea();
//            for(AreaTb a:area)
//            {
//                out.println(a.getAreaName()+" "+a.getCityId().getCityName()+" "+a.getLatitude()+"<br>");
//            }

// ------------------------------------user------------------------------------------------------------------------------

//            int id=ejb.addUser("nancy", "nancy", "nan@gmail.com", 9876545678L);
//            out.println(id);
//            int i=ejb.addUserGroup(id, 2);

// --------------------------------------------------specailization---------------------------------------------
//            int status=ejb.addSpecialization("abc", 1, "xyz", "images/img.jpg");
//            if(status==1)
//            {
//                out.println("<script>alert('inserted');</script>");
//            }
//            else
//            {
//                out.println("<script>alert('not inserted');</script>");
//            }
               
//            int status=ejb.updateSpecialization(2, "pqr", 2, "bhdfvj", "images/img.jpg");
//            if(status==1)
//            {
//                out.println("<script>alert('updated');</script>");
//            }
//            else
//            {
//                out.println("<script>alert('not updated');</script>");
//            }

//            Collection<SpecializationTb> spes=ejb.getAllSpecialization();
//            for(SpecializationTb s:spes)
//            {
//                out.println(s.getName()+"--- "+s.getParentSpecializationId().getName()+"--- "+s.getDescription()+"--- "+s.getImage()+"<br>");
//            }
//
//            Collection<SpecializationTb> spes=ejb.getSpecializationByParentId(1);
//            for(SpecializationTb s:spes)
//            {
//                out.println(s.getName()+" "+s.getParentSpecializationId().getName()+" "+s.getDescription()+" "+s.getImage()+"<br>");
//            }

//            int status=ejb.deleteSpecialization(2);
//            if(status==1)
//            {
//                out.println("<script>alert('deleted');</script>");
//            }
//            else
//            {
//                out.println("<script>alert('not deleted');</script>");
//            }

// ----------------------------------------------------hospital------------------------------------------------------

//           SimpleDateFormat ft =new SimpleDateFormat ("hh:mm");
//           String str1="10:00:00";
//           String str2="8:00:00";
//           Time t1=null;
//           Time t2=null;
//            try {
//                t1=new Time(ft.parse(str1).getTime());
//               t2=new Time(ft.parse(str2).getTime());
//               // out.println(t);
//            } catch (ParseException ex) {
//                Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        
//        int status=ejb.hospitalRegistration("city hospital", "c-54,mangal", 1, 1, 395009, 21.1998441, 72.7434155, t1, t2, "images/img.jpg", "images/img.jpg","nidhimehta129399@gmail.com", 9878987898L);
//          if(status==1)
//            {
//                out.println("<script>alert('inserted');</script>");
//            }
//            else
//            {
//                out.println("<script>alert('not inserted');</script>");
//            }


//            int status=ejb.doctorRegistration("Dr.roshan desai",1,"5 yaers ", "Male","imgaes/img.jpg" , "DMD", "roshan1.u.desai@gmail.com", 9876787678L);
//            if(status==1)
//            {
//                out.println("<script>alert('inserted');</script>");
//            }
//            else
//            {
//                out.println("<script>alert('not inserted');</script>");
//            }

//            int status=ejb.patientRegistration("prince", "Male", "adajan", 19, "prince", "password", "princekapadia.pk@gmail.com",8765676567L );
//            if(status==1)
//            {
//                out.println("<script>alert('inserted');</script>");
//            }
//            else
//            {
//                out.println("<script>alert('not inserted');</script>");
//            }
//            
//                int status=ejb.verifyHospital(3);
//                Collection<HospitalTb> hospitals=ejb.getAllHospital();
//                for(HospitalTb h:hospitals)
//                {
//                    out.println(h.getHospitalName()+" "+h.getUserId().getUserName()+ " "+h.getUserId().getPassword()
//                            +" "+ h.getAreaId().getAreaName()+" "+h.getCityId().getCityName() +"<br>");
//                }

//                int status=ejb.verifyDoctor(1);
//                Collection<DoctorTb> doctors=ejb.getAllDoctor();
//                for(DoctorTb d:doctors)
//                {
//                    out.println(d.getDoctorName()+" "+d.getUserId().getUserName()+ " "+d.getUserId().getPassword()+" "+d.getSpecializationId().getName() +"<br>");
//                }


// ----------------------------------------------------fees------------------------------------------------------------

//            int status=ejb.addFees(3, 2, 600);
//            if(status==1)
//            {
//                out.println("<script>alert('inserted');</script>");
//            }
//            else
//            {
//                out.println("<script>alert('not inserted');</script>");
//            }

//            int status=ejb.updateFees(13, 3, 2, 1000);
//            if(status==1)
//            {
//                out.println("<script>alert('inserted');</script>");
//            }
//            else
//            {
//                out.println("<script>alert('not inserted');</script>");
//            }

//            int status=ejb.deleteFees(13);
//            if(status==1)
//            {
//                out.println("<script>alert('inserted');</script>");
//            }
//            else
//            {
//                out.println("<script>alert('not inserted');</script>");
//            }
            
//            Collection<FeesTb> fees=ejb.getFeesByHospital(3);
//                for(FeesTb f:fees)
//                {
//                    out.println(f.getHospitalId().getHospitalName()+" "+f.getSpecializationId().getName()+ " "+f.getFees()+"<br>");
//                }

//                Collection<FeesTb> fees=ejb.getFeesBySpeciality(1);
//                for(FeesTb f:fees)
//                {
//                    out.println(f.getHospitalId().getHospitalName()+" "+f.getSpecializationId().getName()+ " "+f.getFees()+"<br>");
//                }
//            
                   
//--------------------------------------------Review----------------------------------------------

//            int status=ejb.addReview(1,0,3,"Nice experience");
//            if(status==1)
//            {
//                out.println("<script>alert('inserted');</script>");
//            }
//            else
//            {
//                out.println("<script>alert('not inserted');</script>");
//            }
                

//                Collection<ReviewTb> reviews=ejb.getAllReview();
//                for(ReviewTb r:reviews)
//                {
//                    out.println(r.getPatientId().getPatientName()+"  "+r.getReview()+"<br>");
//                }


// --------------------------------------------appointment--------------------------------------
            
//           SimpleDateFormat ft =new SimpleDateFormat ("hh:mm:ss");
//           String str1="3:00:00";
//           
//           Time t1=null;
//           String string = "2020-02-23";
//            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//            java.sql.Date d = Date.valueOf("2020-02-23");
//            try {
//                t1=new Time(ft.parse(str1).getTime());
//               
//                
//            } catch (ParseException ex) {
//                Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            int status=ejb.bookAppointment(1, 1, 3, d, t1, "PM");
//            if(status==1)
//            {
//                out.println("<script>alert('inserted');</script>");
//            }
//            else
//            {
//                out.println("<script>alert('not inserted');</script>");
//            }

//            int status=ejb.cancelAppointment(2);
//            if(status==1)
//            {
//                out.println("<script>alert('inserted');</script>");
//            }
//            else
//            {
//                out.println("<script>alert('not inserted');</script>");
//            }

//                Collection<AppointmentTb> doctors=ejb.getAllDoctorAppointment(3, 1);
//                for(AppointmentTb a:doctors)
//                {
//                    out.println(a.getPatientId().getPatientName()+"  "+a.getDoctorId().getDoctorName()+"<br>");
//                }
                
//                 Collection<AppointmentTb> doctors=ejb.getAllAppointment();
//                for(AppointmentTb a:doctors)
//                {
//                    out.println(a.getPatientId().getPatientName()+"  "+a.getDoctorId().getDoctorName()+"<br>");
//                }
                
//                 Collection<AppointmentTb> doctors=ejb.getAllPatientAppointment(1);
//                for(AppointmentTb a:doctors)
//                {
//                    out.println("Patient : " +a.getPatientId().getPatientName()+"  "+a.getDoctorId().getDoctorName()+"<br>");
//                }

// ---------------------------------------------------search-------------------------------------------------------

//                Collection<PatientTb> doctors=ejb.getPatientOfDoctor(1);
//                for(PatientTb a:doctors)
//                {
//                    out.println("Patient : " +a.getPatientName()+"<br>");
//                }
//                out.println("hospital<br>");
//                Collection<PatientTb> hos=ejb.getPatientOfHospital(3);
//                for(PatientTb a:hos)
//                {
//                    out.println("Patient : " +a.getPatientName()+"<br>");
//                }

//                Collection<HospitalTb> hos=ejb.getHospitalByArea("adajan");
//                for(HospitalTb a:hos)
//                {
//                    out.println(a.getHospitalName()+"<br>");
//                }

//                Collection<DoctorTb> hos=ejb.getDoctorByArea("adajan");
//                for(DoctorTb a:hos)
//                {
//                    out.println(a.getDoctorName()+"<br>");
//                }

//               Collection<DoctorTb> hos=ejb.getDoctorOfHospital(3);
//                for(DoctorTb a:hos)
//                {
//                    out.println(a.getDoctorName()+"<br>");
//                }

//                Collection<HospitalTb> hos=ejb.getHospitalBySpecialization(1);
//                for(HospitalTb a:hos)
//                {
//                    out.println(a.getHospitalName()+"<br>");
//                }

//                Collection<DoctorTb> hos=ejb.getDoctorBySpecialization(1);
//                for(DoctorTb a:hos)
//                {
//                    out.println(a.getDoctorName()+"<br>");
//                }

//                Collection<HospitalTb> hos=ejb.getHospitalByName("city hospital");
//                for(HospitalTb a:hos)
//                {
//                    out.println(a.getHospitalName()+" "+a.getAreaId().getAreaName()+"<br>");
//                }

//                Collection<DoctorTb> hos=ejb.getDoctorByName("Dr.roshan desai");
//                for(DoctorTb a:hos)
//                {
//                    out.println(a.getDoctorName()+" "+a.getExperience()+"<br>");
//                }

//                Collection<HospitalTb> hos=ejb.getHospitalByFeesAndSpecialization(1, 500, 1000);
//                for(HospitalTb a:hos)
//                {
//                    out.println(a.getHospitalName()+" "+a.getAreaId().getAreaName()+"<br>");
//                }

//-------------------------------------------------------------------------------------------------

//                Collection<HospitalTb> hos=ejb.emergency(1);
//                for(HospitalTb h:hos)
//                {
//                    out.println(h.getHospitalName()+" "+h.getCityId().getCityName()+"<br>");
//                }

//                Collection<HospitalTb> hos=ejb.getHospitalByCity("surat");
//                for(HospitalTb h:hos)
//                {
//                    out.println(h.getHospitalName()+" "+h.getCityId().getCityName()+"<br>");
//                }

//                Collection<HospitalTb> hos=ejb.getHospitalByState("gujarat");
//                for(HospitalTb h:hos)
//                {
//                    out.println(h.getHospitalName()+" "+h.getCityId().getCityName()+" "+h.getCityId().getStateId().getStateName()+"<br>");
//                }
//
//                Collection<DoctorTb> d=ejb.getDoctorByExperience();
//                for(DoctorTb doctors:d)
//                {
//                    out.println(doctors.getDoctorName()+"    "+doctors.getExperience()+"    "+doctors.getGender()+"<br>");
//                }

//                Collection<HospitalTb> hos=ejb.getHospitalByLowToHighFees("dentist");
//                for(HospitalTb h:hos)
//                {
//                    out.println(h.getHospitalName()+" "+h.getCityId().getCityName()+" "+h.getCityId().getStateId().getStateName()+"<br>");
//                }
//                
//                Collection<HospitalTb> hospitals=ejb.getHospitalByHighToLowFees("dentist");
//                for(HospitalTb h:hospitals)
//                {
//                    out.println(h.getHospitalName()+" "+h.getCityId().getCityName()+" "+h.getCityId().getStateId().getStateName()+"<br>");
//                }

//                Collection<HospitalTb> hospitals=ejb.getHospitalByFees("dentist",1200,1800);
//                for(HospitalTb h:hospitals)
//                {
//                    out.println(h.getHospitalName()+" "+h.getCityId().getCityName()+" "+h.getCityId().getStateId().getStateName()+"<br>");
//                }

//              String string = "2020-02-23";
//              DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//              java.sql.Date date = Date.valueOf("2020-02-23");
//                Collection<DoctorTb> d=ejb.getDoctorByAvailability(date);
//                for(DoctorTb doctors:d)
//                {
//                    out.println(doctors.getDoctorName()+"    "+doctors.getExperience()+"    "+doctors.getEducation()+"<br>");
//                }

           
//            SimpleDateFormat ft =new SimpleDateFormat ("hh:mm:ss");
//           String str1="3:00:00";
//           String str2="8:00:00";
//           
//           Time t1=null;
//           Time t2=null;
//           //String string = "2020-02-23";
//            //DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//            //java.sql.Date d = Date.valueOf("2020-02-23");
//            try {
//                t1=new Time(ft.parse(str1).getTime());
//                t2=new Time(ft.parse(str2).getTime());
//               
//                
//            } catch (ParseException ex) {
//                Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            int status=ejb.editHospitalProfile(3, "shelby hospital", "c-44,complex", 2, 1, 395009, t1, t2, "images/img.jpg", "nidhimehta9399@gmail.com", 8141435995L, "shelby", 15);
//            if(status==1)
//            {
//                System.out.println("updated");
//            }
//            else
//            {
//                System.out.println("not updated");
//            }

//            int status=ejb.editDoctorProfile(1,"Dr.Roshan Kapadia",1,"3 years","female","imgaes/img.jpg","DDA","roshan.u.desai1@gmail.com",8141435995L,"roshan",16);
//            if(status==1)
//            {
//                System.out.println("updated");
//            }
//            else
//            {
//                System.out.println("not updated");
//            }

//            int status=ejb.editPatientProfile(1, "Princee","Male", "Adajan", 20, "princekapadia","princekapadia.pk@gmail.com", 8141435995L, 17);
//            if(status==1)
//            {
//                System.out.println("updated");
//            }
//            else
//            {
//                System.out.println("not updated");
//            }

//            SimpleDateFormat ft =new SimpleDateFormat ("hh:mm:ss");
//           String str1="11:00:00";
//           String str2="12:35:00";
//           String string = "2020-03-02";
//            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//            java.sql.Date d = Date.valueOf(string);
//           Time t1=null;
//           Time t2=null;
//            try {
//                t1=new Time(ft.parse(str1).getTime());
//               t2=new Time(ft.parse(str2).getTime());
//                //out.println(d);
//            } catch (ParseException ex) {
//                Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        
//        long status=ejb.addSchedule(3, 1,d , t1, t2, 10);
//        out.println("status=" +status);
            


           
    //-----------------------------------------Doctor Attachment---------------------------------------------------------------------


//            int status=ejb.addAttachment(1,1,"images/img.png");
//            if(status==1)
//            {
//                out.println("<script>alert('inserted');</script>");
//            }
//            else
//            {
//                out.println("<script>alert('not inserted');</script>");
//            }

//                Collection<DoctorAttachmentTb> att=ejb.viewAttachmentOfDoctor(1);
//                for(DoctorAttachmentTb a:att)
//                {
//                    out.println(a.getDoctorId().getDoctorName()+" "+a.getPatientId().getPatientName()+" "+a.getAttachment()+"<br>");
//                }

//                Collection<DoctorAttachmentTb> att=ejb.viewAttachmentOfPatient(2);
//                for(DoctorAttachmentTb a:att)
//                {
//                    out.println("By Patient : "+a.getDoctorId().getDoctorName()+" "+a.getPatientId().getPatientName()+" "+a.getAttachment()+"<br>");
//                }

 
            Collection<StateTb> state=new ArrayList<StateTb>();
            GenericType<Collection<StateTb>> states=new GenericType<Collection<StateTb>>(){};
            res =c1.getAllState(Response.class);
          
            state=res.readEntity(states);
                
            for(StateTb s:state)
            {
                out.println("\n"+s.getStateName()+"<br>");
            }

            out.println("check servlet");
            out.println("</body>");
            out.println("</html>");
        }
    }
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
