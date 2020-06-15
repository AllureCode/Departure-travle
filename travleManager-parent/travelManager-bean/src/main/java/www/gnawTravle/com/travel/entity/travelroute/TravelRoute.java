package www.gnawTravle.com.travel.entity.travelroute;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @program: travleManager-parent
 * @description: 旅游路线实体类
 * @author: wang_sir
 * @create: 2020-06-14 21:53
 **/
@Data
@ToString
@Setter
@Getter
public class TravelRoute implements Serializable {
    private Integer id;

    private String title;

    private String startSite;

    private String endSite;

    private String endTime;

    private String startTime;

    private Integer day;

    private String productCode;

    private double price;

    private Integer state;

    private String imgUrl;

    private String intro;
}
