package it.esdebitamiretake.retake_ticket.anagrafica;

import javax.validation.constraints.NotEmpty;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserPassword {

	@JsonProperty("password")
	@NotEmpty
	private String password;

	@JsonProperty("oldPassword")
	@NotEmpty
	private String oldPassword;
	
	public String getPassword() {

		return password;
	}

	public String getOldPassword() {

		return oldPassword;
	}
}