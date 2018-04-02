package com.parients.repository;


import com.parients.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommentsRepository {

    public ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    public JdbcTemplate jdbcTemplate = (JdbcTemplate) context.getBean("jdbcTemplate");


    public void create_table(){
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS comments " +
                "(" +
                "  comments_id bigint NOT NULL PRIMARY KEY (comments_id)," +
                "  comment_value character varying," +
                "  id_patient bigserial NOT NULL" +
                "); ALTER TABLE comments ADD CONSTRAINT id_pat_const FOREIGN KEY (id_patient)" +
                "REFERENCES patient(patient_id)" );
    }

    public CommentsRepository(){}

    public CommentsRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate=jdbcTemplate;
    }

   public List<Comment> commentList(){
        return jdbcTemplate.query("SELECT comments_id,comment_value ,id_patient FROM comments",
                (rs,rowNum)->new Comment(rs.getInt("comments_id"),
                        rs.getString("comment_value"),rs.getLong("id_patient")));
    }


   public List<Comment> commentListSelectByForeigh(int key){
        return jdbcTemplate.query("SELECT comments_id,comment_value,id_patient FROM comments WHERE id_patient="+key,
                (rs,rowNum)->new Comment(rs.getLong("comments_id"),
                        rs.getString("comment_value"),rs.getLong("id_patient")));
    }




    public void update(Comment comment){
        jdbcTemplate.update("UPDATE comments SET comment_value=\'"+comment.getComment_value()+"\' WHERE comments_id="+comment.getComment_id());

    }



    public void delete(Comment comment){
        jdbcTemplate.execute("DELETE FROM comments WHERE comments_id="+comment.getComment_id());
    }


    public void add(Comment comment){
        System.out.println("add method");
        jdbcTemplate.update("INSERT INTO comments VALUES("+comment.getComment_id()+",\'"+comment.getComment_value()+"\',\'"+comment.getPatient_id()+"\');");
    }



}
