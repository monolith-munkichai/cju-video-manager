package kr.co.monolith.service;


import kr.co.monolith.dto.GreetingDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class GreetingService {

	public String sayHello(GreetingDto greetingDto) {

		return "Hello, " + greetingDto.getName();
	}

}
