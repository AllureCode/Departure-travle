package www.gnawTravle.com.travel.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import www.gnawTravle.com.travel.entity.admin.Admin;
import www.gnawTravle.com.travel.mapper.IAdminMapper;
import www.gnawTravle.com.travel.service.IAdminService;
/**
 * @program: travleManager-parent
 * @description: 管理员服务层接口实现类
 * @author: wang_sir
 * @create: 2020-06-12 21:02
 **/
@Service
public class AdminServiceImpl implements IAdminService {

    @Autowired
    private IAdminMapper adminMapper;
    @Override
    public Admin login(String username, String password)  {
        return adminMapper.login(username,password);
    }
}
