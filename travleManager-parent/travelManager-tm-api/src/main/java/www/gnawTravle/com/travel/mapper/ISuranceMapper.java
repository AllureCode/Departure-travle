package www.gnawTravle.com.travel.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import www.gnawTravle.com.travel.entity.insurance.Insurance;

import java.util.List;

@Mapper
public interface ISuranceMapper {
     Insurance findById(@Param("id") Integer id);

     List<Insurance> findList();

     List<Insurance> indexList();

     List<Insurance> findListByQuery(@Param("query") String query);

     void save(Insurance article);

     void update(Insurance article);

     void deleteByid(@Param("id") Integer id);

     long count();


     long state0count();

     long state1count();

     long state2count();


     long company0count();

     long company1count();
}
