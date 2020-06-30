package www.gnawTravle.com.travel.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import www.gnawTravle.com.travel.entity.message.Message;

import java.util.List;

@Mapper
public interface IMessageMapper {

     Message findById(@Param("id") Integer id);

     List<Message> findList();

     List<Message> findListByUserId(@Param("userId") String userId);

     List<Message> findListByQuery(@Param("query") String query);

     void save(Message article);

     void update(Message article);

     void deleteByid(@Param("id") Integer id);

     long count();

     long countByUserId(@Param("userId") String userId);
}
