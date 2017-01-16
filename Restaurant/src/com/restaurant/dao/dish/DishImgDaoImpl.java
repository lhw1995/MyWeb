package com.restaurant.dao.dish;

import com.restaurant.entity.Dish;
import com.restaurant.entity.DishImage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by lhw on 2016/12/23.
 */
@Repository("DishImgDao")
public class DishImgDaoImpl implements DishImgDao{

    private SessionFactory sessionFactory;

    @Override
    public void saveImgs(String fileName,Dish dish) {
        DishImage dishImage = new DishImage();
        dishImage.setDish(dish);
        dishImage.setImage(fileName);
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(dishImage);
        session.getTransaction().commit();
    }

    @Override
    public void deleteImgs(Dish dish) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.createQuery("delete from DishImage d where d.dish=:dishId").setParameter("dishId",dish).executeUpdate();
        session.getTransaction().commit();
    }

    @Override
    public List<DishImage> getDIshImgList(Dish dish) {
        Session session = sessionFactory.openSession();
        List<DishImage> dishImageList = session.createQuery("from DishImage d where d.dish=:dish").setParameter("dish",dish).list();
        session.close();
        return dishImageList;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Resource
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
