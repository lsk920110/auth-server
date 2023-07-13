package com.auth.server.api.interceptor;

public class AuthParamVo implements AuthIf{
    private String authToken;

    /** 관리자 아이디 */
    private String adminId;

    /** 관리자 그룹 */
    private Integer adminGroup;

    @Override
    public String getAuthToken() {
        return authToken;
    }

    @Override
    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    @Override
    public String getAdminId() {
        return adminId;
    }

    @Override
    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    @Override
    public Integer getAdminGroup() {
        return adminGroup;
    }

    @Override
    public void setAdminGroup(Integer menu_seq) {
        this.adminGroup = menu_seq;
    }
}
