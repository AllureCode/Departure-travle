package www.gnawTravle.com.travel.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import www.gnawTravle.com.travel.entity.order.Order;

import java.util.List;

@Mapper
public interface IOrderMapper {
     Order findById(@Param("id") Integer id);

     List<Order> findList();

     List<Order> findListByQuery(@Param("query") String query);

     List<Order> findListByUserId(@Param("userId") String userId);

     void save(Order notice);

     void update(Order notice);

     void deleteByid(@Param("id") Integer id);

     long count();

     long countByUserId(@Param("userId") String userId);

     long state0count();
     long state1count();
     long state2count();
}
