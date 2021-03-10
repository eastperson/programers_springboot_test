package com.github.prgrms.reviews;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

public class ReviewRequest {

    @NotBlank(message = "content must be provided")
    @Length(max = 1000, message = "content length must be less than 1000 characters")
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "ReviewRequest{" +
                "content='" + content + '\'' +
                '}';
    }
}
