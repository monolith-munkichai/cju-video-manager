package kr.co.monolith.event;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.core.ResolvableType;
import org.springframework.core.ResolvableTypeProvider;


@Getter
@AllArgsConstructor
public class Event<T> implements ResolvableTypeProvider {

	private T body;

	@Override
	public ResolvableType getResolvableType() {
		return ResolvableType.forClassWithGenerics(getClass(), ResolvableType.forInstance(this.body));
	}

}
