package com.github.prgrms.orders;

import com.github.prgrms.configures.web.Pageable;
import com.github.prgrms.configures.web.SimplePageRequest;
import com.github.prgrms.configures.web.SimplePageRequestHandlerMethodArgumentResolver;
import com.github.prgrms.security.JwtAuthentication;

import static com.github.prgrms.utils.ApiUtils.ApiResult;
import static com.github.prgrms.utils.ApiUtils.success;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/orders")
public class OrderRestController {
    // TODO findAll, findById, accept, reject, shipping, complete 메소드 구현이 필요합니다.

    @GetMapping
    public ApiResult<List<Orders>> findAll(@AuthenticationPrincipal JwtAuthentication authentication,Long offset, int size){

        Pageable pageable = new SimplePageRequest(offset,size);

        SimplePageRequestHandlerMethodArgumentResolver resolver = new SimplePageRequestHandlerMethodArgumentResolver();

        resolver.setOffsetParameterName("offset");
        resolver.setSizeParameterName("size");

        return null;
    }
}