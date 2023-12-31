package kr.co.monolith.admin;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping("/upload")
public class UploadController {

	@GetMapping
	public ResponseEntity upload() {
		return ResponseEntity.ok().build();
	}

}
