package com.github.prgrms.configures.web;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class SimplePageRequestHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

    private static final String DEFAULT_OFFSET_PARAMETER = "offset";

    private static final String DEFAULT_SIZE_PARAMETER = "size";

    private static final long DEFAULT_OFFSET = 0;

    private static final int DEFAULT_SIZE = 5;

    private String offsetParameterName = DEFAULT_OFFSET_PARAMETER;

    private String sizeParameterName = DEFAULT_SIZE_PARAMETER;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return Pageable.class.isAssignableFrom(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(
            MethodParameter methodParameter,
            ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory
    ) {

        String offsetString = webRequest.getParameter(offsetParameterName);
        String sizeString = webRequest.getParameter(sizeParameterName);

        long offset = DEFAULT_OFFSET;
        int size = DEFAULT_SIZE;

        if(offsetString != null){
            offset = Long.parseLong(offsetString);
        }

        if(sizeString != null) {
            size = Integer.parseInt(sizeString);
        }

        if(offset < 1 || Long.MAX_VALUE < offset){
            offset = DEFAULT_OFFSET;
        }

        if(size < 1 || 5 < size) {
            size = DEFAULT_SIZE;
        }

        return new SimplePageRequest(offset,size);
    }

    public void setOffsetParameterName(String offsetParameterName) {
        this.offsetParameterName = offsetParameterName;
    }

    public void setSizeParameterName(String sizeParameterName) {
        this.sizeParameterName = sizeParameterName;
    }

}