package kr.co.monolith.config;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import reactor.netty.http.client.HttpClient;


/**
 * Webclient 사용시 Codec에 buffer size 지정 (Required)
 * spring.codec.max-in-memory-size not used (Deprecated)
 * 응답대기시간 3초
 * 연결대기시간 3초
 * 읽기대기시간 10초
 * 쓰기대기시간 10초
 */
@Configuration
public class WebClientConfig {

	@Bean
	public WebClient webClient(WebClient.Builder webClientBuilder) {
		HttpClient httpClient = HttpClient.newConnection()
				.doOnConnected(connection ->
						connection.addHandlerLast(new ReadTimeoutHandler(10000, TimeUnit.MILLISECONDS)) // Read Timeout
								.addHandlerLast(new WriteTimeoutHandler(10000, TimeUnit.MILLISECONDS))) // Write Timeout
				.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 3000) // Connection Timeout
				.responseTimeout(Duration.ofSeconds(3));

		return webClientBuilder
				.clientConnector(new ReactorClientHttpConnector(httpClient.followRedirect(true)))
				.exchangeStrategies(ExchangeStrategies.builder()
						.codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(-1))
						.build())
				.build();
	}

	@Bean
	public WebClient logClient(WebClient.Builder webClientBuilder) {
		HttpClient httpClient = HttpClient.newConnection()
				.doOnConnected(connection ->
						connection.addHandlerLast(new ReadTimeoutHandler(10000, TimeUnit.MILLISECONDS)) // Read Timeout
								.addHandlerLast(new WriteTimeoutHandler(10000, TimeUnit.MILLISECONDS))) // Write Timeout
				.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 3000) // Connection Timeout
				.responseTimeout(Duration.ofSeconds(3));

		return webClientBuilder
				.clientConnector(new ReactorClientHttpConnector(httpClient.followRedirect(true)))
				.exchangeStrategies(ExchangeStrategies.builder()
						.codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(-1))
						.build())
				.baseUrl("https://chromedriver.storage.googleapis.com")
				.build();
	}

}
