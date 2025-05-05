package com.Gymweb.gymweb.contoller;

import com.Gymweb.gymweb.entity.Member;
import com.Gymweb.gymweb.error.ValidationException;
import com.Gymweb.gymweb.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @PostMapping("/add")
    public Member addUser(@RequestBody Member member) throws ValidationException {
        return memberService.add(member);
    }


    @GetMapping("/list")
    public List<Member> viewUserList(){
        return memberService.fetchList();
    }

    @GetMapping("/email/{email}")
    public Member getMemberByEmail(@PathVariable String email) throws ValidationException {
        return memberService.findByEmail(email);
    }
}
