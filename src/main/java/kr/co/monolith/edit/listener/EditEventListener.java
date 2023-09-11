package kr.co.monolith.edit.listener;


import kr.co.monolith.core.event.EditEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class EditEventListener {

	@EventListener
	public void handleContextEvent(EditEvent event) {
		log.info("Received Spring Event Message={}", event.getMessage());
	}

}
