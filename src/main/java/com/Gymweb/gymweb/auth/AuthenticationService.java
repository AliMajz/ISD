package com.Gymweb.gymweb.auth;

import com.Gymweb.gymweb.config.JwtService;
import com.Gymweb.gymweb.entity.Role;
import com.Gymweb.gymweb.entity.User;
import com.Gymweb.gymweb.error.ValidationException;
import com.Gymweb.gymweb.repository.UserRepo;
import com.Gymweb.gymweb.service.BaseService;
import com.Gymweb.gymweb.utils.Securityutils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor =  Exception.class)
public class AuthenticationService extends BaseService<User> {


    private final UserRepo userRepositroy;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService; // to generate the token
    private final AuthenticationManager authenticationManager;

    @Autowired
    private ModelMapper modelMapper;
    //Creating a user object out of register request, and after generating the user account it also generates a token
    public AuthenticationResponse register(RegisterRequest request) throws ValidationException {

//        // User user = map(request, User.class)
//        User user = User.builder()
//                .firstname(request.getFirstname())
//                .lastname(request.getLastname())
//                .email(request.getEmail())
//                .password(passwordEncoder.encode(request.getPassword()))
//                .role(Role.USER)
//                .department(null != request.getDepartment() ? request.getDepartment() : null)
//                .createdBy(Securityutils.getCurrentUsername())
//                .createdDate(LocalDate.now())
//                .build();
        User user = modelMapper.map(request, User.class);

        user.setRole(Role.ADMIN);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setCreatedBy(Securityutils.getCurrentUsername());
       // user.setCreatedDate(LocalDate.now());

        validateUnique(user); //no need for it because when calling save method in next step it will pass through it

        //userRepositroy.save(user);
        userRepositroy.save(user);

        //return authentication response contain token
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .role(Role.ADMIN)
                .build();
    }

    // this method if the user already authorized (have account) so it only generate a token for it
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        var test = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        // In case user is authenticated
        var user = userRepositroy.findByEmail(request.getEmail())
                .orElseThrow();

        HashMap<String, Object> extraCliams = new HashMap<>();

        var jwtToken = jwtService.generateToken(extraCliams, user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .role(user.getRole())
                .build();
    }

    @Override
    protected void validateUnique(User entity)  throws ValidationException {
        Optional<User> existingUsers = userRepositroy.findByEmail(entity.getUsername());
        if(existingUsers.isPresent()){
            throw new ValidationException("User " +entity.getUsername()+ " already exist");
        }
    }
}
