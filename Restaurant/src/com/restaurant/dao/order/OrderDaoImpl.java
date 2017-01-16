package com.restaurant.dao.order;

import com.restaurant.entity.Consumer;
import com.restaurant.entity.Dish;
import com.restaurant.entity.DishOrder;
import com.restaurant.entity.Item;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by lhw on 2016/12/27.
 */
@Repository("orderDao")
public class OrderDaoImpl implements OrderDao {

    private SessionFactory sessionFactory;

    @Override
    public List<Dish> checkEnough(Map<Dish,Integer> dishMap) {
        Session session = sessionFactory.openSession();

        List<Dish> dishList = new ArrayList<>();
        //将数量超过库存的商品放入list
        for(Map.Entry<Dish,Integer> entry:dishMap.entrySet()){
            Dish dish = (Dish)session.get(Dish.class,entry.getKey().getId());
            if (dish.getInventory() < entry.getValue()) {
                dishList.add(dish);
            }
        }
        session.close();
        return dishList;
    }

    @Override
    public void createOrder(Consumer consumer, Map<Dish, Integer> dishMap) {

        //创建订单
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
        String date = sdf.format(new Date());
        DishOrder dishOrder = new DishOrder();
        float sumPrice = 0;     //总价
        for (Map.Entry<Dish,Integer> entry:dishMap.entrySet()) {
            sumPrice += entry.getKey().getPrice() * entry.getValue();
        }
        dishOrder.setConsumer(consumer);
        dishOrder.setTime(date);
        dishOrder.setPrice(sumPrice);
        dishOrder.setStatus("receiving");
        dishOrder.setDeliver("delivering");
        //创建项目
        List<Item> items = new ArrayList<>();
        for (Map.Entry<Dish, Integer> entry : dishMap.entrySet()) {
            Item item = new Item();
            item.setDish(entry.getKey());   //菜品
            item.setDishOrder(dishOrder);   //订单
            item.setCount(entry.getValue());    //数量
            item.setScore(10);
            item.setAssess("assessing");
            items.add(item);
        }

        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        session.save(dishOrder);        //保存订单
        for (int i = 0; i < items.size(); i++) {    //保存项目
            session.save(items.get(i));
        }

        session.getTransaction().commit();

    }

    @Override
    public Map<DishOrder, List<Item>> getOrder(Consumer consumer) {
        Session session = sessionFactory.openSession();

        Map<DishOrder,List<Item>> orderMap = new TreeMap<>();
        List<DishOrder> dishOrderList = session.createQuery("from DishOrder d where d.consumer=:consumer order by d.id desc")
                .setParameter("consumer",consumer).list();
        for (int i = 0; i < dishOrderList.size(); i++) {
            List<Item> items = session.createQuery("from Item i where i.dishOrder=:dishOrder")
                    .setParameter("dishOrder",dishOrderList.get(i)).list();
            orderMap.put(dishOrderList.get(i),items);
        }

        session.close();
        return orderMap;
    }

    @Override
    public Map<DishOrder, List<Item>> getAllOrder() {
        Session session = sessionFactory.openSession();

        Map<DishOrder,List<Item>> orderMap = new TreeMap<>();
        List<DishOrder> dishOrderList = session.createQuery("from DishOrder").list();
        for (int i = 0; i < dishOrderList.size(); i++) {
            List<Item> items = session.createQuery("from Item i where i.dishOrder=:dishOrder")
                    .setParameter("dishOrder",dishOrderList.get(i)).list();
            orderMap.put(dishOrderList.get(i),items);
        }

        session.close();
        return orderMap;
    }

    @Override
    public void confirmOrder(int orderId) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.createQuery("update DishOrder d set d.status = :status where d.id = :orderId")
                .setParameter("status","received").setParameter("orderId",orderId).executeUpdate();
        session.getTransaction().commit();
    }

    @Override
    public Item getItemById(int itemId) {
        Session session = sessionFactory.openSession();
        Item item = (Item)session.get(Item.class,itemId);
        session.close();
        return item;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Resource
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
