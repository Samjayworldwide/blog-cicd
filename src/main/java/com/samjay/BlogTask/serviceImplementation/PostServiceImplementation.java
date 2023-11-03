package com.samjay.BlogTask.serviceImplementation;

import com.samjay.BlogTask.entity.AppUser;
import com.samjay.BlogTask.entity.Post;
import com.samjay.BlogTask.repository.AppUserRepository;
import com.samjay.BlogTask.repository.PostRepository;
import com.samjay.BlogTask.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImplementation implements PostService {
    private PostRepository postRepository;
    private AppUserRepository appUserRepository;
      @Autowired
    public PostServiceImplementation(PostRepository postRepository,AppUserRepository appUserRepository) {
        this.postRepository = postRepository;
        this.appUserRepository = appUserRepository;
    }

     @Override
    public Post createPost(Post post,Long id) {
        Optional<AppUser> appUser = appUserRepository.findById(id);
        if (appUser.isEmpty()){
            throw new RuntimeException("user with " + id +" not found");
        }
        if (appUser.get().getId() > 1){
            throw new RuntimeException("user cannot write a post");
        }
          appUser.get().addPosts(post);
          return postRepository.save(post);
    }

    @Override
    public Post findAPost(String postContent) {
        Post searchResults = postRepository.findPostByContent(postContent);
        if (searchResults.getContent().isEmpty()){
            throw new RuntimeException("post not found");
        }
        return searchResults;
    }

//    @Override
//    public Post findPostByCategory(String category) {
//        return postRepository.findPostWithComments(category);
//    }

    @Override
    public void deleteAPost(Long userId, Long postId) {
        Optional<AppUser> appUser = appUserRepository.findById(userId);
        if (appUser.isEmpty()){
            throw new RuntimeException("user is not found");
        }
        if (appUser.get().getId() > 1){
            throw new RuntimeException("user cannot delete a post");
        }
        Optional<Post> post = postRepository.findById(postId);
        if (post.isEmpty()){
            throw new RuntimeException("post is not found");
        }
            postRepository.deleteById(postId);

    }
}
