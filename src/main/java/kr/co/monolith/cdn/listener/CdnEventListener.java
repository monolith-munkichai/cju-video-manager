package kr.co.monolith.cdn.listener;


import kr.co.monolith.core.event.CdnEvent;
import kr.co.monolith.core.event.MssEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class CdnEventListener {

	@EventListener
	public void handleContextEvent(CdnEvent event) {
		log.info("Received Spring Event Message={}", event.getMessage());
	}

}
