package www.gnawTravle.com.travel.service;

import www.gnawTravle.com.travel.entity.admin.Admin;
import java.util.List;

/**
 * @author wang_sir
 * 管理员服务层接口
 */
public interface IAdminService {
    /**
     * 管理员登录
     * @param username 用户名
     * @param password 密码
     * @return
     * @throws Exception
     */
     Admin login(String username,String password) throws Exception;

    /**
     * 查询总记录数
     */
    long count() throws Exception;

    /**
     * 根据页面查询
     * @param currentPage 当前页面
     * @param pageSize  当前页面有多少数据
     * @param query   查询条件
     * @return
     * @throws Exception
     */
    List<Admin> findByPage(int currentPage, int pageSize, String query);

    /**
     * 根据id查询用户
     * @param id
     * @return
     * @throws Exception
     */
    Admin findById(Integer id)throws  Exception;

    /**
     * 根据用户名查询用户
     * @param userName
     * @return
     * @throws Exception
     */
    Admin findByUserName(String userName)throws Exception;

    /**
     * 保存用户
     * @param admin
     * @throws Exception
     */
    void save(Admin admin)throws Exception;

    /**
     * 更新用户
     * @param admin
     * @throws Exception
     */
    void update(Admin admin)throws Exception;

    /**
     * 根据id删除用户
     * @param id
     * @throws Exception
     */
    void deleteByid(Integer id)throws Exception;
}
