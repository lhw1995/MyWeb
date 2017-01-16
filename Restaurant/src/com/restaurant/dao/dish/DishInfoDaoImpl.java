package com.restaurant.dao.dish;

import com.restaurant.controller.dto.ChangeDishDto;
import com.restaurant.entity.Dish;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by lhw on 2016/12/19.
 */
@Repository("dishInfoDao")
public class DishInfoDaoImpl implements DishInfoDao {

    private SessionFactory sessionFactory;

    //获取所有菜品的数目
    @Override
    public int dishCount() {
        Session session = sessionFactory.openSession();
        long count = (long)session.createQuery("select count (*) from Dish d").uniqueResult();
        session.close();
        return Integer.parseInt(String.valueOf(count));
    }

    //获取当前页的菜品
    @Override
    public List<Dish> getCurPageDish(int startNum, int size) {
        Session session = sessionFactory.openSession();
        List dishList = session.createQuery("from Dish").setFirstResult(startNum).setMaxResults(size).list();
        session.close();
        return dishList;
    }

    //添加菜品
    @Override
    public void addDish(Dish dish) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(dish);
        session.getTransaction().commit();
    }

    //根据菜名查询菜品
    @Override
    public List<Dish> getDishByName(int startNum, int size,String dishName){
        Session session = sessionFactory.openSession();
        List<Dish> dishList = session.createQuery("from Dish d where d.dishName like :dishName")
                                .setParameter("dishName","%"+dishName+"%")
                                .setFirstResult(startNum).setMaxResults(size)
                                .list();
        session.close();
        return dishList;
    }

    //获取查询到的菜品的数目
    @Override
    public int dishCountByName(String dishName) {
        Session session = sessionFactory.openSession();
        long count = (long)session.createQuery("select count (*) from Dish d where d.dishName like :dishName")
                .setParameter("dishName","%"+dishName+"%")
                .uniqueResult();
        session.close();
        return Integer.parseInt(String.valueOf(count));
    }

    //根据id删除菜品
    @Override
    public void deleteDish(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.createQuery("delete from Dish d where d.id=:id").setParameter("id",id).executeUpdate();
        session.getTransaction().commit();
    }

    //根据id获取菜品
    @Override
    public Dish getDishById(int id) {
        Session session = sessionFactory.openSession();
        Dish dish = (Dish)session.get(Dish.class,id);
        session.close();
        return dish;
    }

    //修改菜品
    @Override
    public void changeDish(Dish dish) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.update(dish);
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
