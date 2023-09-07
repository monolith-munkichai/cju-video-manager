package kr.co.monolith.service;


import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.test.StepVerifier;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(MockitoExtension.class)
public class ChromeServiceTest {

	private ChromeService chromeService;

	private MockWebServer server;

	@BeforeEach
	void setUp() {
		server = new MockWebServer();
		chromeService = new ChromeService(WebClient.builder()
				.baseUrl(server.url("").toString())
				.build());
	}

	@AfterEach
	void shutdown() throws IOException {
		server.shutdown();
	}

	@Test
	@DisplayName("[ChromeService]getVersion")
	public void getVersion() {

		MockResponse mockResponse = new MockResponse()
				.addHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
				.setResponseCode(HttpStatus.OK.value());

		server.enqueue(mockResponse);

		StepVerifier.create(chromeService.getVersion())
				.consumeNextWith(entity -> assertThat(HttpStatus.OK == entity.getStatusCode()).isTrue())
				.verifyComplete();

	}

}
