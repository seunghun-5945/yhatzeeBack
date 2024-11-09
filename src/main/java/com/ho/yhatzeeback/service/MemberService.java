package com.ho.yhatzeeback.service;

import com.ho.yhatzeeback.DTO.RankingResponse;
import com.ho.yhatzeeback.entity.Member;
import com.ho.yhatzeeback.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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

    public void update(String name,int score)
    {
        Member member = memberRepository.findByName(name)
                        .orElseThrow(() -> new RuntimeException("Invalid credentials"));
        member.updateScore(score);
        memberRepository.save(member);

    }

    public List<RankingResponse> topten()
    {
        return memberRepository.findTop10ByOrderByScoreDesc().stream()
                .map(member -> new RankingResponse(member.getName(), member.getScore()))
                .collect(Collectors.toList());
    }


}
