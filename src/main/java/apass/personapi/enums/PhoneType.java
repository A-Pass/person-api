package apass.personapi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PhoneType {

	HOME("Home"),
	MOBILE("Mobile"),
	COMMERCIAL("Commercial");


	public final String description;
}
