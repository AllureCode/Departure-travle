package www.gnawTravle.com.travel.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import www.gnawTravle.com.travel.entity.strategy.Strategy;

import java.util.List;

@Mapper
public interface IStrategyMapper {
     Strategy findById(@Param("id") Integer id);

     List<Strategy> findList();

     List<Strategy> indexList();

     List<Strategy> findListByQuery(@Param("query") String query);

     void save(Strategy article);

     void update(Strategy article);

     void deleteByid(@Param("id") Integer id);

     long count();

     long state0count();

     long state1count();

     long state2count();
}
