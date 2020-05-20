<%-- 
    Document   : review
    Created on : 19 May, 2020, 7:32:08 PM
    Author     : Admin
--%>

<%@page import="com.oracle.wls.shaded.org.apache.regexp.recompile"%>
<%@page import="cdi.reviewBean"%>
<%@page import="javax.ejb.EJB"%>
<%@page import="beans.doctzBeanLocal"%>
<%@page import="beans.doctzBeanLocal"%>
<%@page import="entity.PatientTb"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
        
    <%
        
        
        //int did=Integer.parseInt(request.getParameter("did"));
        String rev=request.getParameter("rev");
       // System.out.println(rev);
        
        reviewBean r=new reviewBean();
        //int pid=se
        String str=r.addReview(4,rev);
        if(str.equals("failure"))
        {
            response.sendRedirect("login.xhtml");
        }
        else if(str.equals("success"))
        {
            response.sendRedirect("doctorProfile.xhtml?did="+4);
        }


        %>
</html>
