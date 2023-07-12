package com.auth.server.api.service;

import com.auth.server.api.vo.MemberParamVo;

public interface MemberService {
    String login(MemberParamVo param);

    Boolean joinMember(MemberParamVo param);

    Boolean isEmailExist(String email);

}
