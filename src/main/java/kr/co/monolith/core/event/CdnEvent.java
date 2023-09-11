package kr.co.monolith.core.event;


public class CdnEvent extends AbstractEvent {

	public CdnEvent(Object source, String message) {
		super(source);
		this.message = message;
	}

}
