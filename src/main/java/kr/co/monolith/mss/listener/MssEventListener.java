package kr.co.monolith.mss.listener;


import kr.co.monolith.dto.MssDto;
import kr.co.monolith.event.Event;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MssEventListener {

	@EventListener
	public void handleContextEvent(Event<MssDto> event) {
		log.info("Received Spring Event Message={}", event.getBody());
	}

}
