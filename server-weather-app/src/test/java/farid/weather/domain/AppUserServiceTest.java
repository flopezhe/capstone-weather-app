package farid.weather.domain;

import farid.weather.data.AppUserRepository;
import farid.weather.models.AppUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


@SpringBootTest
class AppUserServiceTest {

    @MockBean
    AppUserRepository appUserRepository;

    @Autowired
    AppUserService appUserService;

    @Test
    void getAppUsers() {
        AppUser appUser1 = new AppUser("1", "user1", "password1","random1");
    }


    @Test
    void createAppUser() {
    }

    @Test
    void getAppUser() {
    }

    @Test
    void deleteAppUser() {
    }

    @Test
    void updateAppUser() {
    }
}