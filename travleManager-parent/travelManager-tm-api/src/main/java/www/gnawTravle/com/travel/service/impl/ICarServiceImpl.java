package www.gnawTravle.com.travel.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import www.gnawTravle.com.travel.entity.car.Car;
import www.gnawTravle.com.travel.mapper.ICarMapper;
import www.gnawTravle.com.travel.service.ICarService;
import www.gnawTravle.com.travel.utils.Tools;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: travleManager-parent
 * @description: 车票管理服务层实现类
 * @author: wang_sir
 * @create: 2020-06-17 10:00
 **/
@Service
public class ICarServiceImpl implements ICarService {

    @Autowired
    private ICarMapper carMapper;

    @Override
    public long count() throws Exception {
        return carMapper.count();
    }

    @Override
    public Car findById(Integer id) throws Exception {
        return carMapper.findById(id);
    }

    @Override
    public List<Car> findList() throws Exception {
        return carMapper.findList();
    }

    @Override
    public void save(Car car) throws Exception {
        carMapper.save(car);
    }

    @Override
    public void update(Car car) throws Exception {
        carMapper.update(car);
    }

    @Override
    public void deleteByid(Integer id) throws Exception {
        carMapper.deleteByid(id);
    }

    @Override
    public List<Car> findByPage(int currentPage, int pageSize, String query) throws Exception {
        List<Car> list = new ArrayList<Car>();
        PageHelper.startPage(currentPage, pageSize);
        if (!Tools.isEmpty(query)) {
            list = carMapper.findListByQuery("%" + query + "%");
        } else {
            list = carMapper.findList();
        }
        PageInfo<Car> pageInfo = new PageInfo<Car>(list);
        return pageInfo.getList();
    }

    @Override
    public List<Car> findByPage(int currentPage, int pageSize) throws Exception {
        List<Car> list = new ArrayList<Car>();
        PageHelper.startPage(currentPage, pageSize);
        list = carMapper.indexList();
        PageInfo<Car> pageInfo = new PageInfo<Car>(list);
        return pageInfo.getList();
    }

    @Override
    public long state0count() {
        return carMapper.state0count();
    }

    @Override
    public long state1count() {
        return carMapper.state1count();
    }

    @Override
    public long state2count() {
        return carMapper.state2count();
    }

    @Override
    public long type0count() {
        return carMapper.type0count();
    }

    @Override
    public long type1count() {
        return carMapper.type1count();
    }

    @Override
    public long type2count() {
        return carMapper.type2count();
    }
}
