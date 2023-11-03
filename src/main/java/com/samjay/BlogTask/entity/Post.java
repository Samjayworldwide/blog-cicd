package com.samjay.BlogTask.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity(name = "Post")
@Table(name = "post")

public class Post {
    @Id
    @SequenceGenerator(
            name = "post_sequence",
            sequenceName = "post_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "post_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;


    @Column(
            name = "content",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String content;



    @Column(
            name = "category",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String category;


    @OneToMany(
            mappedBy = "posts",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<Comment> comments = new ArrayList<>();



    @ManyToOne(
            cascade = {CascadeType.DETACH,CascadeType.PERSIST,CascadeType.REFRESH,CascadeType.MERGE},
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "app_user_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "app_user_post_fk"
            )
    )
    private AppUser appUser;


    @OneToMany(
            mappedBy = "posts",
            cascade = {CascadeType.ALL}
    )
    private List<Likes> likes = new ArrayList<>();

    public void addCommentToPost(Comment comment){
        if (comments == null){
            comments = new ArrayList<>();
        }
        comments.add(comment);
        comment.setPosts(this);
    }
    public void addLikesToPost(Likes like){
        if (likes == null){
          likes = new ArrayList<>();
        }
        likes.add(like);
        like.setPosts(this);
    }
}
