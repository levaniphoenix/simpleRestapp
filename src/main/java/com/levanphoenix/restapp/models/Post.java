package com.levanphoenix.restapp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private String content;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "person_id")
    private Person postOwner;

    @OneToMany(mappedBy = "post")
    private List<Comment> commnets;

    public Person getPostOwner() {
        return postOwner;
    }

    public void setPostOwner(Person postOwner) {
        this.postOwner = postOwner;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
