package com.samjay.BlogTask.mappers.implementation;

import com.samjay.BlogTask.dto.request.AppUserRequestDto;
import com.samjay.BlogTask.entity.AppUser;
import com.samjay.BlogTask.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AppUserMapperImplementation implements Mapper<AppUser, AppUserRequestDto> {
    private ModelMapper modelMapper;

    public AppUserMapperImplementation(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public AppUserRequestDto mapTo(AppUser appUser) {
      return  modelMapper.map(appUser,AppUserRequestDto.class);
    }

    @Override
    public AppUser mapFrom(AppUserRequestDto appUserRequestDto) {
        return modelMapper.map(appUserRequestDto,AppUser.class);
    }
}
