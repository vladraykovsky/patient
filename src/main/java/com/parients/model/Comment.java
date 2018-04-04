package com.parients.model;

import java.io.Serializable;
import java.util.Objects;

public class Comment implements Comparable<Comment> , Serializable{
    long comment_id;
    String comment_value;
    long patient_id;
    static long comment_id_add;
    public Comment(long comment_id, String comment_value, long patient_id) {
        this.comment_id = comment_id;
        this.comment_value = comment_value;
        this.patient_id = patient_id;
    }

    public Comment(){}

    public Comment(String comment_value, long patient_id) {
        comment_id_add++;
        this.comment_id = comment_id_add;
        this.comment_value = comment_value;
        this.patient_id = patient_id;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return comment_id == comment.comment_id &&
                patient_id == comment.patient_id &&
                Objects.equals(comment_value, comment.comment_value);
    }

    @Override
    public int hashCode() {

        return Objects.hash(comment_id, comment_value, patient_id);
    }


    public long getComment_id() {
        return comment_id;
    }

    public void setComment_id(long comment_id) {
        this.comment_id = comment_id;
    }

    public String getComment_value() {
        return comment_value;
    }

    public void setComment_value(String comment_value) {
        this.comment_value = comment_value;
    }

    public long getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(long patient_id) {
        this.patient_id = patient_id;
    }


    @Override
    public String toString() {
        return "Comment{" +
                "comment_id=" + comment_id +
                ", comment_value='" + comment_value + '\'' +
                ", patient_id=" + patient_id +
                '}';
    }

    @Override
    public int compareTo(Comment o) {
        return (int) (o.getComment_id()- this.getComment_id());
    }
}
