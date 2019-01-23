package com.ssm.common.controller;

import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.Serializable;

public abstract class BaseController<T> implements Serializable {

    private static final long serialVersionUID = 3784994171905825243L;

    private HttpServletRequest request;

    private HttpServletResponse response;

    private HttpSession session;

    public RESTUIModel RESTUIModel = new RESTUIModel();

    public BaseController() {

    }

    public RESTUIModel add(final T t) {
        return null;
    }

    public RESTUIModel update(final T t) {
        return null;
    }

    public RESTUIModel delete(final String id) {
        return null;
    }

    public RESTUIModel update(final T t1, final String t2){
        return null;
    }
    @ModelAttribute
    public void setRequestAndResponse(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        this.session = request.getSession();
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public HttpSession getSession() {
        return session;
    }
}
