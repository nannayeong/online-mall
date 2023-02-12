package com.mall.nanna.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * 비동기 설정
 * @author EDA
 * @since 2022-12-12
 */
@Configuration
@EnableAsync
public class AsyncConfig extends AsyncConfigurerSupport {

    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5); // 기본 실행 대기하는 쓰레드 수
        executor.setMaxPoolSize(30); // 동시 시작하는 최대 쓰레드 수
        executor.setQueueCapacity(50); // 최대 쓰레드 수 초과 시 대기 가능한 요청 수
        executor.setThreadNamePrefix("NANNA-ASYNC"); // 생성되는 쓰레드의 접두사
        executor.initialize();
        return executor;
    }
}
