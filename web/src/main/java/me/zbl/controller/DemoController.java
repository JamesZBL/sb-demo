package me.zbl.controller;

import me.zbl.pojo.User;
import me.zbl.pojo.UserParam;
import me.zbl.pojo.UserVo;
import me.zbl.service.NameService;
import org.springframework.web.bind.annotation.*;

/**
 * 用户
 *
 * @author 郑保乐
 */
@RestController
@RequestMapping("/user")
public class DemoController {

    private final NameService services;

    public DemoController(NameService services) {
        this.services = services;
    }

    /**
     * 直接获取姓名
     */
    @GetMapping
    public String getName() {
        return "James";
    }

    /**
     * 从 service 中获取姓名
     */
    @GetMapping("/name/service")
    public String getNameFromService() {
        return services.getName();
    }

    /**
     * Lombok {@link lombok.Data} 的使用
     * <pre>{@link}</pre> 的使用
     */
    @GetMapping("/lombok")
    public User lombok() {
        return services.getUser();
    }

    /**
     * 配置文件使用
     */
    @GetMapping("/config")
    public User getUserFromConfig() {
        return services.getUserFromConfig();
    }

    /**
     * json 数组 - string
     */
    @PostMapping("/mobile")
    public User getUserMobile() {
        return services.getUserMobile();
    }

    /**
     * json 数组 - object
     */
    @PostMapping("/friend")
    public User getUserFriends() {
        return services.getFriends();
    }

    /**
     * PathVariable 的使用
     */
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Integer id) {
        return services.getUserById(id);
    }

    /**
     * RequestBody 的使用
     */
    @PostMapping("/param")
    public User getUserByParam(@RequestBody UserParam param) {
        return services.getUserByParam(param);
    }

    /**
     * vo
     */
    @PostMapping("/vo")
    public UserVo getUserVo(@RequestBody UserParam param) {
        return services.getUserVo(param);
    }
}
