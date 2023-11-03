package com.samjay.BlogTask.repository;


import com.samjay.BlogTask.entity.AppUser;
import com.samjay.BlogTask.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser,Long> {

}
