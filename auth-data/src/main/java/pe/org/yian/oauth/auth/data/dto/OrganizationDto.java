package pe.org.yian.oauth.auth.data.dto;

public class OrganizationDto {
	private String code;
	private String name;

	public OrganizationDto() {
		super();
	}

	public OrganizationDto(String code, String name) {
		super();
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
