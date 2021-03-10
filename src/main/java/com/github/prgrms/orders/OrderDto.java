package com.github.prgrms.orders;

import com.github.prgrms.products.Product;
import com.github.prgrms.reviews.Review;
import com.github.prgrms.reviews.ReviewDto;

import java.time.LocalDateTime;

import static org.springframework.beans.BeanUtils.copyProperties;

public class OrderDto {

    private Long seq;
    private Long productId;
    private ReviewDto review;
    private String state;

    private String requestMessage;
    private String rejectMessage;
    private LocalDateTime completedAt;
    private LocalDateTime rejectedAt;
    private LocalDateTime createAt;

    public OrderDto(Orders source) {
        copyProperties(source, this);

        this.productId = source.getProductSeq();
        if(source.getReviewSeq() != null) {
            this.review = new ReviewDto(source.getReviewSeq());
        }
        this.rejectMessage = source.getRejectMsg();
        this.requestMessage = source.getRequestMsg();
    }

    public Long getSeq() {
        return seq;
    }

    public void setSeq(Long seq) {
        this.seq = seq;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public ReviewDto getReview() {
        return review;
    }

    public void setReview(ReviewDto review) {
        this.review = review;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getRequestMsg() {
        return requestMessage;
    }

    public void setRequestMsg(String requestMsg) {
        this.requestMessage = requestMsg;
    }

    public String getRejectMsg() {
        return rejectMessage;
    }

    public void setRejectMsg(String rejectMsg) {
        this.rejectMessage = rejectMsg;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }

    public LocalDateTime getRejectedAt() {
        return rejectedAt;
    }

    public void setRejectedAt(LocalDateTime rejectedAt) {
        this.rejectedAt = rejectedAt;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }
}
