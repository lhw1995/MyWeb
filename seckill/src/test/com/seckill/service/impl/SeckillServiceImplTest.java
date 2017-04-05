package com.seckill.service.impl;

import com.seckill.dto.Exposer;
import com.seckill.entity.Seckill;
import com.seckill.service.SeckillService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by lhw2 on 2017/3/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-*.xml"})
public class SeckillServiceImplTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillService seckillService;

    @Test
    public void getSeckillList() throws Exception {
        List<Seckill> list = seckillService.getSeckillList();
        logger.info("list={}",list);
    }

    @Test
    public void getById() throws Exception {
        long id = 1000L;
        Seckill seckill = seckillService.getById(id);
        logger.info("seckill={}",seckill);
    }

    @Test
    public void exportSeckillUrl() throws Exception {
        long id = 1000L;
        Exposer exposer = seckillService.exportSeckillUrl(id);
        logger.info("exposer={}",exposer);
    }

    @Test
    public void executeSeckill() throws Exception {
        long id = 1000L;
        long phone = 13511111111L;
        String md5 = "31c4d99389e2f663fce8a672056cb6de";
        seckillService.executeSeckill(id,phone,md5);
    }

}