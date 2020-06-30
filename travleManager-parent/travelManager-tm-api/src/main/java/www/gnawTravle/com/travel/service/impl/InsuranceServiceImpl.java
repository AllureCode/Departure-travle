package www.gnawTravle.com.travel.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import www.gnawTravle.com.travel.entity.insurance.Insurance;
import www.gnawTravle.com.travel.mapper.ISuranceMapper;
import www.gnawTravle.com.travel.service.InsuranceService;
import www.gnawTravle.com.travel.utils.Tools;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: travleManager-parent
 * @description: 保险serviceImpl
 * @author: wang_sir
 * @create: 2020-06-17 10:44
 **/
@Service
public class InsuranceServiceImpl implements InsuranceService {

    @Autowired
    private ISuranceMapper suranceMapper;

    @Override
    public long count() throws Exception {
        return suranceMapper.count();
    }

    @Override
    public Insurance findById(Integer id) throws Exception {
        return suranceMapper.findById(id);
    }

    @Override
    public List<Insurance> findList() throws Exception {
        return suranceMapper.findList();
    }

    @Override
    public void save(Insurance article) throws Exception {
        suranceMapper.save(article);
    }

    @Override
    public void update(Insurance article) throws Exception {
        suranceMapper.update(article);
    }

    @Override
    public void deleteByid(Integer id) throws Exception {
        suranceMapper.deleteByid(id);
    }

    @Override
    public List<Insurance> findByPage(int currentPage, int pageSize, String query) throws Exception {
        List<Insurance> list = new ArrayList<Insurance>();
        PageHelper.startPage(currentPage, pageSize);
        if (!Tools.isEmpty(query)) {
            list = suranceMapper.findListByQuery("%" + query + "%");
        } else {
            list = suranceMapper.findList();
        }
        PageInfo<Insurance> pageInfo = new PageInfo<Insurance>(list);
        return pageInfo.getList();
    }

    @Override
    public List<Insurance> findByPage(int currentPage, int pageSize) throws Exception {
        List<Insurance> list = new ArrayList<Insurance>();
        PageHelper.startPage(currentPage, pageSize);
        list = suranceMapper.indexList();
        PageInfo<Insurance> pageInfo = new PageInfo<Insurance>(list);
        return pageInfo.getList();
    }

    @Override
    public long state0count() {
        return suranceMapper.state0count();
    }

    @Override
    public long state1count() {
        return suranceMapper.state1count();
    }

    @Override
    public long state2count() {
        return suranceMapper.state2count();
    }

    @Override
    public long company0count() {
        return suranceMapper.company0count();
    }

    @Override
    public long company1count() {
        return suranceMapper.company1count();
    }
}
