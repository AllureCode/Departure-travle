package www.gnawTravle.com.travel.service;

import www.gnawTravle.com.travel.entity.admin.Admin;

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
}
