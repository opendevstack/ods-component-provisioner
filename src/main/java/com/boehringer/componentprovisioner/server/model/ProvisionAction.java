package com.boehringer.componentprovisioner.server.model;

import java.net.URI;
import java.util.Objects;
import com.boehringer.componentprovisioner.server.model.ProvisionActionParameter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * ProvisionAction
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.10.0")
public class ProvisionAction {

  private String id;

  @Valid
  private List<@Valid ProvisionActionParameter> parameters = new ArrayList<>();

  public ProvisionAction id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
   */
  
  @Schema(name = "id", example = "PROVISION", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("id")
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public ProvisionAction parameters(List<@Valid ProvisionActionParameter> parameters) {
    this.parameters = parameters;
    return this;
  }

  public ProvisionAction addParametersItem(ProvisionActionParameter parametersItem) {
    if (this.parameters == null) {
      this.parameters = new ArrayList<>();
    }
    this.parameters.add(parametersItem);
    return this;
  }

  /**
   * Get parameters
   * @return parameters
   */
  @Valid 
  @Schema(name = "parameters", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("parameters")
  public List<@Valid ProvisionActionParameter> getParameters() {
    return parameters;
  }

  public void setParameters(List<@Valid ProvisionActionParameter> parameters) {
    this.parameters = parameters;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProvisionAction provisionAction = (ProvisionAction) o;
    return Objects.equals(this.id, provisionAction.id) &&
        Objects.equals(this.parameters, provisionAction.parameters);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, parameters);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProvisionAction {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    parameters: ").append(toIndentedString(parameters)).append("\n");
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

    private ProvisionAction instance;

    public Builder() {
      this(new ProvisionAction());
    }

    protected Builder(ProvisionAction instance) {
      this.instance = instance;
    }

    protected Builder copyOf(ProvisionAction value) { 
      this.instance.setId(value.id);
      this.instance.setParameters(value.parameters);
      return this;
    }

    public ProvisionAction.Builder id(String id) {
      this.instance.id(id);
      return this;
    }
    
    public ProvisionAction.Builder parameters(List<@Valid ProvisionActionParameter> parameters) {
      this.instance.parameters(parameters);
      return this;
    }
    
    /**
    * returns a built ProvisionAction instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public ProvisionAction build() {
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
  public static ProvisionAction.Builder builder() {
    return new ProvisionAction.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public ProvisionAction.Builder toBuilder() {
    ProvisionAction.Builder builder = new ProvisionAction.Builder();
    return builder.copyOf(this);
  }

}

