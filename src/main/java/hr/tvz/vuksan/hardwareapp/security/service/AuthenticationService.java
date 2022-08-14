package hr.tvz.vuksan.hardwareapp.security.service;

import hr.tvz.vuksan.hardwareapp.security.command.LoginCommand;
import hr.tvz.vuksan.hardwareapp.security.dto.LoginDTO;

import java.util.Optional;

public interface AuthenticationService {

    Optional<LoginDTO> login(LoginCommand command);
}
