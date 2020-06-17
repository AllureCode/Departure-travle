package www.gnawTravle.com.travel.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.util.resources.cldr.nr.CurrencyNames_nr;
import www.gnawTravle.com.travel.entity.hotel.Hotel;
import www.gnawTravle.com.travel.mapper.IHotelMapper;
import www.gnawTravle.com.travel.service.IHotelService;
import www.gnawTravle.com.travel.utils.Tools;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: travleManager-parent
 * @description: 酒店管理服务层实现类
 * @author: wang_sir
 * @create: 2020-06-16 10:12
 **/
@Service
public class HotelServiceImpl implements IHotelService {

    @Autowired
    private IHotelMapper hotelMapper;

    @Override
    public long count() throws Exception {
        return hotelMapper.count();
    }

    @Override
    public Hotel findById(Integer id) throws Exception {
        return hotelMapper.findById(id);
    }

    @Override
    public List<Hotel> findList() throws Exception {
        return hotelMapper.findList();
    }

    @Override
    public void save(Hotel article) throws Exception {
        hotelMapper.save(article);
    }

    @Override
    public void update(Hotel article) throws Exception {
        hotelMapper.update(article);
    }

    @Override
    public void deleteByid(Integer id) throws Exception {
        hotelMapper.deleteByid(id);
    }

    @Override
    public List<Hotel> findByPage(int currentPage, int pageSize, String query) throws Exception {
       List<Hotel> list = new ArrayList<>();
       PageHelper.startPage(currentPage,pageSize);
       if (!Tools.isEmpty(query)){
           list = hotelMapper.findListByQuery("%" + query + "%");
       }else {
           list = hotelMapper.findList();
       }
        PageInfo<Hotel> pageInfo = new PageInfo<Hotel>(list);
        return pageInfo.getList();
    }

    @Override
    public List<Hotel> findByPage(int currentPage, int pageSize) throws Exception {
        List<Hotel> list = new ArrayList<>();
        PageHelper.startPage(currentPage,pageSize);
        list = hotelMapper.indexList();
        PageInfo<Hotel> pageInfo = new PageInfo<>(list);
        return pageInfo.getList();
    }

    @Override
    public long count2() {
        return hotelMapper.count();
    }

    @Override
    public long state0count() throws Exception {
        return hotelMapper.state0count();
    }

    @Override
    public long state1count() throws Exception {
        return hotelMapper.state1count();
    }

    @Override
    public long state2count() throws Exception {
        return hotelMapper.state2count();
    }
}
