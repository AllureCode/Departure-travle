package www.gnawTravle.com.travel.entity.car;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @program: travleManager-parent
 * @description: 车票实体类
 * @author: wang_sir
 * @create: 2020-06-17 09:58
 **/
@Data
@ToString
@Getter
@Setter
public class Car implements Serializable {

    private Integer id;

    private String title;

    private String startPlace;

    private String endPlace;

    private String startDateAndTime;

    private double needTime;

    private String gatherPlace;

    private double price;

    private Integer state;

    private String remark;
    private Integer type;

    private String imgUrl;
}
