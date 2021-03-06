package it.esdebitamiretake.retake_ticket.collection;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "activities")
@JsonInclude(JsonInclude.Include.ALWAYS)
@ApiModel(description = "Model to describe a specific activity to create a report")
public class Activity {
	
	@Id
	@Field("_id")
	private ObjectId id;
	
	@Indexed(unique=true)
	@Field("name")
	@JsonProperty("name")
	private String name;
	
	@Indexed(unique=true)
	@Field("activityCode")
	@JsonProperty("activityCode")
	private Integer activityCode;
	
	
	public Activity() {
		
	}

	public Activity(String name, Integer activityCode) {
		this.name = name;
		this.activityCode = activityCode;
	}

	public String getId() {
		
		return id.toString();
	}

	public String getName() {
		
		return name;
	}

	public Integer getActivityCode() {
		return activityCode;
	}

	public void setActivityCode(Integer activityCode) {
		this.activityCode = activityCode;
	}

	@Override
	public String toString() {
		
		return getName();
	}
}