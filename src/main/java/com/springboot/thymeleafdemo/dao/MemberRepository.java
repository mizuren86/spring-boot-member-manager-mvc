package com.springboot.thymeleafdemo.dao;

import com.springboot.thymeleafdemo.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Integer> {

    public List<Member> findAll();

}
