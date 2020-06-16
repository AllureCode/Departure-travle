package www.gnawTravle.com.travel.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import www.gnawTravle.com.travel.entity.spot.Spot;
import www.gnawTravle.com.travel.mapper.ISpotMapper;
import www.gnawTravle.com.travel.service.ISpotService;
import www.gnawTravle.com.travel.utils.Tools;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: travleManager-parent
 * @description: 景点接口实现类
 * @author: wang_sir
 * @create: 2020-06-15 14:40
 **/
@Service
public class SpotServiceImpl implements ISpotService {

    @Autowired
    private ISpotMapper spotMapper;

    @Override
    public long count() throws Exception {
        return spotMapper.count();
    }

    @Override
    public long count2() throws Exception {
        return spotMapper.count2();
    }

    @Override
    public Spot findById(Integer id) throws Exception {
        return spotMapper.findById(id);
    }

    @Override
    public List<Spot> findList() throws Exception {
        return spotMapper.findList();
    }

    @Override
    public void save(Spot spot) throws Exception {
        spotMapper.save(spot);
    }

    @Override
    public void update(Spot spot) throws Exception {
        spotMapper.update(spot);
    }

    @Override
    public void deleteByid(Integer id) throws Exception {
        spotMapper.deleteByid(id);
    }

    @Override
    public List<Spot> findByPage(int currentPage, int pageSize, String query) throws Exception {
        List<Spot> list = new ArrayList<Spot>();
        PageHelper.startPage(currentPage, pageSize);
        if (!Tools.isEmpty(query)) {
            list = spotMapper.findListByQuery("%" + query + "%");
        } else {
            list = spotMapper.findList();
        }
        PageInfo<Spot> pageInfo = new PageInfo<Spot>(list);
        return pageInfo.getList();
    }

    @Override
    public List<Spot> findByPage(int currentPage, int pageSize) throws Exception {
        List<Spot> list = new ArrayList<Spot>();
        PageHelper.startPage(currentPage, pageSize);
        list = spotMapper.indexList();
        PageInfo<Spot> pageInfo = new PageInfo<Spot>(list);
        return pageInfo.getList();
    }

    @Override
    public long state0count() {
        return spotMapper.state0count();
    }

    @Override
    public long state1count() {
        return spotMapper.state1count();
    }

    @Override
    public long state2count() {
        return spotMapper.state2count();
    }

}
