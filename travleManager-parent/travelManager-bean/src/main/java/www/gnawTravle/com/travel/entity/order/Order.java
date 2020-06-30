package www.gnawTravle.com.travel.entity.order;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @program: travleManager-parent
 * @description: 订单管理实体类
 * @author: wang_sir
 * @create: 2020-06-17 11:57
 **/
@Data
@ToString
@Getter
@Setter
public class Order implements Serializable {

    private Integer id;

    private String userId;

    private String userName;

    private String productId;

    private String productName;

    private double fee;

    private Integer productType;

    private Integer state;

    private String orderCode;

    private String orderTime;

    private String setoffTime;

    private String linkTel;

    private Integer peopleCount;

    private String requirement;

    private String icCode;

    private String imgUrl;

}
