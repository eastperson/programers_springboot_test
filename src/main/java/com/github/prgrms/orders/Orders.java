package com.github.prgrms.orders;


import java.time.LocalDateTime;

import static com.google.common.base.Preconditions.checkArgument;
import static java.time.LocalDateTime.now;
import static org.apache.commons.lang3.StringUtils.isEmpty;

public class Orders {

    private final Long seq;

    private final Long userSeq;
    private final Long productSeq;
    private Long reviewSeq;
    private String state;

    private String requestMsg;
    private String rejectMsg;
    private LocalDateTime completedAt;
    private LocalDateTime rejectedAt;
    private final LocalDateTime createAt;

    public Long getSeq() {
        return seq;
    }

    public Long getUserSeq() {
        return userSeq;
    }

    public Long getProductSeq() {
        return productSeq;
    }

    public Long getReviewSeq() {
        return reviewSeq;
    }

    public void setReviewSeq(Long reviewSeq) {
        this.reviewSeq = reviewSeq;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getRequestMsg() {
        return requestMsg;
    }

    public void setRequestMsg(String requestMsg) {
        this.requestMsg = requestMsg;
    }

    public String getRejectMsg() {
        return rejectMsg;
    }

    public void setRejectMsg(String rejectMsg) {
        this.rejectMsg = rejectMsg;
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

    public Orders(Long seq, Long userSeq, Long productSeq, String requestMsg){
        checkArgument(isEmpty(requestMsg) || requestMsg.length()<=1000,
                "request msg length must be less than 1000 characters");

        this.seq = seq;
        this.userSeq = userSeq;
        this.productSeq = productSeq;

        this.requestMsg = requestMsg;
        this.state = State.REQUESTED.toString();
        this.createAt = now();
    }

    public Orders(Long seq, Long userSeq, Long productSeq, Long reviewSeq, String state, String requestMsg, String rejectMsg, LocalDateTime completedAt, LocalDateTime rejectedAt, LocalDateTime createAt) {
        this.seq = seq;
        this.userSeq = userSeq;
        this.productSeq = productSeq;
        this.reviewSeq = reviewSeq;
        this.state = state;
        this.requestMsg = requestMsg;
        this.rejectMsg = rejectMsg;
        this.completedAt = completedAt;
        this.rejectedAt = rejectedAt;
        this.createAt = createAt;
    }

    static public class Builder {
        private Long seq;
        private Long userSeq;
        private Long productSeq;
        private Long reviewSeq;
        private String state;

        private String requestMsg;
        private String rejectMsg;
        private LocalDateTime completedAt;
        private LocalDateTime rejectedAt;
        private LocalDateTime createAt;

        public Builder() {/*empty*/}

        public Builder(Orders orders){
            this.seq = orders.userSeq;
            this.userSeq = orders.userSeq;
            this.productSeq = orders.productSeq;
            this.state = orders.state;
            this.createAt = orders.createAt;
        }

        public Orders.Builder seq(Long seq){
            this.seq = seq;
            return this;
        }

        public Orders.Builder userSeq(Long userSeq){
            this.userSeq = userSeq;
            return this;
        }

        public Orders.Builder productSeq(Long productSeq){
            this.productSeq = productSeq;
            return this;
        }

        public Orders.Builder state(String state){
            this.state = state.toString();
            return this;
        }

        public Orders.Builder createAt(LocalDateTime createAt){
            this.createAt = createAt;
            return this;
        }

        public Orders.Builder reviewSeq(Long reviewSeq){
            this.reviewSeq = reviewSeq;
            return this;
        }

        public Orders.Builder requestMsg(String requestMsg){
            this.requestMsg = requestMsg;
            return this;
        }

        public Orders.Builder rejectMsg(String rejectMsg){
            this.rejectMsg = rejectMsg;
            return this;
        }

        public Orders.Builder completedAt(LocalDateTime completedAt){
            this.completedAt = completedAt;
            return this;
        }

        public Orders.Builder rejectedAt(LocalDateTime rejectedAt){
            this.rejectedAt = rejectedAt;
            return this;
        }

        public Orders build() {
            return new Orders(
                    seq,
                    userSeq,
                    productSeq,
                    reviewSeq,
                    state,
                    requestMsg,
                    rejectMsg,
                    completedAt,
                    rejectedAt,
                    createAt
            );
        }
    }

    @Override
    public String toString() {
        return "Orders{" +
                "seq=" + seq +
                ", userSeq=" + userSeq +
                ", productSeq=" + productSeq +
                ", reviewSeq=" + reviewSeq +
                ", state='" + state + '\'' +
                ", requestMsg='" + requestMsg + '\'' +
                ", rejectMsg='" + rejectMsg + '\'' +
                ", completedAt=" + completedAt +
                ", rejectedAt=" + rejectedAt +
                ", createAt=" + createAt +
                '}';
    }
}
