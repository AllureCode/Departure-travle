package www.gnawTravle.com.travel.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import www.gnawTravle.com.travel.entity.travelroute.TravelRoute;

import java.util.List;

/**
 * @author wang_sir
 */
@Mapper
public interface ITravelRouteMapper {
    /**
     * 根据id查询
     * @param id
     * @return
     */
    TravelRoute findById(@Param("id") Integer id);

    /**
     * 查询所有的路线
     * @return
     */
    List<TravelRoute> findList();

    /**
     *
     * @return
     */
    List<TravelRoute> indexList();

    /**
     * 根据条件查询
     * @param query
     * @return
     */
    List<TravelRoute> findListByQuery(@Param("query") String query);

    /**
     * 保存
     * @param travelRoute
     */
    void save(TravelRoute travelRoute);

    /**
     * 更新
     * @param travelRoute
     */
    void update(TravelRoute travelRoute);

    /**
     * 删除
     * @param id
     */
    void deleteByid(@Param("id") Integer id);

    /**
     * 总数
     * @return
     */
    long count();

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
