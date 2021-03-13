package com.github.prgrms.reviews;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.time.LocalDateTime;

import static org.springframework.beans.BeanUtils.copyProperties;

public class ReviewDto {

    private Long seq;

    private Long userSeq;

    private Long productId;

    private String content;

    private LocalDateTime createAt;

    public ReviewDto(Review source){
        copyProperties(source,this);

        this.productId = source.getProductSeq();

    }

    public ReviewDto(Long seq){
        this.seq = seq;
    }

    public Long getSeq() {
        return seq;
    }

    public void setSeq(Long seq) {
        this.seq = seq;
    }

    public Long getUserSeq() {
        return userSeq;
    }

    public void setUserSeq(Long userSeq) {
        this.userSeq = userSeq;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("seq",seq)
                .append("userSeq",userSeq)
                .append("productSeq", productId)
                .append("content",content)
                .append("createAte",createAt)
                .toString();
    }
}
