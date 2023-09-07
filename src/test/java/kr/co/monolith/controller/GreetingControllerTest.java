package kr.co.monolith.controller;


import kr.co.monolith.dto.GreetingDto;
import kr.co.monolith.service.GreetingService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = GreetingController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE))
public class GreetingControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private GreetingService greetingService;

	@Test
	@DisplayName("[GreetingController]인사 테스트")
	void getGreeting() throws Exception {

		String name = "홍길동";
		String text = "Hello, " + name;

		GreetingDto greetingDto = GreetingDto
				.builder()
				.name(name)
				.build();

		given(greetingService.sayHello(greetingDto))
				.willReturn(text);

		RequestBuilder request = MockMvcRequestBuilders
				.get("/greetings")
				.param("name", name);

		mockMvc.perform(request)
				.andExpect(status().isOk())
				.andReturn();

	}

}
