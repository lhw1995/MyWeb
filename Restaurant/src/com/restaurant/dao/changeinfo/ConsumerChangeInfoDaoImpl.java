package com.restaurant.dao.changeinfo;

import com.restaurant.entity.Consumer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by lhw on 2016/12/8.
 */
@Repository("consumerChangeInfoDao")
public class ConsumerChangeInfoDaoImpl implements ConsumerChangeInfoDao{

    private SessionFactory sessionFactory;

    @Override
    public void updateInfo(int consumerId,String showName, String phone, String headPortrait) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.createQuery("update Consumer c set c.showName=:showName,c.phone=:phone,c.headPortrait=:headPortrait where c.id=:consumerId")
                .setParameter("showName",showName)
                .setParameter("phone",phone)
                .setParameter("headPortrait",headPortrait)
                .setParameter("consumerId",consumerId).executeUpdate();
        session.getTransaction().commit();
    }

    @Override
    public void updateInfo(int consumerId, String showName, String phone) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.createQuery("update Consumer c set c.showName=:showName,c.phone=:phone where c.id=:consumerId")
                .setParameter("showName",showName)
                .setParameter("phone",phone)
                .setParameter("consumerId",consumerId).executeUpdate();
        session.getTransaction().commit();
    }

    @Override
    public Consumer getConsumerById(int id) {
        Session session = sessionFactory.openSession();
        Consumer consumer = (Consumer) session.get(Consumer.class,id);
        session.close();
        return consumer;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Resource(name = "sessionFactory")
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
