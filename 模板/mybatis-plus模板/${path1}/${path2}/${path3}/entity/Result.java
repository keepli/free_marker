package entity;

import lombok.Data;

/**
 * 结果封装实体类
 */
@Data
public class Result {
    private Integer code;//状态码
    private Boolean flag;//是否成功
    private String message;//返回消息
    private Object data;//返回数据

    public Result() {
    }

    public Result(Integer code, Boolean flag, String message) {
        this.code = code;
        this.flag = flag;
        this.message = message;
    }

    public Result(Integer code, Boolean flag, String message, Object data) {
        this.code = code;
        this.flag = flag;
        this.message = message;
        this.data = data;
    }
}
