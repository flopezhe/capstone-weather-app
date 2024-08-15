package farid.weather.models;

import org.springframework.data.annotation.Id;

public class AppUser {

    @Id
    private String id;


    private String userName;


    private String password;
}
