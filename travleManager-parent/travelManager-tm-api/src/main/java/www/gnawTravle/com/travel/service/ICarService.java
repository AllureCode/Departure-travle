package www.gnawTravle.com.travel.service;

import www.gnawTravle.com.travel.entity.car.Car;

import java.util.List;

/**
 * @author
 */
public interface ICarService {
    long count() throws Exception;

    Car findById(Integer id) throws Exception;

    List<Car> findList() throws Exception;

    void save(Car car) throws Exception;

    void update(Car car) throws Exception;

    void deleteByid(Integer id) throws Exception;

    List<Car> findByPage(int currentPage, int pageSize, String query) throws Exception;

    List<Car> findByPage(int currentPage, int pageSize) throws Exception;

    long state0count();

    long state1count();

    long state2count();

    long type0count();

    long type1count();

    long type2count();
}
