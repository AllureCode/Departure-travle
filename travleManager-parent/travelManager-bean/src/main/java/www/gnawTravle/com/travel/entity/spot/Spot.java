package www.gnawTravle.com.travel.entity.spot;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.io.Serializable;

/**
 * @program: travleManager-parent
 * @description: 景点实体类
 * @author: wang_sir
 * @create: 2020-06-15 14:36
 **/
@Data
@ToString
@Getter
@Setter
public class Spot implements Serializable {
    private Integer id;

    private String spotName;

    private String spotIntro;

    private Integer spotStar;

    private String spotAddress;

    private String openTime;

    private double ticketsMessage;

    private Integer state;

    private String imgUrl;
}
