package com.Gymweb.gymweb.auth;

import com.Gymweb.gymweb.config.JwtService;
import com.Gymweb.gymweb.entity.Member;
import com.Gymweb.gymweb.entity.Role;
import com.Gymweb.gymweb.entity.User;
import com.Gymweb.gymweb.error.ValidationException;
import com.Gymweb.gymweb.repository.MemberRepo;
import com.Gymweb.gymweb.service.BaseService;
import com.Gymweb.gymweb.service.MemberService;
import com.Gymweb.gymweb.utils.Securityutils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional(rollbackFor =  Exception.class)
public class MemberAuthenticationService extends BaseService<Member> {

    private final MemberService memberService;
    private final MemberRepo memberRepositroy;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService; // to generate the token
    private final AuthenticationManager authenticationManager;

    @Autowired
    private ModelMapper modelMapper;

    public AuthenticationResponse registerMember(RegisterMemberRequest request) throws ValidationException {

        Member member = modelMapper.map(request, Member.class);

        // Set fields that don't map well by default
        //member.setRelaxingAreas(request.getRelaxingAreas());
        //member.setClassNames(request.getClassname());
        member.setRole(Role.MEMBER);
        member.setStatus("expired");
        member.setPt(false);
        member.setPassword(passwordEncoder.encode(request.getPassword()));

        validateUnique(member);
        memberRepositroy.save(member);

        var jwtToken = jwtService.generateToken(member);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .role(Role.MEMBER)
                .build();
    }



    // this method if the user already authorized (have account) so it only generate a token for it

    public AuthenticationResponse authenticateMember(AuthenticationMemberRequest request) {
        var test = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        // In case user is authenticated
        var member = memberRepositroy.findByEmail(request.getEmail())
                .orElseThrow();

        HashMap<String, Object> extraCliams = new HashMap<>();

        var jwtToken = jwtService.generateToken(extraCliams, member);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .role(Role.MEMBER)
                .build();
    }





    protected void validateUnique(Member entity)  throws ValidationException {
        Optional<Member> existingMembers = memberRepositroy.findByEmail(entity.getUsername());
        if(existingMembers.isPresent()){
            throw new ValidationException("Member " +entity.getUsername()+ " already exist");
        }
    }
}
