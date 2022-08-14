package hr.tvz.vuksan.hardwareapp.security.service;

import hr.tvz.vuksan.hardwareapp.security.domain.User;

public interface JwtService {

    boolean authenticate(String token);
    String createJwt(User user);
}
