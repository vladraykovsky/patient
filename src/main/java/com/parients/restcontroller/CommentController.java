package com.parients.restcontroller;

import com.parients.model.Comment;
import com.parients.model.Patient;
import com.parients.repository.CommentsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {
    CommentsRepository commentsRepository = new CommentsRepository();




    @RequestMapping(value = "api/comment",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Comment> db(){
        return commentsRepository.commentList();
    }

    @RequestMapping(value = "api/comment/{comment_id}")
    public List<Comment> byid(@PathVariable("comment_id") String comment_id){
        System.out.println("comment_id");
        return commentsRepository.commentListSelectByForeigh(Integer.valueOf(comment_id));
    }

    @RequestMapping(value = "/api/comment/update",method = RequestMethod.PATCH)
    public ResponseEntity<String> update(@RequestBody Comment comment){
        System.out.println(comment);
        commentsRepository.update(comment);
        System.out.println("update");
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @RequestMapping(value = "/api/comment/add",method = RequestMethod.POST)
    public ResponseEntity<String> add(@RequestBody Comment comment){
        commentsRepository.add(comment);
        System.out.println("add");
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @RequestMapping(value = "/api/comment/delete",method = RequestMethod.DELETE)
    public ResponseEntity< String > delete(@RequestBody Comment comment){
        System.out.println(comment);
        commentsRepository.delete(comment);
        System.out.println("delete");
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


}
