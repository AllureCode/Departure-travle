package www.gnawTravle.com.travel.entity.user;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.Serializable;

/**
 * @program: travleManager-parent
 * @description: 用户实体类
 * @author: wang_sir
 * @create: 2020-06-13 12:29
 **/
@Data
@ToString
@Setter
@Getter
public class User implements Serializable {
    private Integer id;
    private String userName;

    private String password;

    private String linkTel;

    private String name;

    private String icCode;

    private Integer state;

    private Integer province;

}
