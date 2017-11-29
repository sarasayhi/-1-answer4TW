package com.Marissa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages={"com.Marissa"})
public class Application {
//        extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

/*    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        //自定义应用程序或调用application.sources（...）添加源
        //因为我们的例子本身是一个@Configuration类（通过@SpringBootApplication）
        //我们实际上不需要重写这个方法。
        return application.sources(Application.class);
    }
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }*/

    /*@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return configureApplication(builder);
    }

    public static void main(String[] args) {
        configureApplication(new SpringApplicationBuilder()).run(args);
    }

    private static SpringApplicationBuilder configureApplication(SpringApplicationBuilder builder) {
        return builder.sources(Application.class);
//                .bannerMode(Banner.Mode.OFF);
    }*/

}