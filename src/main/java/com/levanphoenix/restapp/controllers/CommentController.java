package com.levanphoenix.restapp.controllers;

import com.levanphoenix.restapp.DAL.CommentRepository;
import com.levanphoenix.restapp.DAL.PersonRepository;
import com.levanphoenix.restapp.DAL.PostRepository;
import com.levanphoenix.restapp.models.Comment;
import com.levanphoenix.restapp.models.Person;
import com.levanphoenix.restapp.models.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static java.lang.Long.parseLong;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    PostRepository postRepository;

    @RequestMapping("/get")
    public Comment getComment(@RequestParam String id){
        return commentRepository.findById(parseLong(id)).orElseThrow();
    }

    @RequestMapping("/add")
    public Comment addComment(@RequestParam String postId , @RequestParam String content ){
        Person person= personRepository.findById(1L).orElseThrow();
        Post post  = postRepository.findById(parseLong(postId)).orElseThrow();

        Comment comment = new Comment();
        comment.setPerson(person);
        comment.setPost(post);
        comment.setContent(content);

        return commentRepository.save(comment);

    }
    @RequestMapping("/update")
    public Comment editComment(@RequestParam String content, @RequestParam String id){

        Comment c = commentRepository.findById(parseLong(id)).orElseThrow();
        c.setContent(content);
        return commentRepository.save(c);
    }

    @RequestMapping("/delete")
    public String deleteComment(@RequestParam String id){
        try {
            commentRepository.deleteById(parseLong(id));
        }catch (Exception e){
            return e.toString();
        }
        return "successfully deleted a comment with id " + id;
    }
}
