package kr.co.monolith.event;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class EventPublisher<T> {

	private final ApplicationEventPublisher applicationEventPublisher;

	public void publish(final T body) {
		log.info("Publishing generic Event.");

		Event<T> event = new Event<>(body);
		applicationEventPublisher.publishEvent(event);
	}

}
