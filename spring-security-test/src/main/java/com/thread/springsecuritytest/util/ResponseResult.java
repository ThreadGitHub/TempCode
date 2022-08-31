package com.thread.springsecuritytest.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ResponseResult<T> {
    private int code;

    private String msg;

    private T data;

    public static ResponseResult success(Object data) {
        return new ResponseResult(HttpStatus.OK.value(), "", data);
    }

    public static ResponseResult success() {
        return ResponseResult.success(null);
    }

    public static ResponseResult error(String msg, Object data) {
        return new ResponseResult(HttpStatus.BAD_REQUEST.value(), msg, data);
    }

    public static void main(String[] args) {
        ResponseResult success = ResponseResult.success(123);
        System.out.println(success);
    }
}
