package com.springboot.thymeleafdemo.service;

import com.springboot.thymeleafdemo.entity.Member;

import java.util.List;

public interface MemberService {

    List<Member> findAll();

    Member findById(int theId);

    Member save(Member theMember);

    void deleteById(int theId);

}
