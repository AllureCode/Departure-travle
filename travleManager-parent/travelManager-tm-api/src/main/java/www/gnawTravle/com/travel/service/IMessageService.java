package www.gnawTravle.com.travel.service;

import www.gnawTravle.com.travel.entity.message.Message;
import www.gnawTravle.com.travel.entity.page.PageParam;

import java.util.List;

public interface IMessageService {
      long count()throws Exception;

      long countByUserId(String userId)throws Exception;

      Message findById(Integer id)throws Exception;

      List<Message> findList()throws Exception;

      void save(Message article)throws Exception;

      void update(Message article)throws Exception;

      void deleteByid(Integer id)throws Exception;

      List<Message> findByPage(int currentPage, int pageSize, String query)throws Exception;

      PageParam<Message> findByPageByUserId(int currentPage, int pageSize, String userId)throws Exception;
}
