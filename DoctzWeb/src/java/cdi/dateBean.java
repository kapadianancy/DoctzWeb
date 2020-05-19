/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cdi;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import javax.inject.Named;
import javax.enterprise.context.Dependent;

/**
 *
 * @author ADMIN
 */
@Named(value = "dateBean")
@Dependent
public class dateBean {

   private String Date1,Date2,Date3,Date4,Date5,Date6,Date7;
   private String Day1,Day2,Day3,Day4,Day5,Day6,Day7;
  
    public dateBean() throws ParseException {
       
        Date1=LocalDateTime.now().toLocalDate().toString();
        Date2=LocalDateTime.now().plusDays(1).toLocalDate().toString();
        Date3=LocalDateTime.now().plusDays(2).toLocalDate().toString();
        Date4=LocalDateTime.now().plusDays(3).toLocalDate().toString();
        Date5=LocalDateTime.now().plusDays(4).toLocalDate().toString();
        Date6=LocalDateTime.now().plusDays(5).toLocalDate().toString();
        Date7=LocalDateTime.now().plusDays(6).toLocalDate().toString();
        
        Day1=LocalDateTime.now().toLocalDate().getDayOfWeek().toString();
        Day2=LocalDateTime.now().plusDays(1).toLocalDate().getDayOfWeek().toString();
        Day3=LocalDateTime.now().plusDays(2).toLocalDate().getDayOfWeek().toString();
        Day4=LocalDateTime.now().plusDays(3).toLocalDate().getDayOfWeek().toString();
        Day5=LocalDateTime.now().plusDays(4).toLocalDate().getDayOfWeek().toString();
        Day6=LocalDateTime.now().plusDays(5).toLocalDate().getDayOfWeek().toString();
        Day7=LocalDateTime.now().plusDays(6).toLocalDate().getDayOfWeek().toString();
    }

    
    public String getDate1() {
        return Date1;
    }

    public void setDate1(String Date1) {
        this.Date1 = Date1;
    }

    public String getDate2() {
        return Date2;
    }

    public void setDate2(String Date2) {
        this.Date2 = Date2;
    }

    public String getDate3() {
        return Date3;
    }

    public void setDate3(String Date3) {
        this.Date3 = Date3;
    }

    public String getDate4() {
        return Date4;
    }

    public void setDate4(String Date4) {
        this.Date4 = Date4;
    }

    public String getDate5() {
        return Date5;
    }

    public void setDate5(String Date5) {
        this.Date5 = Date5;
    }

    public String getDate6() {
        return Date6;
    }

    public void setDate6(String Date6) {
        this.Date6 = Date6;
    }

    public String getDate7() {
        return Date7;
    }

    public void setDate7(String Date7) {
        this.Date7 = Date7;
    }

    public String getDay1() {
        return Day1;
    }

    public void setDay1(String Day1) {
        this.Day1 = Day1;
    }

    public String getDay2() {
        return Day2;
    }

    public void setDay2(String Day2) {
        this.Day2 = Day2;
    }

    public String getDay3() {
        return Day3;
    }

    public void setDay3(String Day3) {
        this.Day3 = Day3;
    }

    public String getDay4() {
        return Day4;
    }

    public void setDay4(String Day4) {
        this.Day4 = Day4;
    }

    public String getDay5() {
        return Day5;
    }

    public void setDay5(String Day5) {
        this.Day5 = Day5;
    }

    public String getDay6() {
        return Day6;
    }

    public void setDay6(String Day6) {
        this.Day6 = Day6;
    }

    public String getDay7() {
        return Day7;
    }

    public void setDay7(String Day7) {
        this.Day7 = Day7;
    }

    
    
    
}
