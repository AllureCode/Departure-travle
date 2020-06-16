package www.gnawTravle.com.travel.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import www.gnawTravle.com.travel.entity.travelroute.TravelRoute;
import www.gnawTravle.com.travel.mapper.ITravelRouteMapper;
import www.gnawTravle.com.travel.service.ITravelRouteService;
import www.gnawTravle.com.travel.utils.Tools;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: travleManager-parent
 * @description: 旅游路线接口实现类
 * @author: wang_sir
 * @create: 2020-06-14 22:05
 **/
@Service
public class TravelRouteServiceImpl implements ITravelRouteService {

    @Autowired
    private ITravelRouteMapper travelRouteMapper;
    @Override
    public long count() throws Exception {
        return travelRouteMapper.count();
    }

    @Override
    public TravelRoute findById(Integer id) throws Exception {
        return travelRouteMapper.findById(id);
    }

    @Override
    public List<TravelRoute> findList() throws Exception {
        return travelRouteMapper.findList();
    }

    @Override
    public void save(TravelRoute travelRoute) throws Exception {
        travelRouteMapper.save(travelRoute);
    }

    @Override
    public void update(TravelRoute travelRoute) throws Exception {
        travelRouteMapper.update(travelRoute);
    }

    @Override
    public void deleteByid(Integer id) throws Exception {
        travelRouteMapper.deleteByid(id);
    }


    @Override
    public List<TravelRoute> findByPage(int currentPage, int pageSize, String query) throws Exception {
        List<TravelRoute> list = new ArrayList<TravelRoute>();
        PageHelper.startPage(currentPage, pageSize);
        if (!Tools.isEmpty(query)) {
            list = travelRouteMapper.findListByQuery("%" + query + "%");
        } else {
            list = travelRouteMapper.findList();
        }
        PageInfo<TravelRoute> pageInfo=new PageInfo<TravelRoute>(list);
        return pageInfo.getList();
    }

    @Override
    public List<TravelRoute> findByPage(int currentPage, int pageSize) throws Exception {
        List<TravelRoute> list = new ArrayList<TravelRoute>();
        PageHelper.startPage(currentPage, pageSize);
        list = travelRouteMapper.indexList();
        PageInfo<TravelRoute> pageInfo=new PageInfo<TravelRoute>(list);
        return pageInfo.getList();
    }

    @Override
    public long count2() {
        return travelRouteMapper.count2();
    }

    @Override
    public long state0count() {
        return travelRouteMapper.state0count();
    }

    @Override
    public long state1count() {
        return travelRouteMapper.state1count();
    }

    @Override
    public long state2count() {
        return travelRouteMapper.state2count();
    }

}
