package gpswebservice;


import gpswebservice.ioc.Ioc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    private static void init() {
        Ioc.getInstance().init();
    }

    public static void main(String[] args) {
        init();
        SpringApplication.run(Application.class, args);
    }
}

