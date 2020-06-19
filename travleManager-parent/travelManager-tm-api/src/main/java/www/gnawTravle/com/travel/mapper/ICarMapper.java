package www.gnawTravle.com.travel.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import www.gnawTravle.com.travel.entity.car.Car;

import java.util.List;

/**
 * @author
 */
@Mapper
public interface ICarMapper {
     Car findById(@Param("id") Integer id);

     List<Car> findList();

     List<Car> indexList();

     List<Car> findListByQuery(@Param("query") String query);

     void save(Car car);

     void update(Car car);

     void deleteByid(@Param("id") Integer id);

     long count();


     long state0count();

     long state1count();

     long state2count();

     long type0count();

     long type1count();

     long type2count();
}
