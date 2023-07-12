package com.auth.server.api.service;

import com.auth.server.api.entity.MemberEntity;
import com.auth.server.api.repository.MemberRepository;
import com.auth.server.api.vo.MemberParamVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    @Override
    public String login(MemberParamVo param) {

        MemberEntity memberInfo = memberRepository.findByEmail(param.getEmail());
        if(memberInfo == null){
            return null;
        }

        String password = memberInfo.getPassword();
        boolean password_match = param.getPassword().equals(password);

        String auth_token = password_match ? UUID.randomUUID().toString() : null;
        memberInfo.setAuth_token(auth_token);
        memberRepository.save(memberInfo);
        return auth_token;
    }

    @Override
    public Boolean joinMember(MemberParamVo param) {

        if(isEmailExist(param.getEmail())){
            return false;
        };

        //passwordEncoding 추가
        MemberEntity memberEntity = param.makeMemberEntity();
        memberRepository.save(memberEntity);
        return true;
    }

    @Override
    public Boolean isEmailExist(String email) {
        Integer count = memberRepository.countByEmail(email);
        return count > 0;
    }
}
