package com.restaurant.dao.register;

import com.restaurant.entity.Consumer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by lhw on 2016/12/4.
 */
@Repository("consumerRegisterDao")
public class ConsumerRegisterDaoImpl implements ConsumerRegisterDao {

    private SessionFactory sessionFactory;

    //检查用户名是否重复
    @Override
    public boolean exist(String userName) {
        Session session = sessionFactory.openSession();
        long count = (long)session.createQuery("select count(*) from Consumer c where c.userName=:userName").setParameter("userName",userName).uniqueResult();
        session.close();
        //该用户已存在，返回true
        if (count > 0){
            return true;
        }
        return false;
    }

    //保存用户
    @Override
    public void save(Consumer consumer) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(consumer);
        session.getTransaction().commit();
    }

    @Override
    public List<Consumer> consumerList() {
        Session session = sessionFactory.openSession();
        List<Consumer> consumerList = session.createQuery("from Consumer ").list();
        session.close();
        return consumerList;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Resource
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
