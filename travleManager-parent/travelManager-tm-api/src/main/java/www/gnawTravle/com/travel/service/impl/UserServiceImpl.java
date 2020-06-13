package www.gnawTravle.com.travel.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import www.gnawTravle.com.travel.entity.user.Province;
import www.gnawTravle.com.travel.mapper.IUserMapper;
import www.gnawTravle.com.travel.service.IUserService;

import java.util.List;

/**
 * @program: travleManager-parent
 * @description: 用户服务层实现类
 * @author: wang_sir
 * @create: 2020-06-13 12:35
 **/
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserMapper userMapper;
    @Override
    public List<Province> countPorvice() throws Exception {
        return userMapper.countPorvice();
    }
}
