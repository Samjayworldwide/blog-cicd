package com.samjay.BlogTask.serviceImplementation;

import com.samjay.BlogTask.entity.AppUser;
import com.samjay.BlogTask.entity.Likes;
import com.samjay.BlogTask.entity.Post;
import com.samjay.BlogTask.repository.AppUserRepository;
import com.samjay.BlogTask.repository.LikeRepository;
import com.samjay.BlogTask.repository.PostRepository;
import com.samjay.BlogTask.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LikeServiceImplementation implements LikeService {
    private LikeRepository  likeRepository;
    private AppUserRepository appUserRepository;
    private PostRepository postRepository;
      @Autowired
    public LikeServiceImplementation(LikeRepository likeRepository,AppUserRepository appUserRepository,PostRepository postRepository) {
        this.likeRepository = likeRepository;
        this.appUserRepository = appUserRepository;
        this.postRepository = postRepository;
    }

    @Override
    public Likes addLike(Likes likes, Long userId,Long postId){


         Optional<AppUser> appUser = appUserRepository.findById(userId);

         if (appUser.isEmpty()){
             throw new RuntimeException("user cannot be found");
         }

        if (likes.getLikes() > 1 && appUser.get().getLikes().contains(likes)){
            throw new RuntimeException("You cannot like a post more than once");
        }

         Optional<Post> post = postRepository.findById(postId);
         if (post.isEmpty()){
             throw  new RuntimeException("post cannot be found");
         }

         appUser.get().addLikes(likes);
         post.get().addLikesToPost(likes);
         return likeRepository.save(likes);
    }
}
