# Spring Boot Demo

## 项目结构

```plain
.
├── .editorconfig
├── .gitignore
├── HELP.md
├── LICENSE
├── README.md
├── config
│   ├── pom.xml
│   └── src
│       └── main
│           └── resources
├── pom.xml
├── service
│   ├── pom.xml
│   └── src
│       └── main
│           └── java
└── web
    ├── pom.xml
    └── src
        └── main
            └── java
```

* ``src`` 存放 Java 源代码，``resources`` 存放配置文件等无需编译的内容，``src/main`` 存放主要功能代码，``src/test`` 存放单元测试代码

* ``pom.xml`` 是 Maven 项目的配置文件，项目根目录和每个 module （模块）都有一个 pom.xml
* config 模块存放配置文件
* service 模块存放业务数据模型业务逻辑代码（CRUD）
* web 模块存放接口控制器
* ...


## Maven
* 任何具备 archive（产物）的 Maven 项目或模块，都具有一个坐标，用来标识一个唯一的依赖

```xml
<groupId>me.zbl</groupId>
<artifactId>demo</artifactId>
<version>0.0.1-SNAPSHOT</version>
```
* 比如 Spring-Web 项目的依赖

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
    <version>2.1.7.RELEASE</version>
</dependency>
```

## 项目入口

* ``@SpringBootApplication`` 主类上添加注解表示这是一个 SpringBoot 项目，main 方法是整个项目的启动入口

```java
/**
 * 项目入口
 *
 * @author 郑保乐
 */
@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
```

## 配置文件与 profile

* ``application.properties`` 是 Spring Boot 项目的全局配置，``spring.profiles.active=dev,myprofile`` 表示激活 ``dev`` 和 ``myprofile`` 两个环境（名称可自定义，数量不限），此配置项放在全局配置中

* ``application-dev`` 表示 ``dev`` 环境下的配置文件，Spring Boot 按配置文件的 ``-dev`` 后缀解析将此文件和 ``dev`` 环境进行映射

* 配置项的 key 有两种
    * 框架中提供的配置类属性
    * 自定义的配置项

* 全局配置中存放公用配置，在子环境配置中只存放和环境相关的配置，比如需要在配置文件中保存站点名（自定义配置），就可以放在全局配置中
```plian
app.name=GitHub
```

* 测试环境和开发环境的端口（框架提供的配置）不同，则分别放在 ``dev`` 和 ``prod`` 中
```plian
server.port=8080
```

## Bean

* Spring Bean 是 Spring IOC 的核心，是对 Spring 项目中任何功能组件的对象的统称，简单地理解：bean 是封装一系列功能后用来被调用的
* Service 是一种特殊的 bean，用来标识业务操作组件

```java
@Service
public class UserService {
    //...
}
```

* 声明过的 bean 通过 @Autowired 注解注入，通常用于 controller 或其他 service 中

```java
@Service
public class AdminService {

    @Autowired
    private UserService users;
    
    public User getUser(Integer userId) {
        return users.findUser(userId);
    }
}
```

## Controller

* Controller (Web 控制器) 也是一种特殊的 bean，负责和 web 相关的数据交互操作
* 使用 ``@RestController`` 来标识一个 controller，controller 类的方法返回值对应接口响应的 json 内容
* 定义一个类来标识一种形式的 json

```java
public class User {

    private Integer id;
    private String name;
    private Integer age;
}
```

```json
{
    "id": 19970701,
    "name": "James",
    "age": 29
}
```

* 入参类的类名用 ``Param`` 结尾，出参类的类名用 ``Vo`` 结尾
* 使用 ``@GetMapping`` 或 ``@PostMapping`` 标识一个方法，方法的返回值为出参，方法的参数为入参
* 使用 ``@PathVariable`` 标识一个路径变量
* 使用 ``@RequestBody`` 标识入参对象

```java
/**
 * PathVariable 的使用
 */
@GetMapping("/{id}")
public UserVo getUserById(@PathVariable Integer id) {
    return users.getUserById(id);
}

/**
 * RequestBody 的使用
 */
@PostMapping("/param")
public UserVo getUserByParam(@RequestBody UserParam param) {
    return users.getUserByParam(param);
}
```

## POJO 与 Lombok

* 任何简数据的封装结构（入参、出参、中间对象等）都视为 POJO
* 使用 ``@Data`` 注解一个 POJO 类，该类编译后会自动生称 getter、setter、toString、equals 等方法，这里的 equals 方法是检验所有非 transient 的成员变量判断两个对象是否相等
* 常用的 Lombok 注解还有 ``@Getter``、``@Setter``、``@AllArgsConstructor`` 等

```java
@Data
public class UserParam {

    private Integer id;
    private String name;
}
```

<p align="center" style="color: gray">by ZHENG BAOLE</p>