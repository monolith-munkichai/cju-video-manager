package kr.co.monolith.admin;


import kr.co.monolith.dto.CdnDto;
import kr.co.monolith.dto.EditDto;
import kr.co.monolith.dto.MssDto;
import kr.co.monolith.event.EventPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {

	private final EventPublisher<CdnDto> cdnEventPublisher;
	private final EventPublisher<EditDto> editEventPublisher;
	private final EventPublisher<MssDto> mssEventPublisher;

	@GetMapping("/cdn")
	public String cdn() {
		cdnEventPublisher.publish(CdnDto.builder()
				.wkfId(System.currentTimeMillis())
				.message("video-manager-cdn")
				.build());

		return "Published CDN Event!!!";
	}

	@GetMapping("/edit")
	public String edit() {
		editEventPublisher.publish(EditDto.builder()
				.wkfId(System.currentTimeMillis())
				.message("video-manager-edit")
				.build());

		return "Published EDIT Event!!!";
	}

	@GetMapping("/mss")
	public String mss() {
		mssEventPublisher.publish(MssDto.builder()
				.wkfId(System.currentTimeMillis())
				.message("video-manager-mss")
				.build());

		return "Published MSS Event!!!";
	}

}
