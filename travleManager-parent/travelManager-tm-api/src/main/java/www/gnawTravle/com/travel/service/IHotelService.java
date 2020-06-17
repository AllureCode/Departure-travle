package www.gnawTravle.com.travel.service;

import www.gnawTravle.com.travel.entity.hotel.Hotel;

import java.util.List;

/**
 * @author
 */
public interface IHotelService {
     long count()throws Exception;

     Hotel findById(Integer id)throws Exception;

     List<Hotel> findList()throws Exception;

     void save(Hotel article)throws Exception;

     void update(Hotel article)throws Exception;

     void deleteByid(Integer id)throws Exception;

     List<Hotel> findByPage(int currentPage, int pageSize, String query)throws Exception;

     List<Hotel> findByPage(int currentPage, int pageSize)throws Exception;
    long count2();

    long state0count()throws Exception;
    long state1count()throws Exception;
    long state2count()throws Exception;
}
