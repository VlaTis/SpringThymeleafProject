package eu.codeacademy.vteshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.StandardEnvironment;

@SpringBootApplication
public class VteshopApplication {

    public static void main(String[] args) {
        SpringApplication.run(VteshopApplication.class, args);




//        ConfigurableEnvironment envConf = new StandardEnvironment();
//        envConf.addActiveProfile("dev");
//
//       SpringApplication app = new SpringApplication(VteshopApplication.class);
//       app.setEnvironment(envConf);
//       app.run(args);
    }

}
