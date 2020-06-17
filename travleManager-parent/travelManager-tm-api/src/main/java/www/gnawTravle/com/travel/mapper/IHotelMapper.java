package www.gnawTravle.com.travel.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import www.gnawTravle.com.travel.entity.hotel.Hotel;

import java.util.List;

@Mapper
public interface IHotelMapper {
     Hotel findById(@Param("id") Integer id);

     List<Hotel> findList();

     List<Hotel> indexList();

     List<Hotel> findListByQuery(@Param("query") String query);

     void save(Hotel article);

     void update(Hotel article);

     void deleteByid(@Param("id") Integer id);

     long count();

     long count2();

     long state0count();
     long state1count();
     long state2count();
}
