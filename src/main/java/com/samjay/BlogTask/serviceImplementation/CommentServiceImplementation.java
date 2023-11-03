package com.samjay.BlogTask.serviceImplementation;

import com.samjay.BlogTask.dto.request.CommentRequestDto;
import com.samjay.BlogTask.entity.*;
import com.samjay.BlogTask.repository.AppUserRepository;
import com.samjay.BlogTask.repository.CommentRepository;
import com.samjay.BlogTask.repository.PostRepository;
import com.samjay.BlogTask.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImplementation implements CommentService {
    private CommentRepository commentRepository;
    private AppUserRepository appUserRepository;
    private PostRepository postRepository;
      @Autowired
    public CommentServiceImplementation(CommentRepository commentRepository,
                                        AppUserRepository appUserRepository,
                                        PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.appUserRepository = appUserRepository;
        this.postRepository = postRepository;

    }

    @Override
    public Comment addComment(Comment comment,Long userId,Long postId) {


        Optional<AppUser> appUser = appUserRepository.findById(userId);
        if (appUser.isEmpty()) {
            throw new RuntimeException("user with " + userId +" not found");
        }


        Optional<Post> post = postRepository.findById(postId);
        if (post.isEmpty()){
            throw  new RuntimeException("post with " + postId + " not found");
        }

        appUser.get().addComments(comment);
        post.get().addCommentToPost(comment);
        return commentRepository.save(comment);

    }
    @Override
    public void deleteComment(Long id,Long userId) {
        Optional<Comment> comment = commentRepository.findById(id);
        Optional<AppUser> appUser = appUserRepository.findById(userId);
        if (appUser.isEmpty()){
            throw new RuntimeException("invalid user id");
        }
        if (comment.isPresent()){
            appUser.get().removeComments(comment.get());
            commentRepository.deleteById(id);
        } throw new RuntimeException("comment is not found");
    }

    @Override
    public List<Comment> getAllCommentsForAPost(Long postId) {
       boolean postExists = postRepository.findById(postId).isPresent();
       if (!postExists){
           throw new RuntimeException("post cannot be found");

       }else return commentRepository.findCommentsByPostsId(postId);

    }


}
