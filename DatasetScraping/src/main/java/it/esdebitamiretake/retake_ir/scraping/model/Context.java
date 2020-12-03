package it.esdebitamiretake.retake_ir.scraping.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Context {

	@JsonProperty("id")
	private String id;

	@JsonProperty("uri")
	private String uri;

	@JsonProperty("cron")
	private String cronExp;

	@JsonProperty("css")
	private String cssQuery;

	@JsonProperty("cssRef")
	private String cssRefQuery;

	@JsonProperty("template")
	private String templateId;

	@JsonProperty("language")
	private String language;
	
	@JsonProperty("date")
	private Date date;
	
	
	public String getID() {

		return id.toString();
	}
	
	public String getUri() {

		return uri;
	}

	public String getCronExp() {

		return cronExp;
	}

	public String getCSSQuery() {

		return cssQuery;
	}
	
	public String getCSSRefQuery() {

		return cssRefQuery;
	}
	
	public String getTemplateId() {

		return templateId;
	}

	public String getLanguage() {

		return language;
	}
	
	public Date getDate() {

		return date;
	}

	public void setDate(Date date) {

		this.date=date;
	}
	
	@Override
	public String toString() {

		return String.format("%s:%s", getTemplateId(),getUri());
	}
}