package kr.co.monolith.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

import static org.springframework.context.support.AbstractApplicationContext.APPLICATION_EVENT_MULTICASTER_BEAN_NAME;


@Configuration
public class EventConfig {

	/**
	 * 기본값이 동기인 Application Event를 비동기로 전역 설정
	 * (@EventListener에 @Async 선언 불필요)
	 */
	@Bean(APPLICATION_EVENT_MULTICASTER_BEAN_NAME)
	public ApplicationEventMulticaster applicationEventMulticaster() {
		SimpleApplicationEventMulticaster eventMulticaster = new SimpleApplicationEventMulticaster();
		eventMulticaster.setTaskExecutor(eventAsyncExecutor());
		return eventMulticaster;
	}


	/**
	 * CorePoolSize: 활성 상태를 유지하는 최소 스레드 수
	 * MaxPoolSize: 생성될 수 있는 최대 스레드 수 (QueueCapacity를 초과하는 경우에만 새 스레드 생성)
	 */
	private Executor eventAsyncExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(1);
		executor.setMaxPoolSize(1);
		executor.setQueueCapacity(2);
		executor.setWaitForTasksToCompleteOnShutdown(true);
		executor.setAwaitTerminationSeconds(60);
		executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
		executor.setThreadNamePrefix("Event-");
		executor.initialize();
		return executor;
	}

}
