package kr.co.monolith.core.event;


public class MssEvent extends AbstractEvent {

	public MssEvent(Object source, String message) {
		super(source);
		this.message = message;
	}

}
