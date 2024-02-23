package com.example.socketmessagesc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import static com.example.socketmessagesc.Client.runClient;

@SpringBootApplication
public class Application {

    @Autowired
    private static AppConfig appConfig;

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Application.class, args);
        AppConfig appConfig = context.getBean(AppConfig.class);
        if (args.length > 0 && args[0].equals("client")) {
            runClient(appConfig);
        } else {
            Server.runServer(appConfig);
        }
    }

}
