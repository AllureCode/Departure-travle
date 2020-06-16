package www.gnawTravle.com.travel.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import www.gnawTravle.com.travel.entity.user.Province;
import www.gnawTravle.com.travel.entity.user.User;
import www.gnawTravle.com.travel.mapper.IUserMapper;
import www.gnawTravle.com.travel.service.IUserService;
import www.gnawTravle.com.travel.utils.Tools;
import java.util.ArrayList;
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

    @Override
    public long count() throws Exception {
        return userMapper.count();
    }

    @Override
    public List<User> findByPage(int currentPage, int pageSize, String query)  {
        List<User> list = new ArrayList<User>();
        PageHelper.startPage(currentPage,pageSize);
        if (!Tools.isEmpty(query)){
            list = userMapper.findListByQuery("%" + query + "%");
        }else {
            list = userMapper.findList();
        }
        PageInfo<User> pageInfo = new PageInfo<User>(list);
        return pageInfo.getList();
    }

    @Override
    public User findById(Integer id) throws Exception {
        return userMapper.findById(id);
    }

    @Override
    public User findByUserName(String userName) throws Exception {
        return userMapper.findByUserName(userName);
    }

    @Override
    public void save(User user) throws Exception {
        userMapper.save(user);
    }

    @Override
    public void update(User user) throws Exception {
        userMapper.update(user);
    }

    @Override
    public void deleteByid(Integer id) throws Exception {
        userMapper.deleteByid(id);
    }
}
