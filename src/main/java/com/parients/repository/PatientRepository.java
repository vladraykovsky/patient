package com.parients.repository;


import com.parients.model.Patient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;


@Repository
@CrossOrigin(origins = "http://localhost:4200")
public class PatientRepository {
    public ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    public JdbcTemplate jdbcTemplate = (JdbcTemplate) context.getBean("jdbcTemplate");


   public List<Patient> selectAll(){
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
                        rs.getString("sex")));
    }


    public List<Patient> selectFirstPatient(){
        return jdbcTemplate.query("SELECT patient_id, name , surname , date_of_birth,country,state, address,sex " +
                                       "FROM patient LIMIT 1" ,
                (rs,rowNum)->new Patient(rs.getLong("patient_id"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getDate("date_of_birth").toString(),
                        rs.getString("country"),
                        rs.getString("state"),
                        rs.getString("address"),
                        rs.getString("sex")));
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
        jdbcTemplate.update("INSERT INTO patient VALUES( DEFAULT"+
                ",\'"+patient.getName()+
                "\',\'"+patient.getSurname()+
                "\',\'"+patient.getDate_of_birth()+
                "\',\'"+patient.getCountry()+
                "\',\'"+patient.getState()+
                "\',\'"+patient.getAddress()+
                "\',\'"+patient.getSex()+
                "\');");
    }


    public void resetDataBase(){
        jdbcTemplate.execute("DROP TABLE comments; " +
                " DROP TABLE patient;" +
                "CREATE TABLE patient" +
                "(" +
                "  patient_id BIGSERIAL NOT NULL PRIMARY KEY," +
                "  name character varying," +
                "  surname character varying," +
                "  date_of_birth date," +
                "  country character varying," +
                "  state character varying," +
                "  address character varying," +
                "  sex character varying" +
                ");" +
                "CREATE TABLE comments" +
                "(" +
                "  comments_id BIGSERIAL NOT NULL PRIMARY KEY," +
                "  comment_value character varying," +
                "  id_patient BIGSERIAL NOT NULL" +
                ");" +
                "ALTER TABLE comments " +
                "   ADD CONSTRAINT fk_someName" +
                "   FOREIGN KEY (id_patient) " +
                "   REFERENCES patient(patient_id);");
    }
}
