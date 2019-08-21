package me.zbl.pojo;

import lombok.Data;

import java.util.List;

/**
 * 用户
 *
 * @author 郑保乐
 */
@Data
public class User {

    private Integer id;
    /** 姓名 **/
    private String name;
    /** 年龄 **/
    private Integer age;
    /** 体重 **/
    private Double weight;
    /** 手机号 **/
    private List<String> mobile;
    /** 朋友 **/
    private List<User> friends;
}
