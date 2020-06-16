package www.gnawTravle.com.travel.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import www.gnawTravle.com.travel.entity.spot.Spot;

import java.util.List;

/**
 * @author
 */
@Mapper
public interface ISpotMapper {
     Spot findById(@Param("id") Integer id);

    List<Spot> findList();

    List< Spot> indexList();

    List< Spot> findListByQuery(@Param("query") String query);

    void save( Spot article);

    void update( Spot article);

    void deleteByid(@Param("id") Integer id);

    long count();

    long count2();

    long state0count();

    long state1count();

    long state2count();
}
