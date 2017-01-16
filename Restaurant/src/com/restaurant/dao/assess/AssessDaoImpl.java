package com.restaurant.dao.assess;

import com.restaurant.controller.dto.MultiAssessDto;
import com.restaurant.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lhw on 2016/12/31.
 */
@Repository("assessDao")
public class AssessDaoImpl implements AssessDao {

    private SessionFactory sessionFactory;

    @Override
    public void saveAssess(Assess assess) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(assess);
        session.getTransaction().commit();
    }

    @Override
    public void saveAssessImg(AssessImage assessImage) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(assessImage);
        session.getTransaction().commit();
    }

    @Override
    public Map<String,Object> getAssess(int dishId) {
        Session session = sessionFactory.openSession();
        Dish dish = (Dish) session.get(Dish.class,dishId);
        List<Item> itemList = session.createQuery("from Item i where i.dish=:dish").setParameter("dish",dish).list();
        List<MultiAssessDto> multiAssessDtoList = new ArrayList<>();
        if (itemList.size()>0) {
            for (int i = 0; i < itemList.size(); i++) {
                MultiAssessDto multiAssessDto = new MultiAssessDto();
                Assess assess = (Assess) session.createQuery
                        ("from Assess a where a.item=:item").setParameter("item", itemList.get(i)).uniqueResult();
                Consumer consumer = (Consumer) session.createQuery("select a.consumer from Assess a where a.item = :item")
                        .setParameter("item",itemList.get(i)).uniqueResult();
                List<AssessImage> assessImageList = session.createQuery("from AssessImage ai where ai.assess=:assess").setParameter("assess",assess).list();
                if (assess != null){
                    multiAssessDto.setAssess(assess);
                    multiAssessDto.setConsumer(consumer);
                    multiAssessDto.setAssessImageList(assessImageList);
                    multiAssessDtoList.add(multiAssessDto);
                }
            }
        }
        double score = 0;
        try{
            score = (Double) session.createQuery("select avg(i.score) from Item i where i.dish = :dish and i.assess=:assess")
                    .setParameter("dish",dish).setParameter("assess","assessed").uniqueResult();
        } catch (NullPointerException e){
            score = 0;
        }
        Map<String,Object> map = new HashMap<>();
        map.put("multiAssessDtoList",multiAssessDtoList);
        map.put("score",score);
        map.put("assessCount",multiAssessDtoList.size());
        session.close();
        return map;
    }

    @Override
    public void updateScore(Item item) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.saveOrUpdate(item);
        session.getTransaction().commit();
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Resource
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
