package kr.co.monolith.service;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@Service
@RequiredArgsConstructor
public class ChromeService {

	private final WebClient chromeClient;

	public Mono<ResponseEntity<String>> getVersion() {

		return chromeClient.get()
				.uri("/LATEST_RELEASE")
				.retrieve()
				.toEntity(String.class);
	}

}
