package com.boehringer.componentprovisioner.server.model;

import java.net.URI;
import java.util.Objects;
import com.boehringer.componentprovisioner.server.model.ProvisionerMessageDefinitionType;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * ProvisionerMessageDefinition
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.10.0")
public class ProvisionerMessageDefinition {

  private String id;

  private ProvisionerMessageDefinitionType type;

  private String title;

  private String message;

  public ProvisionerMessageDefinition id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
   */
  
  @Schema(name = "id", example = "OPENSHIFT_CONNECTION_ERROR", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("id")
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public ProvisionerMessageDefinition type(ProvisionerMessageDefinitionType type) {
    this.type = type;
    return this;
  }

  /**
   * Get type
   * @return type
   */
  @Valid 
  @Schema(name = "type", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("type")
  public ProvisionerMessageDefinitionType getType() {
    return type;
  }

  public void setType(ProvisionerMessageDefinitionType type) {
    this.type = type;
  }

  public ProvisionerMessageDefinition title(String title) {
    this.title = title;
    return this;
  }

  /**
   * Get title
   * @return title
   */
  
  @Schema(name = "title", example = "An error occurred while connecting to OpenShift", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("title")
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public ProvisionerMessageDefinition message(String message) {
    this.message = message;
    return this;
  }

  /**
   * Get message
   * @return message
   */
  
  @Schema(name = "message", example = "Authorization error: please check your user credentials for deployment  and try again later. ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("message")
  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProvisionerMessageDefinition provisionerMessageDefinition = (ProvisionerMessageDefinition) o;
    return Objects.equals(this.id, provisionerMessageDefinition.id) &&
        Objects.equals(this.type, provisionerMessageDefinition.type) &&
        Objects.equals(this.title, provisionerMessageDefinition.title) &&
        Objects.equals(this.message, provisionerMessageDefinition.message);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type, title, message);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProvisionerMessageDefinition {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
  
  public static class Builder {

    private ProvisionerMessageDefinition instance;

    public Builder() {
      this(new ProvisionerMessageDefinition());
    }

    protected Builder(ProvisionerMessageDefinition instance) {
      this.instance = instance;
    }

    protected Builder copyOf(ProvisionerMessageDefinition value) { 
      this.instance.setId(value.id);
      this.instance.setType(value.type);
      this.instance.setTitle(value.title);
      this.instance.setMessage(value.message);
      return this;
    }

    public ProvisionerMessageDefinition.Builder id(String id) {
      this.instance.id(id);
      return this;
    }
    
    public ProvisionerMessageDefinition.Builder type(ProvisionerMessageDefinitionType type) {
      this.instance.type(type);
      return this;
    }
    
    public ProvisionerMessageDefinition.Builder title(String title) {
      this.instance.title(title);
      return this;
    }
    
    public ProvisionerMessageDefinition.Builder message(String message) {
      this.instance.message(message);
      return this;
    }
    
    /**
    * returns a built ProvisionerMessageDefinition instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public ProvisionerMessageDefinition build() {
      try {
        return this.instance;
      } finally {
        // ensure that this.instance is not reused
        this.instance = null;
      }
    }

    @Override
    public String toString() {
      return getClass() + "=(" + instance + ")";
    }
  }

  /**
  * Create a builder with no initialized field (except for the default values).
  */
  public static ProvisionerMessageDefinition.Builder builder() {
    return new ProvisionerMessageDefinition.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public ProvisionerMessageDefinition.Builder toBuilder() {
    ProvisionerMessageDefinition.Builder builder = new ProvisionerMessageDefinition.Builder();
    return builder.copyOf(this);
  }

}

