package www.gnawTravle.com.travel.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import www.gnawTravle.com.travel.entity.order.Order;
import www.gnawTravle.com.travel.entity.page.PageParam;
import www.gnawTravle.com.travel.mapper.IOrderMapper;
import www.gnawTravle.com.travel.service.IOrderService;
import www.gnawTravle.com.travel.utils.Tools;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: travleManager-parent
 * @description:
 * @author: wang_sir
 * @create: 2020-06-17 11:59
 **/
@Service
public class OrderServiceImpl implements IOrderService {
    @Autowired
    private IOrderMapper orderMapper;
    @Override
    public long count() throws Exception {
        return orderMapper.count();
    }

    @Override
    public Order findById(Integer id) throws Exception {
        return orderMapper.findById(id);
    }

    @Override
    public List<Order> findList() throws Exception {
        return orderMapper.findList();
    }

    @Override
    public void save(Order notice) throws Exception {
        orderMapper.save(notice);
    }

    @Override
    public void update(Order notice) throws Exception {
        orderMapper.update(notice);
    }

    @Override
    public void deleteByid(Integer id) throws Exception {
        orderMapper.deleteByid(id);
    }

    @Override
    public List<Order> findByPage(int currentPage, int pageSize, String query) throws Exception {
        List<Order> list = new ArrayList<Order>();
        PageHelper.startPage(currentPage, pageSize);
        if (!Tools.isEmpty(query)) {
            list = orderMapper.findListByQuery("%" + query + "%");
        } else {
            list = orderMapper.findList();
        }
        PageInfo<Order> pageInfo=new PageInfo<Order>(list);
        return pageInfo.getList();
    }

    @Override
    public List<Order> findListByUserId(String userId) throws Exception {
        return orderMapper.findListByUserId(userId);
    }

    @Override
    public long countByUserId(String userId) throws Exception {
        return  orderMapper.countByUserId(userId);
    }

    @Override
    public PageParam<Order> findByPageByUserId(int currentPage, int pageSize, String userId) throws Exception {
        PageParam<Order> pageParam = new PageParam<>();
        Page page = PageHelper.startPage(currentPage, pageSize);
        List<Order> list = orderMapper.findListByUserId(userId);
        pageParam.setResult(list);
        pageParam.setSize(page.getPages());
        pageParam.setCount(page.getTotal());
        pageParam.setPageNumber(currentPage);
        pageParam.setPageSize(pageSize);
        return pageParam;
    }

    @Override
    public long state0count() {
        return orderMapper.state0count();
    }

    @Override
    public long state1count() {
        return orderMapper.state1count();
    }

    @Override
    public long state2count() {
        return orderMapper.state2count();
    }
}
