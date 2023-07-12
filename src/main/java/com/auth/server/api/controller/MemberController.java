package com.auth.server.api.controller;

import com.auth.server.api.service.MemberService;
import com.auth.server.api.vo.MemberParamVo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/isEmailExist")
    public ResponseEntity<Boolean> isEmailExist (@RequestParam String email){

        Boolean result = memberService.isEmailExist(email);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @PostMapping("/join")
    public ResponseEntity<Boolean> join(@RequestBody MemberParamVo param){
        Boolean result = memberService.joinMember(param);
        return new ResponseEntity<Boolean>(result,HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<Boolean> login(@RequestBody MemberParamVo param){

        //서비스에서 로그인 작업을 수행하고, 로그인 성공 결과(BOOLEAN)+AUTH-TOKEN을 발행한다.
        String auth_token = memberService.login(param);
        //response의 headers에 X-Token에 대입하고 반환한다.
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("X-TOKEN",auth_token);
        Boolean result = auth_token != null;

        return new ResponseEntity<Boolean>(result,httpHeaders,HttpStatus.MULTI_STATUS);
    }
}
