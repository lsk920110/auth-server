package com.auth.server.api.controller;

import com.auth.server.api.constant.HeadersKey;
import com.auth.server.api.interceptor.RequestContext;
import com.auth.server.api.service.MemberService;
import com.auth.server.api.vo.MemberParamVo;
import com.auth.server.api.vo.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

    /**
     * 이메일 중복체크
     * @param email
     * @return
     */
    @GetMapping("/isEmailExist")
    public ResponseEntity<ApiResponse<Boolean>> isEmailExist (@RequestParam String email){

        Boolean result = memberService.isEmailExist(email);
        return ResponseEntity.ok().body(ApiResponse.createSuccess(result));
    }


    /**
     * 회원가입
     * @param param
     * @return
     */
    @PostMapping("/join")
    public ResponseEntity<ApiResponse<Boolean>> join(@RequestBody MemberParamVo param){
        Boolean result = memberService.joinMember(param);
        return ResponseEntity.ok().body(ApiResponse.createSuccess(result));
    }

    /**
     * 로그인
     * @param param
     * @return
     */
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<Boolean>> login(@RequestBody MemberParamVo param){

        //서비스에서 로그인 작업을 수행하고, 로그인 성공 결과(BOOLEAN)+AUTH-TOKEN을 발행한다.
        String auth_token = memberService.login(param);
        //response의 headers에 X-Token에 대입하고 반환한다.
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HeadersKey.X_TOKEN,auth_token);
        Boolean result = auth_token != null;

        return ResponseEntity.ok().headers(httpHeaders).body(ApiResponse.createSuccess(result));
    }


    @GetMapping("/test")
    public String test(HttpServletRequest request){
        RequestContext context = (RequestContext) request.getAttribute("request.context");
        log.info("context : {}",context.hashCode());
        if (context == null) return "null";
        String authToken = context.getAuth().getAuthToken();
        String adminId = context.getAuth().getAdminId();
        return authToken+" / "+adminId;
    }
}
