package com.github.prgrms.orders;

import javax.validation.constraints.NotBlank;

public class RejectRequest {

    private String message;

    public RejectRequest(){
        this.message = null;
    }

    public RejectRequest(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
