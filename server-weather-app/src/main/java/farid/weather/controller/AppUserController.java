package farid.weather.controller;

import farid.weather.domain.AppUserService;
import farid.weather.domain.Result;
import farid.weather.models.AppUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/app_user")
public class AppUserController {

    private final AppUserService appUserService;

    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @PostMapping
    public ResponseEntity<Object> add(AppUser appUser) {
        Result<AppUser> result = appUserService.createAppUser(appUser);

        if(result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }

        return ErrorResponse.build(result);
    }

    @DeleteMapping("/{appUserId}")
    public ResponseEntity<Void> delete(@PathVariable String appUserId) {
        if(appUserService.deleteAppUser(appUserId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
