package www.gnawTravle.com.travel.service;

import www.gnawTravle.com.travel.entity.spot.Spot;

import java.util.List;

public interface ISpotService {
    /**
     * 统计
     * @return
     * @throws Exception
     */
     long count()throws Exception;
    /**
     * 统计
     * @return
     * @throws Exception
     */
     long count2()throws Exception;

    /**
     * 根据id查询
     * @param id
     * @return
     * @throws Exception
     */
     Spot findById(Integer id)throws Exception;

    /**
     * 查询所有
     * @return
     * @throws Exception
     */
     List<Spot> findList()throws Exception;

    /**
     * 保存
     * @param spot
     * @throws Exception
     */
     void save(Spot spot)throws Exception;

    /**
     * 更新
     * @param spot
     * @throws Exception
     */
     void update(Spot spot)throws Exception;

    /**
     * 根据id删除
     * @param id
     * @throws Exception
     */
     void deleteByid(Integer id)throws Exception;

    /**
     * 分页
     * @param currentPage
     * @param pageSize
     * @param query
     * @return
     * @throws Exception
     */
     List<Spot> findByPage(int currentPage, int pageSize, String query)throws Exception;

    /**
     * 分页
     * @param currentPage
     * @param pageSize
     * @return
     * @throws Exception
     */
     List<Spot> findByPage(int currentPage, int pageSize)throws Exception;


    long state0count();

    long state1count();

    long state2count();
}
