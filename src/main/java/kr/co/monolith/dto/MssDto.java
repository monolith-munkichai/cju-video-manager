package kr.co.monolith.dto;


import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class MssDto {
	private long wkfId;
	private String message;
}
