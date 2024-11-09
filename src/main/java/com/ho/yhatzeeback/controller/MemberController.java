package com.ho.yhatzeeback.controller;

import com.ho.yhatzeeback.DTO.*;
import com.ho.yhatzeeback.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    @PostMapping("/register")
    public void register(@RequestBody RegisterRequest DTO)
    {
        memberService.createMember(DTO.getName(), DTO.getPassword());
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest DTO) {
        String Response = memberService.loginMember(DTO.getName(),DTO.getPassword());
        return Response;
    }
    @PostMapping("/alreadyusingname")
    public boolean alreadyUsingName(@RequestBody NameRequest DTO)
    {
        return memberService.alreadyUsingname(DTO.getName());
    }
    @PostMapping("/ranking")
    public List<RankingResponse> rankingtopten()
    {
        return memberService.topten();
    }
    @PostMapping("/update")
    public void updateranking(@RequestBody UpdateRequest DTO)
    {
         memberService.update(DTO.getName(),DTO.getScore());
    }

}
