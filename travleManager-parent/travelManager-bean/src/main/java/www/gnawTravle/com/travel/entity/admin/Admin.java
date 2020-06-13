package www.gnawTravle.com.travel.entity.admin;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @program: travleManager-parent
 * @description: 管理员实体类
 * @author: wang_sir
 * @create: 2020-06-12 20:50
 **/
@Data
@ToString
@Setter
@Getter
public class Admin implements Serializable {
    private String userName;

    private String password;

    private String name;

    private String linkTel;

    private Integer state;
}
