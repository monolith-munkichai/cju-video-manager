package kr.co.monolith.core.event;


import org.springframework.context.ApplicationEvent;


public class AbstractEvent extends ApplicationEvent {

	protected String message;

	public AbstractEvent(Object source) {
		super(source);
	}

	public String getMessage() {
		return message;
	}

}
