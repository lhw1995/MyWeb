package com.restaurant.dao.recommend;

import com.restaurant.entity.Consumer;
import com.restaurant.entity.Dish;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lhw on 2017/1/2.
 */
@Repository("recommendDao")
public class RecommendDaoImpl implements RecommendDao {

    private SessionFactory sessionFactory;

    @Override
    public Map<Integer, Double> getScore(Consumer consumer) {
        Session session = sessionFactory.openSession();
        //获得评价过的所有菜品
        List<Dish> dishList = session.createQuery("select i.dish from Item i where i.assess=:assess and i.dishOrder.consumer=:consumer")
                .setParameter("assess","assessed").setParameter("consumer",consumer).list();

        System.out.println("dishlist="+dishList);
        Map<Integer,Double> map = new HashMap<>();
        //获得评价过的菜品的评分
        for (int i = 0; i < dishList.size(); i++) {
            Double score = (Double) session.createQuery("select avg(i.score) from Item i where i.dish=:dish and i.dishOrder.consumer=:consumer")
                    .setParameter("dish",dishList.get(i)).setParameter("consumer",consumer).uniqueResult();
            map.put(dishList.get(i).getId(),score);
        }
        session.close();
        return map;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Resource
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
