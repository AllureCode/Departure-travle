package www.gnawTravle.com.travel.service;

import www.gnawTravle.com.travel.entity.user.Province;

import java.util.List;

/**
 * @author wang_sir
 */
public interface IUserService {
    /**
     * 查询省份
     */
    List<Province> countPorvice() throws Exception;
}
