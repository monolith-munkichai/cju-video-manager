package kr.co.monolith.packet.greeting;


import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class GreetingRes {

	private String name;
	private String text;

}
