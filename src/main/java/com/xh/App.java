package com.xh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author xiaohe
 * @version V1.0.0
 * @Description: main method.
 * @date: 2018-10-14 11:10
 * @Copyright:
 */
@SpringBootApplication
//@MapperScan("com.xh")  // 不需要@MapperScan  在配置数据源的时候已经扫描了
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

}
