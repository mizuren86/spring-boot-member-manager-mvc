package com.springboot.thymeleafdemo.controller;

import com.springboot.thymeleafdemo.entity.Member;
import com.springboot.thymeleafdemo.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/members")
public class MemberController {

    private MemberService memberService;

    public MemberController(MemberService theMemberService) {
        memberService = theMemberService;
    }

    // add a request mapping for /leaders
    @GetMapping("/leaders")
    public String showLeaders(){

        return "members/leaders";
    }

    // add a request mapping for /systems
    @GetMapping("/systems")
    public String showSystems(){

        return "members/systems";
    }

    @GetMapping("/list")
    public String listMembers(Model theModel) {
        List<Member> theMembers = memberService.findAll();
        theModel.addAttribute("members", theMembers);
        return "members/list-members";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {
        Member theMember = new Member();
        theModel.addAttribute("member", theMember);
        return "members/member-form";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("memberId") int theId, Model theModel) {
        Member theMember = memberService.findById(theId);
        theModel.addAttribute("member", theMember);
        return "members/member-form";
    }

    @PostMapping("/save")
    public String saveMember(@ModelAttribute("member") Member theMember) {

        memberService.save(theMember);
        return "redirect:/members/list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("memberId") int theId) {
        memberService.deleteById(theId);
        return "redirect:/members/list";
    }

}

