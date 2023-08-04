package kr.spring.library.facility.vo;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = {"facility_image"})
public class FacilityVO {
	
	private int facility_num;
	@NotEmpty
	private String facility_name;
	private String facility_content;
	private byte[] facility_image;
}
