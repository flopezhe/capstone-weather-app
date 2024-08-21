package farid.weather.data;

import farid.weather.models.AppUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@DataMongoTest
class AppUserRepositoryTest {

    @Autowired
    private AppUserRepository appUserRepository ;

    @Test
    void testFindByUsername() {
        AppUser appUser1 = new AppUser("1", "user1", "password1","random1");
        AppUser appUser2 = new AppUser("2", "user2", "password2","random2");

        appUserRepository.save(appUser1);
        appUserRepository.save(appUser2);

        AppUser user1 = appUserRepository.findByUserName("user1");

        assertNotNull(user1);
    }

    @Test
    void testSave () {
        AppUser appUser1 = new AppUser("1", "user1", "password1","random3");

        appUserRepository.save(appUser1);

        AppUser actual = appUserRepository.findById("1").orElse(null);

        assertEquals(appUser1, actual);

    }

}