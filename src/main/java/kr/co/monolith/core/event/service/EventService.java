package kr.co.monolith.core.event.service;


import kr.co.monolith.core.event.AbstractEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventService {

	private final ApplicationEventPublisher applicationEventPublisher;

	public void publish(final AbstractEvent event) {
		log.info("Publishing custom event message={}", event.getMessage());
		applicationEventPublisher.publishEvent(event);
	}

}
