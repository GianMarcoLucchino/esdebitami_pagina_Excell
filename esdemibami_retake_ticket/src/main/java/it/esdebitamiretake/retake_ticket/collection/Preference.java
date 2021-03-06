package it.esdebitamiretake.retake_ticket.collection;

import org.springframework.data.mongodb.core.mapping.Field;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;

@JsonInclude(JsonInclude.Include.ALWAYS)
@ApiModel(description = "General model of a preference to save settings in the application")
public class Preference {
	
	@Field("name")
	@JsonProperty("name")
	private String name;
	
	@Field("value")
	@JsonProperty("value")
	private Object value;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public Preference(String name, String value) {
		this.name = name;
		this.value = value;
	}

	public Preference(String name) {
		this.name = name;
	}

	public Preference() {
	}

	public Preference(String name, Object value) {
		this.name = name;
		this.value = value;
	}
	

}
