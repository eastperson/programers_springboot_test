package com.github.prgrms.reviews;

import java.time.LocalDateTime;

import static com.google.common.base.Preconditions.checkArgument;
import static java.time.LocalDateTime.now;
import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

public class Review {

    private final Long seq;

    private final Long userSeq;

    private final Long productSeq;

    private String content;

    private LocalDateTime createAt;

    public Long getSeq() {
        return seq;
    }

    public Long getUserSeq() {
        return userSeq;
    }

    public Long getProductSeq() {
        return productSeq;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public Review(Long seq){
        this.seq = seq;
        this.userSeq = null;
        this.productSeq = null;
    }

    public Review(Long seq, Long userSeq, Long productSeq, String content, LocalDateTime createAt){
        checkArgument(isNotEmpty(content));
        checkArgument(content.length() >= 1 && content.length() <= 1000,
                "content length must be less than 1000 characters");

        this.seq = seq;
        this.userSeq = userSeq;
        this.productSeq = productSeq;
        this.content = content;
        this.createAt = defaultIfNull(createAt,now());

    }

    static public class Builder {
        private Long seq;
        private Long userSeq;
        private Long productSeq;
        private String content;
        private LocalDateTime createAt;

        public Builder() {/*empty*/}

        public Builder(Review review){
            this.seq = review.seq;
            this.userSeq = review.userSeq;
            this.productSeq = review.productSeq;
            this.content = review.content;
            this.createAt = review.createAt;
        }

        public Review.Builder seq(Long seq){
            this.seq = seq;
            return this;
        }

        public Review.Builder userSeq(Long userSeq) {
            this.userSeq = userSeq;
            return this;
        }

        public Review.Builder productSeq(Long productSeq) {
            this.productSeq = productSeq;
            return this;
        }

        public Review.Builder content(String content) {
            this.content = content;
            return this;
        }

        public Review.Builder createAt(LocalDateTime createAt) {
            this.createAt = createAt;
            return this;
        }

        public Review build() {
            return new Review(
                    seq,
                    userSeq,
                    productSeq,
                    content,
                    createAt
            );
        }
    }

}
