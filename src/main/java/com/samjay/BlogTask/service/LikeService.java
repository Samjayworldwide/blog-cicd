package com.samjay.BlogTask.service;

import com.samjay.BlogTask.entity.Likes;

public interface LikeService {
    Likes addLike(Likes like,Long userId,Long PostId);
}
