package www.gnawTravle.com.travel.mapper;

import org.apache.ibatis.annotations.Mapper;
import www.gnawTravle.com.travel.entity.user.Province;

import java.util.List;

/**
 * @author wang_sir
 */
@Mapper
public interface IUserMapper {
    /**
     * 查询省份
     */
    List<Province> countPorvice() throws Exception;
}
