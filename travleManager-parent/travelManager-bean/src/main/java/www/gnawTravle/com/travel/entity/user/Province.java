package www.gnawTravle.com.travel.entity.user;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @program: travleManager-parent
 * @description: 省份实体类
 * @author: wang_sir
 * @create: 2020-06-13 12:32
 **/
@Data
@Getter
@Setter
@ToString
public class Province implements Serializable {
    private Long province;

    private String count;
}
