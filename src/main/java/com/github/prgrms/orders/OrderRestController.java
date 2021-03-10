package com.github.prgrms.orders;

import com.github.prgrms.configures.web.Pageable;
import com.github.prgrms.configures.web.SimplePageRequest;
import com.github.prgrms.configures.web.SimplePageRequestHandlerMethodArgumentResolver;
import com.github.prgrms.errors.NotFoundException;
import com.github.prgrms.reviews.Review;
import com.github.prgrms.reviews.ReviewDto;
import com.github.prgrms.reviews.ReviewService;
import com.github.prgrms.security.JwtAuthentication;

import static com.github.prgrms.utils.ApiUtils.ApiResult;
import static com.github.prgrms.utils.ApiUtils.success;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/orders")
public class OrderRestController {
    // TODO findAll, findById, accept, reject, shipping, complete 메소드 구현이 필요합니다.

    private final OrderService orderService;
    private final ReviewService reviewService;

    public OrderRestController(OrderService orderService, ReviewService reviewService){
        this.orderService = orderService;
        this.reviewService = reviewService;
    }

    @GetMapping
    public ApiResult<List<OrderDto>> findAll(@AuthenticationPrincipal JwtAuthentication authentication,Pageable pageable){

        System.out.println("=============================================");
        System.out.println("simeplePageRequest : " + pageable);

        List<OrderDto> list = orderService.findAll(pageable).stream().map(OrderDto::new).collect(Collectors.toList());

        list.stream().forEach(dto -> {
            if(dto.getReview().getSeq() > 0) {
                dto.setReview(reviewService.findById(dto.getReview().getSeq()).map(ReviewDto::new).get());
            }
        });

        return success(list);
    }
}