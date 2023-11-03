package com.samjay.BlogTask.controller;

import com.samjay.BlogTask.dto.request.AppUserRequestDto;
import com.samjay.BlogTask.entity.AppUser;
import com.samjay.BlogTask.mappers.Mapper;
import com.samjay.BlogTask.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppUserController {
   private AppUserService appUserService;
   private Mapper<AppUser,AppUserRequestDto> appUserMapper;
     @Autowired
    public AppUserController(AppUserService appUserService,Mapper<AppUser,AppUserRequestDto> appUserMapper) {
        this.appUserService = appUserService;
        this.appUserMapper = appUserMapper;
    }

    @PostMapping(path = "/createAppUser")
   public ResponseEntity<AppUserRequestDto> createAnAppUser(@RequestBody AppUserRequestDto appUserRequestDto) {
        AppUser appUser = appUserMapper.mapFrom(appUserRequestDto);
     AppUser savedUser = appUserService.createAnAppUser(appUser);
     return new ResponseEntity<>(appUserMapper.mapTo(savedUser), HttpStatus.CREATED);
  }


}
