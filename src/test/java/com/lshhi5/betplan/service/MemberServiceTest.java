package com.lshhi5.betplan.service;

import com.lshhi5.betplan.domain.Member;
import com.lshhi5.betplan.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    public void 회원가입() {
        //given
        String name = "memberA";
        String phoneNumber = "119";
        Long joinId = memberService.join(Member.builder()
                .name(name)
                .phoneNumber(phoneNumber)
                .build());

        //when
        Member findMember = memberService.findMember(joinId);
        Member member = memberRepository.findOne(joinId);

        //then
        assertThat(findMember).isEqualTo(member);
    }

    @Test
    public void 중복예외() {
        //given
        String nameA = "memberA";
        String phoneNumberA = "119";
        Long joinIdA = memberService.join(Member.builder()
                .name(nameA)
                .phoneNumber(phoneNumberA)
                .build());

        //when
        String nameB = "memberA";
        String phoneNumberB = "112";

        //then
        assertThrows(IllegalStateException.class, () -> memberService.join(Member.builder()
                .name(nameB)
                .phoneNumber(phoneNumberB)
                .build()));
    }
}