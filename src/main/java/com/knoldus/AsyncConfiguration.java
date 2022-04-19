package com.knoldus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * configuration class that will be used to enable and configure the asynchronous method execution.
 */
@Configuration
/**
 * The @EnableAsync annotation enables Spring's ability to run  @Async methods in a background thread pool.
 */
@EnableAsync
/**
 * The bean taskExecutor helps to customize the thread executor such as configuring the number of threads for an application
 * //queue limit size, and so on. Spring will specifically look for this bean when the server is started. If this bean is not defined,
 * //Spring will create SimpleAsyncTaskExecutor by default.
 */
public class AsyncConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(AsyncConfiguration.class);

    @Bean (name = "taskExecutor")
    public Executor taskExecutor() {
        LOGGER.debug("Creating Async Task Executor");
        final ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(2);
        executor.setQueueCapacity(25);
        executor.setThreadNamePrefix("CarThread-");
        executor.initialize();
        return executor;
    }

}
