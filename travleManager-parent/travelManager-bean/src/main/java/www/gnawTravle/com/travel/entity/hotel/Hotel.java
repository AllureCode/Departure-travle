package www.gnawTravle.com.travel.entity.hotel;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @program: travleManager-parent
 * @description: 酒店管理
 * @author: wang_sir
 * @create: 2020-06-16 10:07
 **/
@Data
@ToString
@Getter
@Setter
public class Hotel implements Serializable {
    private Integer id;

    private String hotelName;

    private String hotelIntro;

    private Integer hotelStar;

    private String linkPhone;

    private String address;

    private Integer state;

    private String imgUrl;

    private double price;
}
