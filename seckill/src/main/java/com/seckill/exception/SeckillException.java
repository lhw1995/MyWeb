package com.seckill.exception;

import com.seckill.dto.SeckillExecution;

/**
 * Created by lhw2 on 2017/3/14.
 */
public class SeckillException extends RuntimeException{
    public SeckillException(String message) {
        super(message);
    }

    public SeckillException(String message, Throwable cause) {
        super(message, cause);
    }
}
