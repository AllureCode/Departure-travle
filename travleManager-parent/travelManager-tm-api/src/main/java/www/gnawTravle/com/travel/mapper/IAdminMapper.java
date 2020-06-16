package www.gnawTravle.com.travel.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import www.gnawTravle.com.travel.entity.admin.Admin;

import java.util.List;

/**
 * @author wang_sir
 * 管理员数据交互接口
 */
@Mapper
public interface IAdminMapper {

    Admin login(@Param("username") String username,@Param("password") String password);

    /**
     * 查询总记录数
     */
    long count();
    /**
     * 根据id查询用户
     * @param id
     * @return
     * @throws Exception
     */
    Admin findById(@Param("id") Integer id);

    /**
     * 根据用户名查询用户
     * @param userName
     * @return
     */
    Admin findByUserName(@Param("userName") String userName);

    /**
     * 根据条件查询
     * @param query
     * @return
     */
    List<Admin> findListByQuery(@Param("query") String query);

    /**
     * 查询所有用户
     * @return
     */
    List<Admin> findList();
    /**
     * 保存用户
     * @param admin
     */
    void save(Admin admin);

    /**
     * 更新用户
     * @param admin
     */
    void update(Admin admin);

    /**
     * 根据id删除用户
     * @param id
     */
    void deleteByid(Integer id);

}
