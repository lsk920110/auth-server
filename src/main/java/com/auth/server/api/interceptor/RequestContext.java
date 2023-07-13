package com.auth.server.api.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.util.StopWatch;
import org.springframework.web.util.UrlPathHelper;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class RequestContext {
    private AuthIf auth;

    /** 헤더 (내부 서비스에서 관리하는 헤더 정보) */
    protected Map<String, String> headerMap;

    /** 요청 헤더 */
    private String requestHeader;

    /** 요청 시간 */
    private String requestTime;

    /** 응답 시간 */
    private String responseTime;

    /** 요청 URI */
    private String requestURI;

    /** 요청 메소드 */
    private String requestMethod;

    /** 접속 아이피 */
    private String remoteAddr;

    /** 언어 */
    private String language;

    /** 지역정보 */
    private Locale locale;

    /** referer host 정보 (헤더에 존재하는 경우 설정) */
    private String referrerHost;

    /** URL Helper */
    private UrlPathHelper urlPathHelper;

    /** 오류정보 */
    private Exception ex;

    /** 추적아이디 */
    private String traceId;

    /**
     * 생성자
     */
    public RequestContext() {

        headerMap = new HashMap<String, String>();
    }

    /**
     * 생성자
     *
     * @param urlPathHelper
     */
    public RequestContext(UrlPathHelper urlPathHelper) {

        this();
        this.urlPathHelper = urlPathHelper;
    }

    /**
     * 기본 컨텍스트 정보 빌드
     *
     * @param request
     * @param handler
     * @throws IOException
     */
    public void build(HttpServletRequest request, Object handler) throws IOException {

        // 요청시간 저장
//        requestTime = DateTimeUtil.getDateToString(new Date(), DateTimeUtil.TIMESTAMP_LONG_PATTERN);

        // URI 저장
        final String requestURI = request.getRequestURI();
        if (null != urlPathHelper) {
            this.requestURI = urlPathHelper.getLookupPathForRequest(request);
        } else {
            this.requestURI = requestURI;
        }
        this.requestMethod = request.getMethod().toUpperCase();

        // 기본은 IPv6 형식이며, IPv4 형태로 받고자한다면 tomcat인 경우 "-Djava.net.preferIPv4Stack=true"
        // 옵션을 추가한다.
//        this.remoteAddr = HttpServletRequestUtil.getRemoteAddress(request);

        // 지역 및 언어 정보 저장
        this.locale = request.getLocale(); // based on the Accept-Language header
        this.language = locale.getLanguage();

        // referrer host 정보 설정 (옵션정보)
//        this.referrerHost = HttpServletRequestUtil.getReferrerHost(request);

//        this.requestHeader = HttpServletRequestUtil.getAllHeaderString(request);
        // 서비스에서 사용할 헤더정보 저장
//		putHeader(request, /** 헤더키 */);
    }

    /**
     * 헤더 정보 저장
     *
     * @param request
     * @param key
     * @throws UnsupportedEncodingException
     */
//    void putHeader(HttpServletRequest request, String key) throws UnsupportedEncodingException {
//        String value = HttpServletRequestUtil.getHeader(request, key);
//        if (null != value) {
//            headerMap.put(key, value);
//        }
//    }

    public String getHeader(String key) {

        return headerMap.containsKey(key) ? headerMap.get(key) : null;
    }

    public String getRequestTime() {

        return requestTime;
    }

    public String getResponseTime() {

        return responseTime;
    }

    public String getRequestURI() {

        return requestURI;
    }

    public String getRequestMethod() {

        return requestMethod;
    }

    public String getRemoteAddr() {

        return remoteAddr;
    }

    public String getLanguage() {

        return language;
    }

    public Locale getLocale() {

        return locale;
    }

    public String getReferrerHost() {

        return referrerHost;
    }







    public AuthIf getAuth() {
        return auth;
    }

    public void setAuth(AuthIf auth) {
        this.auth = auth;
    }


    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }
}
