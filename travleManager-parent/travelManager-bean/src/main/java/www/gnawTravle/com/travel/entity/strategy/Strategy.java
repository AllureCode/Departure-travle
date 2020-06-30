package www.gnawTravle.com.travel.entity.strategy;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @program: travleManager-parent
 * @description: 攻略
 * @author: wang_sir
 * @create: 2020-06-16 14:01
 **/
@Data
@ToString
@Getter
@Setter
public class Strategy implements Serializable {

    private Integer id;

    private String imgUrl;

    private String title;

    private Integer rating;

    private String summary;

    private String introUrl;

    private Integer state;
}
