package farid.weather.domain;

import farid.weather.data.AppUserRepository;
import farid.weather.models.AppUser;
import org.springframework.stereotype.Service;

@Service
public class AppUserService {

    private final AppUserRepository appUserRepository;

    public AppUserService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    public Result<AppUser> createAppUser(AppUser appUser) {
        Result<AppUser> result = validateUser(appUser);

        if (result.isSuccess()) {
            appUserRepository.save(appUser);
        } else {
            result.addMessage("AppUser not saved", ResultType.INVALID);
        }

        return result;

    }

    public AppUser getAppUser(String userName) {
        return appUserRepository.findByUserName(userName);
    }

    public boolean deleteAppUser(String userName) {
        boolean result = appUserRepository.existsById(userName);

        if (result) {
            appUserRepository.deleteById(userName);
        }

        return result;
    }

    public Result<AppUser> updateAppUser(AppUser appUser) {
        Result<AppUser> result = validateUser(appUser);

        if (result.isSuccess()) {
            appUserRepository.save(appUser);
        } else {
            result.addMessage("AppUser not updated", ResultType.INVALID);
        }

        return result;
    }

    private Result<AppUser> validateUser(AppUser appUser) {

        Result<AppUser> result = new Result<>();

        if (appUser.getUserName() == null || appUser.getUserName().isEmpty()) {
            result.addMessage("Username cannot be empty", ResultType.INVALID);
        }
        if (appUser.getPassword() == null || appUser.getPassword().isEmpty()) {
            result.addMessage("Password cannot be empty", ResultType.INVALID);
        }

        return result;
    }



}
