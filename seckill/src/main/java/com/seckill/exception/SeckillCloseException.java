package com.seckill.exception;

/**
 * 秒杀关闭异常
 * Created by lhw2 on 2017/3/14.
 */
public class SeckillCloseException extends SeckillException{

    public SeckillCloseException(String message) {
        super(message);
    }

    public SeckillCloseException(String message, Throwable cause) {
        super(message, cause);
    }
}
