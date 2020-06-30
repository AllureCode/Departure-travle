package www.gnawTravle.com.travel.service;

import www.gnawTravle.com.travel.entity.insurance.Insurance;

import java.util.List;

public interface InsuranceService {
     long count()throws Exception;

     Insurance findById(Integer id)throws Exception;

     List<Insurance> findList()throws Exception;

     void save(Insurance article)throws Exception;

     void update(Insurance article)throws Exception;

     void deleteByid(Integer id)throws Exception;

     List<Insurance> findByPage(int currentPage, int pageSize, String query)throws Exception;

     List<Insurance> findByPage(int currentPage, int pageSize)throws Exception;

    public long state0count();

    public long state1count();

    public long state2count();


    public long company0count();

    public long company1count();
}
