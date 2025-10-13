package com.boehringer.componentprovisioner.server.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.time.OffsetDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * ProvisionActionResponse
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.10.0")
public class ProvisionActionResponse {

  private Boolean failed;

  private Integer id;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime created;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime modified;

  public ProvisionActionResponse failed(Boolean failed) {
    this.failed = failed;
    return this;
  }

  /**
   * Get failed
   * @return failed
   */
  
  @Schema(name = "failed", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("failed")
  public Boolean getFailed() {
    return failed;
  }

  public void setFailed(Boolean failed) {
    this.failed = failed;
  }

  public ProvisionActionResponse id(Integer id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
   */
  
  @Schema(name = "id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("id")
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public ProvisionActionResponse created(OffsetDateTime created) {
    this.created = created;
    return this;
  }

  /**
   * Get created
   * @return created
   */
  @Valid 
  @Schema(name = "created", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("created")
  public OffsetDateTime getCreated() {
    return created;
  }

  public void setCreated(OffsetDateTime created) {
    this.created = created;
  }

  public ProvisionActionResponse modified(OffsetDateTime modified) {
    this.modified = modified;
    return this;
  }

  /**
   * Get modified
   * @return modified
   */
  @Valid 
  @Schema(name = "modified", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("modified")
  public OffsetDateTime getModified() {
    return modified;
  }

  public void setModified(OffsetDateTime modified) {
    this.modified = modified;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProvisionActionResponse provisionActionResponse = (ProvisionActionResponse) o;
    return Objects.equals(this.failed, provisionActionResponse.failed) &&
        Objects.equals(this.id, provisionActionResponse.id) &&
        Objects.equals(this.created, provisionActionResponse.created) &&
        Objects.equals(this.modified, provisionActionResponse.modified);
  }

  @Override
  public int hashCode() {
    return Objects.hash(failed, id, created, modified);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProvisionActionResponse {\n");
    sb.append("    failed: ").append(toIndentedString(failed)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    created: ").append(toIndentedString(created)).append("\n");
    sb.append("    modified: ").append(toIndentedString(modified)).append("\n");
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

    private ProvisionActionResponse instance;

    public Builder() {
      this(new ProvisionActionResponse());
    }

    protected Builder(ProvisionActionResponse instance) {
      this.instance = instance;
    }

    protected Builder copyOf(ProvisionActionResponse value) { 
      this.instance.setFailed(value.failed);
      this.instance.setId(value.id);
      this.instance.setCreated(value.created);
      this.instance.setModified(value.modified);
      return this;
    }

    public ProvisionActionResponse.Builder failed(Boolean failed) {
      this.instance.failed(failed);
      return this;
    }
    
    public ProvisionActionResponse.Builder id(Integer id) {
      this.instance.id(id);
      return this;
    }
    
    public ProvisionActionResponse.Builder created(OffsetDateTime created) {
      this.instance.created(created);
      return this;
    }
    
    public ProvisionActionResponse.Builder modified(OffsetDateTime modified) {
      this.instance.modified(modified);
      return this;
    }
    
    /**
    * returns a built ProvisionActionResponse instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public ProvisionActionResponse build() {
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
  public static ProvisionActionResponse.Builder builder() {
    return new ProvisionActionResponse.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public ProvisionActionResponse.Builder toBuilder() {
    ProvisionActionResponse.Builder builder = new ProvisionActionResponse.Builder();
    return builder.copyOf(this);
  }

}

