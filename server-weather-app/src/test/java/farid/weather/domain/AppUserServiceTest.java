package farid.weather.domain;

import farid.weather.data.AppUserRepository;
import farid.weather.models.AppUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@SpringBootTest
class AppUserServiceTest {

    @MockBean
    AppUserRepository appUserRepository;

    @Autowired
    AppUserService appUserService;

    @Test
    void saveAppUsers() {
        AppUser appUser1 = new AppUser("5", "user1", "password1","random1");
        AppUser appUser2 = new AppUser("1", "user1", "password1","random1");
        AppUser appUser3 = new AppUser("1", "user1", "password1","random1");

        AppUser arg = appUser1;
        arg.setId("0");

        when(appUserRepository.save(any())).thenReturn(appUser2);


        Result<AppUser> expected = new Result<>();
        expected.setPayload(appUser2);
        Result<AppUser> actual = appUserService.createAppUser(appUser1);
        actual.setPayload(appUser3);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddAppUserWithoutUsername(){
        AppUser appUser1 = new AppUser ("5", "", "password1","random1");

        Result<AppUser> expected = appUserService.createAppUser(appUser1);

        assertFalse(expected.isSuccess());


    }

    @Test
    void shouldNotAddUserWithoutPassword(){
        AppUser appUser1 = new AppUser ("5", "user1", "","random1");

        Result<AppUser> expected = appUserService.createAppUser(appUser1);

        assertFalse(expected.isSuccess());
    }


    @Test
    void getAppUser() {
        AppUser appUser1 = new AppUser("5", "user1", "password1","random1");

        when(appUserRepository.findByUserName("user1")).thenReturn(appUser1);

        AppUser expected = appUser1;
        AppUser actual = appUserService.getAppUser("user1");

        assertEquals(expected, actual);
    }

    @Test
    void deleteAppUser() {
        AppUser appUser1 = new AppUser("5", "user1", "password1","random1");

        when(appUserRepository.existsById("user1")).thenReturn(true);

        boolean expected = true;
        boolean actual = appUserService.deleteAppUser("user1");

        assertEquals(expected, actual);
    }

    @Test
    void updateAppUser() {
        AppUser appUser1 = new AppUser("1", "user1", "password1","random1");

        AppUser appUser2 = new AppUser("5", "user1", "password1","random1");
        appUser2.setId("1");


        when(appUserRepository.save(any())).thenReturn(appUser1);

        Result<AppUser> expected = new Result<>();
        expected.setPayload(appUser1);
        Result<AppUser> actual = appUserService.updateAppUser(appUser1);
        actual.setPayload(appUser1);

        assertEquals(expected, actual);

    }
}