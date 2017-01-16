package com.restaurant.service.recommend;

import com.restaurant.dao.dish.DishInfoDao;
import com.restaurant.dao.recommend.RecommendDao;
import com.restaurant.dao.register.ConsumerRegisterDao;
import com.restaurant.entity.Consumer;
import com.restaurant.entity.Dish;
import com.restaurant.function.Correlate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Created by lhw on 2017/1/2.
 */
@Service("recommendService")
public class RecommendServiceImpl implements RecommendService {

    private ConsumerRegisterDao consumerRegisterDao;
    private RecommendDao recommendDao;
    private DishInfoDao dishInfoDao;

    @Override
    public List<Dish> getRecommend(HttpSession session) {
        Consumer consumer = null;
        if (session.getAttribute("loginUser") != null) {
            consumer = (Consumer) session.getAttribute("loginUser");
        } else {
            return null;
        }
        //map<用户,<菜品，评分>>
        IdentityHashMap<Integer,Map<Integer,Double>> dataMap = new IdentityHashMap<>();
        //获得所有用户
        List<Consumer> consumerList = consumerRegisterDao.consumerList();
        Consumer cc = null;
        for (Consumer c : consumerList) {
            if ("admin".equals(c.getUserName())){
                cc = c;
            }
        }
        consumerList.remove(cc);

        for (int i = 0; i < consumerList.size(); i++) {
            //获得用户评价过的菜的评分
            Map<Integer,Double> scoreMap = recommendDao.getScore(consumerList.get(i));
            dataMap.put(consumerList.get(i).getId(),scoreMap);
        }

        Set<Map.Entry<Integer,Map<Integer,Double>>> set = dataMap.entrySet();
        for (Map.Entry<Integer, Map<Integer, Double>> keyEntry : set){
            if (keyEntry.getKey()==consumer.getId()){
                consumer.setId(keyEntry.getKey());
                break;
            }
        }


        Correlate correlate = new Correlate();
        //推荐的菜品
        System.out.println(consumer);
        List<Integer> dishList = correlate.getRecommendations(dataMap,consumer.getId());
        List<Dish> list = new ArrayList<>();
        int count = 0;
        for (int i = 0; i < dishList.size(); i++) {
            count ++;
            if (count > 3){
                break;
            }
            Dish dish = dishInfoDao.getDishById(dishList.get(i));
            list.add(dish);
        }
        return list;
    }

    public ConsumerRegisterDao getConsumerRegisterDao() {
        return consumerRegisterDao;
    }

    @Resource
    public void setConsumerRegisterDao(ConsumerRegisterDao consumerRegisterDao) {
        this.consumerRegisterDao = consumerRegisterDao;
    }

    public RecommendDao getRecommendDao() {
        return recommendDao;
    }

    @Resource
    public void setRecommendDao(RecommendDao recommendDao) {
        this.recommendDao = recommendDao;
    }

    public DishInfoDao getDishInfoDao() {
        return dishInfoDao;
    }

    @Resource
    public void setDishInfoDao(DishInfoDao dishInfoDao) {
        this.dishInfoDao = dishInfoDao;
    }
}
