package com.boehringer.componentprovisioner.server.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * ProvisionActionParameter
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.10.0")
public class ProvisionActionParameter {

  private String name;

  private String type;

  private Object value;

  public ProvisionActionParameter name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
   */
  
  @Schema(name = "name", example = "workflow", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ProvisionActionParameter type(String type) {
    this.type = type;
    return this;
  }

  /**
   * Get type
   * @return type
   */
  
  @Schema(name = "type", example = "string", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("type")
  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public ProvisionActionParameter value(Object value) {
    this.value = value;
    return this;
  }

  /**
   * Get value
   * @return value
   */
  
  @Schema(name = "value", example = "2558", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("value")
  public Object getValue() {
    return value;
  }

  public void setValue(Object value) {
    this.value = value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProvisionActionParameter provisionActionParameter = (ProvisionActionParameter) o;
    return Objects.equals(this.name, provisionActionParameter.name) &&
        Objects.equals(this.type, provisionActionParameter.type) &&
        Objects.equals(this.value, provisionActionParameter.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, type, value);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProvisionActionParameter {\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    value: ").append(toIndentedString(value)).append("\n");
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

    private ProvisionActionParameter instance;

    public Builder() {
      this(new ProvisionActionParameter());
    }

    protected Builder(ProvisionActionParameter instance) {
      this.instance = instance;
    }

    protected Builder copyOf(ProvisionActionParameter value) { 
      this.instance.setName(value.name);
      this.instance.setType(value.type);
      this.instance.setValue(value.value);
      return this;
    }

    public ProvisionActionParameter.Builder name(String name) {
      this.instance.name(name);
      return this;
    }
    
    public ProvisionActionParameter.Builder type(String type) {
      this.instance.type(type);
      return this;
    }
    
    public ProvisionActionParameter.Builder value(Object value) {
      this.instance.value(value);
      return this;
    }
    
    /**
    * returns a built ProvisionActionParameter instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public ProvisionActionParameter build() {
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
  public static ProvisionActionParameter.Builder builder() {
    return new ProvisionActionParameter.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public ProvisionActionParameter.Builder toBuilder() {
    ProvisionActionParameter.Builder builder = new ProvisionActionParameter.Builder();
    return builder.copyOf(this);
  }

}

