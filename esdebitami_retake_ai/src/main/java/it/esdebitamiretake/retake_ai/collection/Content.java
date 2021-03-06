package it.esdebitamiretake.retake_ai.collection;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "contents")
@JsonInclude(JsonInclude.Include.ALWAYS)
@ApiModel(description = "All details about the Content of a Resource.")
@CompoundIndex(name = "text_resource", unique = true, def = "{'text' : 1, 'resource': 1}")
public class Content {

	@Id
	@Field("_id")
	@JsonProperty("id")
	@ApiModelProperty(notes = "The database generated content ID")
	private ObjectId id;

	@Field("text")
	@JsonProperty("text")
	@NotBlank
	@TextIndexed
	private String text;

	@Field("resource")
	@JsonProperty("resource")
	@ApiModelProperty(notes = "The resource to which the content refers")
	@NotNull
	private ObjectId resourceId;

	@JsonIgnore
	@Field("embedding")
	private double[] embedding;
		
	public String getId() {

		return id.toString();
	}

	public String getText() {

		return this.text;
	}

	public String getResourceId() {

		return this.resourceId.toString();
	}

	@JsonIgnore
	public double[] getEmbedding() {

		return this.embedding;
	}
	
	public void setText(String text) {

		this.text=text;
	}
	
	public void setResourceId(String resourceId) {

		this.resourceId = new ObjectId(resourceId);
	}

	public void setEmbeddings(double[] embedding) {
		
		this.embedding=embedding;
	}

	@Override
	public String toString() {

		return this.getText();
	}

}