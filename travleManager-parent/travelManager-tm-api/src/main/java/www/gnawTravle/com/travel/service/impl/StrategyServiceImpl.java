package www.gnawTravle.com.travel.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import www.gnawTravle.com.travel.entity.strategy.Strategy;
import www.gnawTravle.com.travel.mapper.IStrategyMapper;
import www.gnawTravle.com.travel.service.IStragtegyService;
import www.gnawTravle.com.travel.utils.Tools;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: travleManager-parent
 * @description: 攻略服务实现类
 * @author: wang_sir
 * @create: 2020-06-16 14:05
 **/
@Service
public class StrategyServiceImpl implements IStragtegyService {

    @Autowired
    private IStrategyMapper strategyMapper;

    @Override
    public long count() throws Exception {
        return strategyMapper.count();
    }

    @Override
    public Strategy findById(Integer id) throws Exception {
        return strategyMapper.findById(id);
    }

    @Override
    public List<Strategy> findList() throws Exception {
        return strategyMapper.findList();
    }

    @Override
    public void save(Strategy strategy) throws Exception {
        strategyMapper.save(strategy);
    }

    @Override
    public void update(Strategy strategy) throws Exception {
        strategyMapper.update(strategy);
    }

    @Override
    public void deleteByid(Integer id) throws Exception {
        strategyMapper.deleteByid(id);
    }

    @Override
    public List<Strategy> findByPage(int currentPage, int pageSize, String query) throws Exception {
        List<Strategy> list = new ArrayList<>();
        PageHelper.startPage(currentPage, pageSize);
        if (!Tools.isEmpty(query)) {
            list = strategyMapper.findListByQuery("%" + query + "%");
        } else {
            list = strategyMapper.findList();
        }
        PageInfo<Strategy> pageInfo = new PageInfo<Strategy>(list);
        return pageInfo.getList();
    }

    @Override
    public List<Strategy> findByPage(int currentPage, int pageSize) throws Exception {
        List<Strategy> list = new ArrayList<Strategy>();
        PageHelper.startPage(currentPage, pageSize);
        list = strategyMapper.indexList();
        PageInfo<Strategy> pageInfo = new PageInfo<Strategy>(list);
        return pageInfo.getList();
    }

    @Override
    public long state0count() throws Exception {
        return strategyMapper.state0count();
    }

    @Override
    public long state1count() throws Exception {
        return strategyMapper.state1count();
    }

    @Override
    public long state2count() throws Exception {
        return strategyMapper.state2count();
    }
}
