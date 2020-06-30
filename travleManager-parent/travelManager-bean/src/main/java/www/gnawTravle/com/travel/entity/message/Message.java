package www.gnawTravle.com.travel.entity.message;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @program: travleManager-parent
 * @description: 留言实体类
 * @author: wang_sir
 * @create: 2020-06-16 12:17
 **/
@Data
@Setter
@Getter
@ToString
public class Message implements Serializable {
    private Integer id;

    private String userId;

    private String userName;

    private String name;

    private String title;

    private String content;

    private Integer state;
}
