package com.springboot.thymeleafdemo.service;

import com.springboot.thymeleafdemo.dao.MemberRepository;
import com.springboot.thymeleafdemo.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService {

    private MemberRepository memberRepository;

    @Autowired
    public MemberServiceImpl(MemberRepository theMemberRepository) {
        memberRepository = theMemberRepository;
    }

    @Override
    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    @Override
    public Member findById(int theId) {
        Optional<Member> result = memberRepository.findById(theId);

        Member theMember = null;

        if (result.isPresent()) {
            theMember = result.get();
        }
        else {
            // we didn't find the member
            throw new RuntimeException("Did not find member id - " + theId);
        }

        return theMember;
    }

    @Override
    public Member save(Member theMember) {
        return memberRepository.save(theMember);
    }

    @Override
    public void deleteById(int theId) {
        memberRepository.deleteById(theId);
    }
}






