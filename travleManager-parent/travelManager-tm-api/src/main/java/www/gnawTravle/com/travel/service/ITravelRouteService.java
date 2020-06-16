package www.gnawTravle.com.travel.service;

import www.gnawTravle.com.travel.entity.travelroute.TravelRoute;
import java.util.List;

/**
 * @program: travleManager-parent
 * @description: 旅游路线接口
 * @author: wang_sir
 * @create: 2020-06-14 22:03
 **/
public interface ITravelRouteService {

     long count()throws Exception;

     TravelRoute findById(Integer id)throws Exception;

     List<TravelRoute> findList()throws Exception;

     void save(TravelRoute travelRoute)throws Exception;

     void update(TravelRoute travelRoute)throws Exception;

     void deleteByid(Integer id)throws Exception;

     List<TravelRoute> findByPage(int currentPage, int pageSize, String query)throws Exception;

     List<TravelRoute> findByPage(int currentPage, int pageSize)throws Exception;

     /**
      * 根据state查询的总数
      * @return
      */
     long count2();

     /**
      * 根据state0查询总数
      */
     long state0count();
     /**
      * 根据stat1查询总数
      */
     long state1count();
     /**
      * 根据state2查询总数
      */
     long state2count();
}
