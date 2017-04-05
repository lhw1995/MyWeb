package com.seckill.dao;

import com.seckill.entity.SuccessKilled;
import org.apache.ibatis.annotations.Param;

/**
 * Created by lhw2 on 2017/3/13.
 */
public interface SuccessKilledDao {

    /**
     * 插入购买明细，可过滤重复
     * @param seckillId
     * @param userPhone
     * @return 插入的行数
     */
    int insertSuccessKilled(@Param("seckillId") long seckillId, @Param("userPhone") long userPhone);

    /**
     * 根据商品id查询秒杀成功的SuccessKilled并携带商品实体对象
     * @param seckillId
     * @return
     */
    SuccessKilled queryByIdWithSeckill(@Param("seckillId") long seckillId,@Param("userPhone") long userPhone);
}
