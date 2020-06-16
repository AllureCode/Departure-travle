package www.gnawTravle.com.travel.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import www.gnawTravle.com.travel.entity.user.Province;
import www.gnawTravle.com.travel.entity.user.User;

import java.util.List;

/**
 * @author wang_sir
 */
@Mapper
public interface IUserMapper {
    /**
     * 查询省份
     */
    List<Province> countPorvice();
    /**
     * 查询总记录数
     */
    long count();


    /**
     * 根据条件查询
     * @param query
     * @return
     */
    List<User> findListByQuery(@Param("query") String query);

    /**
     * 查询所有用户
     * @return
     */
    List<User> findList();

    /**
     * 根据id查询用户
     * @param id
     * @return
     * @throws Exception
     */
    User findById(@Param("id") Integer id);

    /**
     * 根据用户名查询用户
     * @param userName
     * @return
     */
    User findByUserName(@Param("userName") String userName);

    /**
     * 保存用户
     * @param user
     * @throws Exception
     */
    void save(User user);

    /**
     * 更新用户
     * @param user
     * @throws Exception
     */
    void update(User user);

    /**
     * 根据id删除用户
     * @param id
     * @throws Exception
     */
    void deleteByid(Integer id);
}
