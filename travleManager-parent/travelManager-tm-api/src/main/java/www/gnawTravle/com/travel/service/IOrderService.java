package www.gnawTravle.com.travel.service;

import www.gnawTravle.com.travel.entity.order.Order;
import www.gnawTravle.com.travel.entity.page.PageParam;

import java.util.List;

public interface IOrderService {
     long count()throws Exception;

     Order findById(Integer id)throws Exception;

     List<Order> findList()throws Exception;

     void save(Order notice)throws Exception;

     void update(Order notice)throws Exception;

     void deleteByid(Integer id)throws Exception;

     List<Order> findByPage(int currentPage, int pageSize, String query)throws Exception;

     List<Order> findListByUserId(String userId)throws Exception;

     long countByUserId(String userId)throws Exception;

     PageParam<Order> findByPageByUserId(int currentPage, int pageSize, String userId)throws Exception;


    long state0count();
    long state1count();
    long state2count();
}
