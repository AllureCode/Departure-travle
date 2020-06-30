package www.gnawTravle.com.travel.service;

import www.gnawTravle.com.travel.entity.strategy.Strategy;

import java.util.List;

public interface IStragtegyService {
     long count()throws Exception;

     Strategy findById(Integer id)throws Exception;

     List<Strategy> findList()throws Exception;

     void save(Strategy strategy)throws Exception;

     void update(Strategy strategy)throws Exception;

     void deleteByid(Integer id)throws Exception;

     List<Strategy> findByPage(int currentPage, int pageSize, String query)throws Exception;

     List<Strategy> findByPage(int currentPage, int pageSize)throws Exception;

    long state0count()throws Exception;
    long state1count()throws Exception;
    long state2count()throws Exception;
}
