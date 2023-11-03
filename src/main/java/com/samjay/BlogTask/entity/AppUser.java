package com.samjay.BlogTask.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity(name = "AppUser")
@Table(name = "app_user")
public class AppUser {
    @Id
    @SequenceGenerator(
            name = "appUser_sequence",
            sequenceName = "appUser_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "appUser_sequence"
    )

    @Column(name = "id",
            updatable = false
    )
    private Long id;


    @Column(
            name = "user_name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String username;


    @OneToMany(
            mappedBy = "appUser",
            cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH}
    )
    private List<Post> posts = new ArrayList<>();


    @OneToMany(
             mappedBy = "appUser",
             cascade = {CascadeType.ALL},
             orphanRemoval = true
    )
    private List<Likes> likes = new ArrayList<>();

    @OneToMany(
            mappedBy = "appUser",
            cascade = {CascadeType.ALL},
            orphanRemoval = true
    )
    private List<Comment> comments = new ArrayList<>();


    public void addComments(Comment comment){
        if (comments == null){
            comments = new ArrayList<>();
        }
        comments.add(comment);
        comment.setAppUser(this);
    }
    public void  removeComments(Comment comment){
        if (comments == null){
            comments = new ArrayList<>();
        }
        comments.remove(comment);
        comment.setAppUser(this);
    }
    public void addLikes(Likes like){
        if (likes == null){
            likes = new ArrayList<>();
        }
        likes.add(like);

        like.setAppUser(this);
    }

    public void addPosts(Post post){
        if (posts == null){
            posts = new ArrayList<>();
        }
        posts.add(post);
        post.setAppUser(this);
    }

}
