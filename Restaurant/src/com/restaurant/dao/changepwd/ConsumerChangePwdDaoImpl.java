package com.restaurant.dao.changepwd;

import com.restaurant.entity.Consumer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by lhw on 2016/12/11.
 */
@Repository("consumerChangePwdDao")
public class ConsumerChangePwdDaoImpl implements ConsumerChangePwdDao {

    private SessionFactory sessionFactory;

    @Override
    public void changePwd(String userNameMD5, String password) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.createQuery("update Consumer c set c.password=:password where c.userNameMD5=:userNameMD5")
                .setParameter("password",password)
                .setParameter("userNameMD5",userNameMD5).executeUpdate();
        session.getTransaction().commit();
    }

    @Override
    public Consumer getConsumerByName(String userName) {
        Session session = sessionFactory.openSession();
        List consumerInfo = session.createQuery("select c.id,c.userName,c.userNameMD5,c.showName,c.password,c.phone,c.email,c.headPortrait,c.consumption from Consumer c where c.userName=:userName")
                .setParameter("userName",userName).list();
        session.close();
        if (consumerInfo.isEmpty()){
            return null;
        } else {
            Object[] consumerInfo2 = (Object[]) consumerInfo.get(0);
            Consumer consumer = new Consumer((Integer) consumerInfo2[0],(String)consumerInfo2[1],(String)consumerInfo2[2],(String)consumerInfo2[3],(String)consumerInfo2[4],(String)consumerInfo2[5],(String)consumerInfo2[6],(String)consumerInfo2[7],(Float) consumerInfo2[8]);
            return consumer;
        }
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Resource
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
