package kr.co.monolith.api.controller;


import kr.co.monolith.core.event.CdnEvent;
import kr.co.monolith.core.event.EditEvent;
import kr.co.monolith.core.event.MssEvent;
import kr.co.monolith.core.event.service.EventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class ApiController {

	private final EventService eventService;

	@GetMapping("/cdn")
	public String cdn() {
		eventService.publish(new CdnEvent(this, "video-manager-cdn"));

		return "Published CDN Event!!!";
	}

	@GetMapping("/edit")
	public String edit() {
		eventService.publish(new EditEvent(this, "video-manager-edit"));

		return "Published EDIT Event!!!";
	}

	@GetMapping("/mss")
	public String mss() {
		eventService.publish(new MssEvent(this, "video-manager-mss"));

		return "Published MSS Event!!!";
	}

}
