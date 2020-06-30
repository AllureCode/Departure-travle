package www.gnawTravle.com.travel.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import www.gnawTravle.com.travel.entity.message.Message;
import www.gnawTravle.com.travel.entity.page.PageParam;
import www.gnawTravle.com.travel.mapper.IMessageMapper;
import www.gnawTravle.com.travel.service.IMessageService;
import www.gnawTravle.com.travel.utils.Tools;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: travleManager-parent
 * @description: 留言service实现类
 * @author: wang_sir
 * @create: 2020-06-16 12:19
 **/
@Service
public class MessageServiceImpl implements IMessageService {

    @Autowired
    private IMessageMapper messageMapper;

    @Override
    public long count() throws Exception {
        return messageMapper.count();
    }


    @Override
    public long countByUserId(String userId) throws Exception {
        return messageMapper.countByUserId(userId);
    }

    @Override
    public Message findById(Integer id) throws Exception {
        return messageMapper.findById(id);
    }

    @Override
    public List<Message> findList() throws Exception {
        return messageMapper.findList();
    }

    @Override
    public void save(Message article) throws Exception {
        messageMapper.save(article);
    }

    @Override
    public void update(Message article) throws Exception {
        messageMapper.update(article);
    }

    @Override
    public void deleteByid(Integer id) throws Exception {
        messageMapper.deleteByid(id);
    }

    @Override
    public List<Message> findByPage(int currentPage, int pageSize, String query) throws Exception {
        List<Message> list = new ArrayList<Message>();
        PageHelper.startPage(currentPage, pageSize);
        if (!Tools.isEmpty(query)) {
            list = messageMapper.findListByQuery("%" + query + "%");
        } else {
            list = messageMapper.findList();
        }
        PageInfo<Message> pageInfo=new PageInfo<Message>(list);
        return pageInfo.getList();

    }

    @Override
    public PageParam<Message> findByPageByUserId(int currentPage, int pageSize, String userId) throws Exception {

        PageParam<Message> pageParam = new PageParam<>();
        Page page = PageHelper.startPage(currentPage, pageSize);
        List<Message> list = messageMapper.findListByUserId(userId);
        pageParam.setResult(list);
        pageParam.setSize(page.getPages());
        pageParam.setCount(page.getTotal());
        pageParam.setPageNumber(currentPage);
        pageParam.setPageSize(pageSize);
        return pageParam;
    }
}
