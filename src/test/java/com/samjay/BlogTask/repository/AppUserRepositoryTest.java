package com.samjay.BlogTask.repository;

import com.samjay.BlogTask.entity.AppUser;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;


@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class AppUserRepositoryTest {

    @Autowired
    private AppUserRepository appUserRepository;



    @Test
    public void testIfTaskRepositorySavesANewAppUserToTheDataBase(){
        AppUser appUser = AppUser
                .builder()
                .username("kingsley")
                .build();

       AppUser savedAppUser = appUserRepository.save(appUser);
        Assertions.assertThat(savedAppUser).isNotNull();
        Assertions.assertThat(savedAppUser.getId()).isGreaterThan(0);
    }


    @Test
    public void testIfTaskRepositoryCanFindAnAppUserById(){
        AppUser appUser = AppUser
                .builder()
                .username("kingsley")
                .build();

        AppUser savedAppUser = appUserRepository.save(appUser);
        Optional<AppUser> appUserId = appUserRepository.findById(1L);
        Assertions.assertThat(savedAppUser).isNotNull();
        Assertions.assertThat(appUserId).isPresent();
    }

}
