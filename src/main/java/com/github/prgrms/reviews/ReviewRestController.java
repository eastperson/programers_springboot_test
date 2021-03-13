package com.github.prgrms.reviews;

import static com.github.prgrms.utils.ApiUtils.ApiResult;
import static com.github.prgrms.utils.ApiUtils.success;
import static com.github.prgrms.utils.ApiUtils.error;

import com.github.prgrms.errors.AlreadyExistException;
import com.github.prgrms.errors.NotAllowedStateException;
import com.github.prgrms.errors.NotFoundException;
import com.github.prgrms.orders.Orders;
import com.github.prgrms.orders.OrderService;
import com.github.prgrms.orders.State;
import com.github.prgrms.products.ProductService;
import com.github.prgrms.security.JwtAuthentication;
import com.github.prgrms.users.User;
import com.github.prgrms.users.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("api/orders")
public class ReviewRestController {
    // TODO review 메소드 구현이 필요합니다.

    private final ProductService productService;
    private final OrderService orderService;
    private final ReviewService reviewService;
    private final UserService userService;

    public ReviewRestController(ProductService productService, OrderService orderService, ReviewService reviewService, UserService userService){
        this.productService = productService;
        this.orderService = orderService;
        this.reviewService = reviewService;
        this.userService = userService;
    }

    @PostMapping("/{id}/review")
    public ApiResult<ReviewDto> review(@AuthenticationPrincipal JwtAuthentication authentication, @PathVariable Long id, @Valid @RequestBody ReviewRequest reviewRequest){

        User user = userService.findById(authentication.id).orElseThrow(() -> new NotFoundException("Could nof found user for " + authentication.id));

        Orders orders = orderService.findById(id).orElseThrow(() -> new NotFoundException("Could not found order for " + id));

        if(!orders.getState().equals(State.COMPLETED.toString())){

            throw new NotAllowedStateException("Could not write review for order 1 because state(REQUESTED) is not allowed");
        }

        if(orders.getReviewSeq() > 0) {

            throw new AlreadyExistException("Could not write review for order 4 because have already written");
        }

        Long seq = reviewService.newReview(user.getSeq(), orders.getProductSeq(),orders.getSeq(),reviewRequest.getContent());

        Optional<Review> result = reviewService.findById(seq);

        ReviewDto dto = result.map(ReviewDto::new).orElseThrow(() -> new NotFoundException("Could not found review for " + seq));

        return success(dto);
    }
}