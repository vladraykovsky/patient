package com.parients.restcontroller;

import com.parients.model.Comment;
import com.parients.repository.CommentsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/api/comment")
public class CommentController {
    CommentsRepository commentsRepository = new CommentsRepository();

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE , method = RequestMethod.GET)
    public List<Comment> selectAllComments(){
        return commentsRepository.commentList();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/{comment_id}", method = RequestMethod.GET)
    public List<Comment> byid(@PathVariable("comment_id") String comment_id){
        return commentsRepository.commentListSelectByForeigh(Integer.valueOf(comment_id));
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(method = RequestMethod.PATCH)
    public ResponseEntity<String> update(@RequestBody Comment comment){
        commentsRepository.update(comment);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> add(@RequestBody Comment comment){
        System.out.println("in add");
        commentsRepository.add(comment);
        System.out.println("added");
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/{comments_id}",method = RequestMethod.DELETE)
    public ResponseEntity< String > delete(@PathVariable("comments_id") String comments_id){
        commentsRepository.delete(new Comment(Long.valueOf(comments_id)," ", 0));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/foreign",method = RequestMethod.POST)
    public ResponseEntity< String > deleteByForeignKey(@RequestBody Comment comment){
        commentsRepository.delete_by_foreign_key(comment);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }



}
