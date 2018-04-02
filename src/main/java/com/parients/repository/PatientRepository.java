package com.parients.repository;


import com.parients.comparator.CommentComparator;
import com.parients.model.Comment;
import com.parients.model.Patient;
import org.springframework.context.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Repository
public class PatientRepository {
    public ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    public JdbcTemplate jdbcTemplate = (JdbcTemplate) context.getBean("jdbcTemplate");

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }




   public List<Patient> selectAll(){
        create_table();
        return jdbcTemplate.query("SELECT patient_id, name , surname , date_of_birth,country,state, address,sex FROM patient",
                (rs,rowNum)->new Patient(rs.getLong("patient_id"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getDate("date_of_birth").toString(),
                        rs.getString("country"),
                        rs.getString("state"),
                        rs.getString("address"),
                        rs.getString("sex")));
    }


    public Set<Comment> loadComments(int id){
        return new TreeSet<>( new CommentsRepository(jdbcTemplate).commentListSelectByForeigh(id));
    }




    public List<Patient> selectById(int id){
        return jdbcTemplate.query("SELECT patient_id, name , surname , date_of_birth,country,state, address,sex " +
                        "FROM patient WHERE patient_id ="+id,
                (rs,rowNum)->new Patient(rs.getLong("patient_id"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getDate("date_of_birth").toString(),
                        rs.getString("country"),
                        rs.getString("state"),
                        rs.getString("address"),
                        rs.getString("sex"),new TreeSet<>(new CommentsRepository(jdbcTemplate).commentListSelectByForeigh(id))));
    }


    public void update(Patient patient){
        jdbcTemplate.update("UPDATE patient SET name=\'"+patient.getName()+
                "\',surname =\'"+patient.getSurname()+
                "\',date_of_birth=\'"+patient.getDate_of_birth()+
                "\',country =\'"+patient.getCountry()+
                "\',state =\'"+patient.getState()+
                "\',address =\'"+patient.getAddress()+
                "\',sex =\'"+patient.getSex()+
                "\' WHERE patient_id ="+patient.getPatient_id());
    }



    public void delete(Patient patient){
        jdbcTemplate.execute("DELETE FROM patient WHERE patient_id="+patient.getPatient_id());
    }


    public void add(Patient patient){
        System.out.println("add method");
        jdbcTemplate.update("INSERT INTO patient VALUES("+patient.getPatient_id()+
                ",\'"+patient.getName()+
                "\',\'"+patient.getSurname()+
                "\',\'"+patient.getDate_of_birth()+
                "\',\'"+patient.getCountry()+
                "\',\'"+patient.getState()+
                "\',\'"+patient.getAddress()+
                "\',\'"+patient.getSex()+
                "\');");
    }








}
