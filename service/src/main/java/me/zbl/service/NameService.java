package me.zbl.service;

import me.zbl.pojo.User;
import me.zbl.pojo.UserParam;
import me.zbl.pojo.UserVo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * 用户
 *
 * @author 郑保乐
 */
@Service
public class NameService {

    /** 从配置文件获取 value **/
    @Value("${user.name}")
    private String username;

    /**
     * 获取姓名
     */
    public String getName() {
        return "James";
    }

    /**
     * 验证 lombok
     * 提示未使用的变量
     */
    public User getUser() {
        User user = new User();
        int hash = user.hashCode();
        boolean euals = user.equals(null);
        user.setName("James");
        return user;
    }

    /**
     * 从配置文件中取值
     */
    public User getUserFromConfig() {
        User user = new User();
        user.setName(username);
        return user;
    }

    /**
     * 手机号
     */
    public User getUserMobile() {
        User user = new User();
        List<String> mobiles = Arrays.asList("18399999999", "13288888888", "18933333333");
        user.setMobile(mobiles);
        return user;
    }

    /**
     * friends
     */
    public User getFriends() {
        User user = new User();
        user.setName("James");
        User friend1 = new User();
        User friend2 = new User();
        User friend3 = new User();
        friend1.setName("Bob");
        friend2.setAge(29);
        friend3.setWeight(103.56);
        List<User> friends = Arrays.asList(friend1, friend2, friend3);
        user.setFriends(friends);
        return user;
    }

    /**
     * by id
     */
    public User getUserById(Integer id) {
        User user = new User();
        user.setId(id);
        return user;
    }

    /**
     * param
     */
    public User getUserByParam(UserParam param) {
        Integer id = param.getId();
        String name = param.getName();
        User user = new User();
        user.setId(id);
        user.setName(name);
        return user;
    }

    /**
     * vo
     */
    public UserVo getUserVo(UserParam param) {
        User user = getUserByParam(param);
        UserVo vo = new UserVo();
        vo.setUser(user);
        vo.setRandom(new Random().nextInt());
        return vo;
    }
}
