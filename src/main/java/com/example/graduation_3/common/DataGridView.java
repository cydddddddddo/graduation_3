package com.example.graduation_3.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * JSON数据实体
 * @author Cy
 * @data 2020/5/6 - 10:06
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataGridView {

    private Integer code=0;
    private String msg="";
    private Long count=0L;
    private Object data;
    public DataGridView(Long count, Object data) {
        super();
        this.count = count;
        this.data = data;
    }

    public DataGridView(String msg,Object data){
        super();
        this.msg = msg;
        this.data = data;
    }

    public DataGridView(Object data) {
        super();
        this.data = data;
    }
}
