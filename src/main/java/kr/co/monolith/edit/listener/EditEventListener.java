package kr.co.monolith.edit.listener;


import kr.co.monolith.dto.EditDto;
import kr.co.monolith.event.Event;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class EditEventListener {

	@EventListener
	public void handleContextEvent(Event<EditDto> event) {
		log.info("Received Spring Event Message={}", event.getBody());
	}

}
