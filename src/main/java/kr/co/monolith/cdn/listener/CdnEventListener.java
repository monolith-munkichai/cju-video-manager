package kr.co.monolith.cdn.listener;


import kr.co.monolith.dto.CdnDto;
import kr.co.monolith.event.Event;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@RequiredArgsConstructor
public class CdnEventListener {

	@EventListener
	public void handleContextEvent(Event<CdnDto> event) {
		log.info("Received Spring Event Message={}", event.getBody());
	}

}
