package com.auth.server.api.vo;

import com.auth.server.api.entity.MemberEntity;
import lombok.Data;

@Data
public class MemberParamVo {

    private Long id;
    private String email;
    private String password;
    private String stat;
    private String auth_token;

    public MemberEntity makeMemberEntity(){
        MemberEntity entity = new MemberEntity();
        entity.setId(this.id);
        entity.setEmail(this.email);
        entity.setPassword(this.password);
        entity.setAuth_token(this.auth_token);
        entity.setStat(this.stat);
        return entity;
    }
}
