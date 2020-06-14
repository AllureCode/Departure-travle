package www.gnawTravle.com.travel.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import www.gnawTravle.com.travel.entity.admin.Admin;

/**
 * @author wang_sir
 * 管理员数据交互接口
 */
@Mapper
public interface IAdminMapper {

    Admin login(@Param("username") String username,@Param("password") String password);
}
