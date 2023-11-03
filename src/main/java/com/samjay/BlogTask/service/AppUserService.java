package com.samjay.BlogTask.service;

import com.samjay.BlogTask.dto.request.AppUserRequestDto;
import com.samjay.BlogTask.entity.AppUser;

public interface AppUserService {
AppUser createAnAppUser(AppUser appUser);
}

