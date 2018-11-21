package org.springframework.hello.api.v1;

public class Greeting {

    private String msg;

    public Greeting(String msg) {
        this.msg = msg;
    }
    
    public String getMsg() {
        return msg;
    }

}
