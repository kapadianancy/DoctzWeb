package servlets;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import beans.doctzBeanLocal;
import client.myadmin;
import client.myclient;
import entity.*;
import entity.SpecializationTb;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import org.glassfish.soteria.identitystores.hash.Pbkdf2PasswordHashImpl;

/**
 *
 * @author ADMIN
 */
public class s2 extends HttpServlet {

   @EJB doctzBeanLocal ejb;
   Pbkdf2PasswordHashImpl pb=new Pbkdf2PasswordHashImpl();
    String n="nidhi";
    String pass=pb.generate(n.toCharArray());
//    
   Response res;
   myclient c1=new myclient();
   myadmin a1=new myadmin();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet s2</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Test</h1>");
            
//            res=c1.patientRegistration(Response.class, "Alexa", "Female", "Alexa", "44", "alexa","alexa", "a4@gmail.com","9876543210");
//            out.println(res);
            
//            int i=ejb.patientRegistration("Alexa", "Female", "Alexa", 44, "alexa","alexa", "a4@gmail.com",9876543210L);
//            out.println(i);
            
//           res=a1.addState(Response.class, "Punjab");

            //res=a1.updateSpecialization(Response.class, "1", "Urologyyyy", "Urology is a part of health care that deals with diseases of the male and female urinary tract.", "resources/img/specialities/specialities-01.png");

//            
//            Collection<SpecializationTb> sp=new ArrayList<SpecializationTb>();
//            GenericType<Collection<SpecializationTb>> s=new GenericType<Collection<SpecializationTb>>(){};
//            res =a1.getAllSpecialization(Response.class);
//          //  out.println(res);
//          
//            sp=res.readEntity(s);
//            
//            for(SpecializationTb s1:spe)
//            {
//                out.println(s1.getName()+" "+s1.getDescription()+" "+s1.getImage()+"<br>");
//            }

//                GenericType<SpecializationTb> specs=new GenericType<SpecializationTb>(){};
//                res=a1.getSpecializationById(Response.class, "2");
//                SpecializationTb spe=res.readEntity(specs);
//                out.println(spe.getName()+" "+spe.getDescription()+" "+spe.getImage()+"<br>");
//                
//            
            
            Collection<DoctorTb> sp=ejb.getDoctorOfHospital(7);
            
            for(DoctorTb s1:sp)
            {
                out.println("\n"+s1.getDoctorName()+"<br>");
            }

<<<<<<< HEAD
            PatientTb p=new PatientTb();
            HttpSession session= request.getSession(true);
            String str=session.getAttribute("username").toString();
            p=ejb.getPatientByEmail(str);
            //System.err.println(p.getPatientName());
            res=c1.addReview(Response.class,String.valueOf(p.getPatientId()),String.valueOf(4),"0","nancy");
            System.out.println(res.toString());
        
=======
//            PatientTb p=new PatientTb();
//            HttpSession session= request.getSession(true);
//            String str=session.getAttribute("username").toString();
//            p=ejb.getPatientByEmail(str);
//            //System.err.println(p.getPatientName());
//            res=c1.addReview(Response.class,String.valueOf(p.getPatientId()),String.valueOf(4),"0","nancy");
//            System.out.println(res.toString());
            
>>>>>>> b89e429879f7d8979ca853437097ff4555b472a8
//
//            PatientTb p=ejb.getPatientByEmail("kapadianancy21@gmail.com");
//            out.println(p.getPatientName()+p.getUserId().getEmail());

 //           out.print(pass);
//             String string = "2020-05-21";
//             DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//             java.sql.Date d1 = Date.valueOf(string);
//            Collection<DoctorScheduleTb> docs=ejb.getScheduleByDoctorAndDate(4,d1);
//            for(DoctorScheduleTb d:docs)
//            {
//                out.println(d.getHospitalId().getHospitalName()+" "+d.getDate()+"   "+d.getFromTime()+ "   "+d.getToTime()+"<br/>");
//            }

//  Collection<ReviewTb> docs=ejb.getReviewByDoctorId(4);
//            for(ReviewTb d:docs)
//            {
//                out.println(d.getDoctorId().getDoctorName()+" "+d.getReview());
//            }

<<<<<<< HEAD

                PatientTb p1=ejb.getPatientByEmail("kapadianancy21@gmail.com");

//  Collection<ReviewTb> docs=ejb.getReviewByDoctorId(4);
=======

//                PatientTb p1=ejb.getPatientByEmail("kapadianancy21@gmail.com");
//
//            Collection<ReviewTb> docs=ejb.getReviewByDoctorId(4);
>>>>>>> b89e429879f7d8979ca853437097ff4555b472a8
//            for(ReviewTb d:docs)
//            {
//                out.println(d.getDoctorId().getDoctorName()+" "+d.getReview());
//            }


        //    out.print(pass);

             Collection<FeesTb> docs=ejb.getFeesBySpecialityandHospital(8,5);
            for(FeesTb d:docs)
            {
                out.println(d.getFees());
            }
        

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