package kr.co.monolith.core.event;


public class EditEvent extends AbstractEvent {

	public EditEvent(Object source, String message) {
		super(source);
		this.message = message;
	}

}
