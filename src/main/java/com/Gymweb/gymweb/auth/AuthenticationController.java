package com.Gymweb.gymweb.auth;

import com.Gymweb.gymweb.error.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user-auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;
    private final MemberAuthenticationService memberAuthenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) throws ValidationException {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate (@RequestBody AuthenticationRequest request)
    {
        return ResponseEntity.ok(service.authenticate(request));
    }

    @PostMapping("/register/member")
    public ResponseEntity<AuthenticationResponse> registerMember(@RequestBody RegisterMemberRequest request) throws ValidationException {
        return ResponseEntity.ok(memberAuthenticationService.registerMember(request));
    }

//    @PostMapping("/authenticate/member")
//    public ResponseEntity<AuthenticationResponse> authenticateMember (@RequestBody AuthenticationMemberRequest request)
//    {
//        return ResponseEntity.ok(memberAuthenticationService.authenticateMember(request));
//    }
}

