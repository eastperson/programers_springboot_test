package com.github.prgrms.orders;

import com.github.prgrms.configures.web.Pageable;
import com.github.prgrms.configures.web.SimplePageRequest;
import com.github.prgrms.configures.web.SimplePageRequestHandlerMethodArgumentResolver;
import com.github.prgrms.errors.EmptyRequestBodyException;
import com.github.prgrms.errors.NotFoundException;
import com.github.prgrms.reviews.Review;
import com.github.prgrms.reviews.ReviewDto;
import com.github.prgrms.reviews.ReviewService;
import com.github.prgrms.security.JwtAuthentication;

import static com.github.prgrms.utils.ApiUtils.ApiResult;
import static com.github.prgrms.utils.ApiUtils.error;
import static com.github.prgrms.utils.ApiUtils.success;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/orders")
public class OrderRestController {

    private final OrderService orderService;
    private final ReviewService reviewService;

    public OrderRestController(OrderService orderService, ReviewService reviewService){
        this.orderService = orderService;
        this.reviewService = reviewService;
    }

    @GetMapping
    public ApiResult<List<OrderDto>> findAll(@AuthenticationPrincipal JwtAuthentication authentication,Pageable pageable){

        List<OrderDto> list = orderService.findAll(pageable).stream().map(OrderDto::new).collect(Collectors.toList());

        list.stream().forEach(dto -> {
            if(dto.getReview().getSeq() > 0) {
                dto.setReview(reviewService.findById(dto.getReview().getSeq()).map(ReviewDto::new).get());
            } else {
                dto.setReview(null);
            }
        });

        return success(list);
    }

    @GetMapping("/{id}")
    public ApiResult<OrderDto> findById(@AuthenticationPrincipal JwtAuthentication authentication, @PathVariable Long id){

        OrderDto dto = orderService.findById(id).map(OrderDto::new).orElseThrow(() -> new NotFoundException("Could not found product for " + id));

        if(dto.getReview().getSeq() > 0){
            dto.setReview(reviewService.findById(dto.getReview().getSeq()).map(ReviewDto::new).get());
        } else {
            dto.setReview(null);
        }

        return success(dto);

    }

    @PatchMapping("/{id}/accept")
    public ApiResult<Boolean> accept(@AuthenticationPrincipal JwtAuthentication authentication,@PathVariable Long id){

        Orders orders = orderService.findById(id).orElseThrow(() -> new NotFoundException("Could not found product for " + id));

        if(!orders.getState().equals(State.REQUESTED.toString())){
            return success(false);
        }

        orderService.updateStateToAccepted(orders);

        return success(true);
    }

    @PatchMapping("/{id}/reject")
    public ApiResult<Boolean> reject(@AuthenticationPrincipal JwtAuthentication authentication,@PathVariable Long id, @Valid @RequestBody(required = false) RejectRequest rejectRequest){

        if(rejectRequest == null){
            throw new EmptyRequestBodyException("Could not found request body for reject request");
        }

        if(rejectRequest.getMessage() == null){
            return success(false);
        }

        Orders orders = orderService.findById(id).orElseThrow(() -> new NotFoundException("Could not found product for " + id));

        if(!orders.getState().equals(State.REQUESTED.toString())){
            return success(false);
        }

        orderService.updateStateToRejected(orders,rejectRequest.getMessage());

        return success(true);

    }

    @PatchMapping("/{id}/shipping")
    public ApiResult<Boolean> shipping(@AuthenticationPrincipal JwtAuthentication authentication,@PathVariable Long id){

        Orders orders = orderService.findById(id).orElseThrow(() -> new NotFoundException("Could not found product for " + id));

        if(!orders.getState().equals(State.ACCEPTED.toString())){
            return success(false);
        }

        orderService.updateStateToShipping(orders);

        return success(true);
    }

    @PatchMapping("/{id}/complete")
    public ApiResult<Boolean> complete(@AuthenticationPrincipal JwtAuthentication authentication,@PathVariable Long id){

        Orders orders = orderService.findById(id).orElseThrow(() -> new NotFoundException("Could not found product for " + id));

        if(!orders.getState().equals(State.SHIPPING.toString())){
            return success(false);
        }

        orderService.updateStateToComplete(orders);

        return success(true);
    }
}