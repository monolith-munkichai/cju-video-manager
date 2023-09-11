package kr.co.monolith.mss.listener;


import kr.co.monolith.core.event.MssEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MssEventListener {

	@EventListener
	public void handleContextEvent(MssEvent event) {
		log.info("Received Spring Event Message={}", event.getMessage());
	}

}
