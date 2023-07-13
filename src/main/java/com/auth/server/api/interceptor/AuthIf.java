package com.auth.server.api.interceptor;

public interface AuthIf {
    /**
     * <pre>
     * 인증 토큰 : 헤더의 X-Token
     * </pre>
     *
     * @return 인증 토큰
     */
    public String getAuthToken();

    /**
     * @param authToken 인증 토큰
     */
    public void setAuthToken(String authToken);

    /**
     * <pre>
     * 관리자 아이디 : adminId
     * </pre>
     *
     * @return 관리자 아이디
     */
    public String getAdminId();

    /**
     * @param adminId 관리자 아이디
     */
    public void setAdminId(String adminId);

    /**
     * <pre>
     * 관리자 그룹 : menu_seq
     * </pre>
     *
     * @return 관리자 그룹
     */
    public Integer getAdminGroup();

    /**
     * @param menu_seq 관리자 그룹
     */
    public void setAdminGroup(Integer menu_seq);
}
