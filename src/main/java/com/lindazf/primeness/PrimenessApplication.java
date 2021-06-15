package com.lindazf.primeness;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.DefaultManagedTaskExecutor;

import java.util.concurrent.Executor;

@SpringBootApplication
public class PrimenessApplication {

    public static void main(String[] args) {
        SpringApplication.run(PrimenessApplication.class, args);
        System.out.println("Webflux Application");
    }

}




