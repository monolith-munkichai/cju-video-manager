package kr.co.monolith.controller;


import kr.co.monolith.dto.GreetingDto;
import kr.co.monolith.packet.greeting.GreetingReq;
import kr.co.monolith.packet.greeting.GreetingRes;
import kr.co.monolith.service.GreetingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/greetings")
@RequiredArgsConstructor
public class GreetingController {

	private final GreetingService greetingService;

	@GetMapping("")
	public ResponseEntity<GreetingRes> getGreeting(GreetingReq greetingReq) {
		log.info("Greeting for me={}", greetingReq.getName());
		GreetingDto greetingDto = GreetingDto.builder()
				.name(greetingReq.getName())
				.build();

		return ResponseEntity.ok(GreetingRes.builder()
				.name("world")
				.text(greetingService.sayHello(greetingDto))
				.build());
	}

}
