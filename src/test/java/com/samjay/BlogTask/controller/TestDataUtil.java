package com.samjay.BlogTask.controller;

import com.samjay.BlogTask.dto.request.AppUserRequestDto;
import com.samjay.BlogTask.entity.AppUser;

public final class TestDataUtil {

    public TestDataUtil() {
    }

    public static AppUser createTestAppUserA(){
        return AppUser.
                builder()
                .id(1L)
                .username("samuel")
                .build();
    }
}
