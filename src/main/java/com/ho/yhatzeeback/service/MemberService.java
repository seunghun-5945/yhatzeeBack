package com.ho.yhatzeeback.service;

import com.ho.yhatzeeback.entity.Member;
import com.ho.yhatzeeback.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public void createMember(String name,String password){
        memberRepository.save(Member.builder()
                .name(name)
                .password(password)
                .build());

    }
    public boolean alreadyUsingname(String name)
    {
        return memberRepository.findByName(name).isPresent();

    }
    public String loginMember(String name, String password) {
        Member member = memberRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));
        if (!member.getPassword().equals(password)) {
            throw new RuntimeException("Invalid credentials");
        }
        return member.getName();
    }
}
