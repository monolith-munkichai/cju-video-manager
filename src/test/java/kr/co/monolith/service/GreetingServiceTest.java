package kr.co.monolith.service;


import kr.co.monolith.dto.GreetingDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class GreetingServiceTest {

	@InjectMocks
	private GreetingService greetingService;

	@Test
	@DisplayName("[GreetingService]sayHello")
	public void sayHello() {

		String name = "홍길동";
		String text = "Hello, " + name;
		GreetingDto greetingDto = GreetingDto.builder()
				.name(name)
				.build();

		String greetingText = greetingService.sayHello(greetingDto);

		Assertions.assertEquals(greetingText, text);
	}

}
