package com.github.prgrms.reviews;

import com.github.prgrms.orders.OrderService;
import com.github.prgrms.products.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkNotNull;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ProductService productService;
    private final OrderService orderService;

    public ReviewService(ReviewRepository reviewRepository,ProductService productService,OrderService orderService){
        this.reviewRepository = reviewRepository;
        this.productService = productService;
        this.orderService = orderService;
    }

    @Transactional(readOnly = true)
    public Optional<Review> findById(Long reviewId) {
        checkNotNull(reviewId, "reviewId must be provided");

        return reviewRepository.findById(reviewId);
    }

    @Transactional(readOnly = true)
    public List<Review> findAll() {
        return reviewRepository.findAll();
    }

    public Long newReview(Long userSeq, Long productSeq,Long orderSeq, String content) {
        Review review = new Review.Builder()
                .userSeq(userSeq)
                .productSeq(productSeq)
                .content(content)
                .build();

        Long seq = reviewRepository.save(review);

        productService.addReviewCnt(productSeq);
        orderService.addReview(orderSeq,seq);

        return seq;
    }
}
