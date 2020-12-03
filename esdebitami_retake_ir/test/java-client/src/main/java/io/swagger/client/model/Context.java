/*
 * Api Documentation
 * Api Documentation
 *
 * OpenAPI spec version: 1.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package io.swagger.client.model;

import java.util.Objects;
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;
import org.threeten.bp.OffsetDateTime;

/**
 * A context associated with a specific template
 */
@ApiModel(description = "A context associated with a specific template")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2020-05-13T11:19:56.034Z")
public class Context {
  @SerializedName("cron")
  private String cron = null;

  @SerializedName("css")
  private String css = null;

  @SerializedName("cssRef")
  private String cssRef = null;

  @SerializedName("date")
  private OffsetDateTime date = null;

  @SerializedName("id")
  private String id = null;

  /**
   * The ISO 639-1 code of the language used in context resources
   */
  @JsonAdapter(LanguageEnum.Adapter.class)
  public enum LanguageEnum {
    EN("EN"),
    
    IT("IT"),
    
    DA("DA"),
    
    DE("DE"),
    
    NL("NL"),
    
    PT("PT"),
    
    SE("SE");

    private String value;

    LanguageEnum(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    public static LanguageEnum fromValue(String text) {
      for (LanguageEnum b : LanguageEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }

    public static class Adapter extends TypeAdapter<LanguageEnum> {
      @Override
      public void write(final JsonWriter jsonWriter, final LanguageEnum enumeration) throws IOException {
        jsonWriter.value(enumeration.getValue());
      }

      @Override
      public LanguageEnum read(final JsonReader jsonReader) throws IOException {
        String value = jsonReader.nextString();
        return LanguageEnum.fromValue(String.valueOf(value));
      }
    }
  }

  @SerializedName("language")
  private LanguageEnum language = null;

  @SerializedName("template")
  private String template = null;

  @SerializedName("uri")
  private String uri = null;

  public Context cron(String cron) {
    this.cron = cron;
    return this;
  }

   /**
   * The cron expression that describe individual details of the schedule.
   * @return cron
  **/
  @ApiModelProperty(value = "The cron expression that describe individual details of the schedule.")
  public String getCron() {
    return cron;
  }

  public void setCron(String cron) {
    this.cron = cron;
  }

  public Context css(String css) {
    this.css = css;
    return this;
  }

   /**
   * The css query to find resources within the context
   * @return css
  **/
  @ApiModelProperty(value = "The css query to find resources within the context")
  public String getCss() {
    return css;
  }

  public void setCss(String css) {
    this.css = css;
  }

  public Context cssRef(String cssRef) {
    this.cssRef = cssRef;
    return this;
  }

   /**
   * The css query to find the reference (e.g.: title) within the context resource
   * @return cssRef
  **/
  @ApiModelProperty(value = "The css query to find the reference (e.g.: title) within the context resource")
  public String getCssRef() {
    return cssRef;
  }

  public void setCssRef(String cssRef) {
    this.cssRef = cssRef;
  }

  public Context date(OffsetDateTime date) {
    this.date = date;
    return this;
  }

   /**
   * Get date
   * @return date
  **/
  @ApiModelProperty(value = "")
  public OffsetDateTime getDate() {
    return date;
  }

  public void setDate(OffsetDateTime date) {
    this.date = date;
  }

  public Context id(String id) {
    this.id = id;
    return this;
  }

   /**
   * The database generated context ID
   * @return id
  **/
  @ApiModelProperty(value = "The database generated context ID")
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Context language(LanguageEnum language) {
    this.language = language;
    return this;
  }

   /**
   * The ISO 639-1 code of the language used in context resources
   * @return language
  **/
  @ApiModelProperty(value = "The ISO 639-1 code of the language used in context resources")
  public LanguageEnum getLanguage() {
    return language;
  }

  public void setLanguage(LanguageEnum language) {
    this.language = language;
  }

  public Context template(String template) {
    this.template = template;
    return this;
  }

   /**
   * The template to which the context refers
   * @return template
  **/
  @ApiModelProperty(value = "The template to which the context refers")
  public String getTemplate() {
    return template;
  }

  public void setTemplate(String template) {
    this.template = template;
  }

  public Context uri(String uri) {
    this.uri = uri;
    return this;
  }

   /**
   * The sources of the context
   * @return uri
  **/
  @ApiModelProperty(value = "The sources of the context")
  public String getUri() {
    return uri;
  }

  public void setUri(String uri) {
    this.uri = uri;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Context context = (Context) o;
    return Objects.equals(this.cron, context.cron) &&
        Objects.equals(this.css, context.css) &&
        Objects.equals(this.cssRef, context.cssRef) &&
        Objects.equals(this.date, context.date) &&
        Objects.equals(this.id, context.id) &&
        Objects.equals(this.language, context.language) &&
        Objects.equals(this.template, context.template) &&
        Objects.equals(this.uri, context.uri);
  }

  @Override
  public int hashCode() {
    return Objects.hash(cron, css, cssRef, date, id, language, template, uri);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Context {\n");
    
    sb.append("    cron: ").append(toIndentedString(cron)).append("\n");
    sb.append("    css: ").append(toIndentedString(css)).append("\n");
    sb.append("    cssRef: ").append(toIndentedString(cssRef)).append("\n");
    sb.append("    date: ").append(toIndentedString(date)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    language: ").append(toIndentedString(language)).append("\n");
    sb.append("    template: ").append(toIndentedString(template)).append("\n");
    sb.append("    uri: ").append(toIndentedString(uri)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}
