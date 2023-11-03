package com.samjay.BlogTask.serviceImplementation;

import com.samjay.BlogTask.dto.request.AppUserRequestDto;
import com.samjay.BlogTask.entity.AppUser;
import com.samjay.BlogTask.entity.Comment;
import com.samjay.BlogTask.repository.AppUserRepository;
import com.samjay.BlogTask.repository.CommentRepository;
import com.samjay.BlogTask.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppUserServiceImplementation implements AppUserService {
    private AppUserRepository appUserRepository;

      @Autowired
    public AppUserServiceImplementation(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Override
    public AppUser createAnAppUser(AppUser appUser) {
        return appUserRepository.save(appUser);
    }


}
