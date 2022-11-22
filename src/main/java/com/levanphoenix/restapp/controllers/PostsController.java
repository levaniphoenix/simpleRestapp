package com.levanphoenix.restapp.controllers;

import com.github.javafaker.Faker;
import com.levanphoenix.restapp.DAL.PersonRepository;
import com.levanphoenix.restapp.DAL.PostRepository;
import com.levanphoenix.restapp.models.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static java.lang.Long.parseLong;

@RestController
@RequestMapping("/posts")
public class PostsController {

    @Autowired
    PostRepository postRepository;

    @Autowired
    PersonRepository personRepository;

    @RequestMapping("/get")
    public Post getPost(@RequestParam String id){
        return postRepository.findById(parseLong(id)).orElseThrow();
    }

    @RequestMapping("/add")
    public Post addPost(){
        Faker faker = new Faker();
        Post post = new Post();
        post.setTitle(faker.job().title());
        post.setContent(faker.lorem().paragraph());
        post.setPostOwner(personRepository.findById(1L).orElseThrow());
        return postRepository.save(post);
    }

    @RequestMapping("/update")
    public Post editPost(@RequestParam String title,@RequestParam String content, @RequestParam String id){
        Post p = postRepository.findById(parseLong(id)).orElseThrow();
        p.setContent(content);
        p.setTitle(title);
        return postRepository.save(p);
    }

    @RequestMapping("/delete")
    public String deletePost(@RequestParam String id){
        try {
            postRepository.deleteById(parseLong(id));
        }catch (Exception e){
            return e.toString();
        }
        return "successfully deleted a post with id " + id;
    }
}
