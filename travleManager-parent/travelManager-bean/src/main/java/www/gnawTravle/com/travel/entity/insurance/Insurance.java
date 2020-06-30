package www.gnawTravle.com.travel.entity.insurance;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @program: travleManager-parent
 * @description: 保险实体类
 * @author: wang_sir
 * @create: 2020-06-17 10:42
 **/
@Data
@Getter
@Setter
@ToString
public class Insurance implements Serializable {

    private Integer id;

    private String title;

    private Integer insuranceCompany;

    private double price;

    private Integer type;

    private String resume;

    private Integer state;

    private String imgUrl;

}
