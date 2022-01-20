package gov.va.api.lighthouse.facilities.api.v1;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import gov.va.api.lighthouse.facilities.api.v1.serializers.PaginationSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;
import org.apache.commons.lang3.ObjectUtils;

@Value
@Builder
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonInclude(value = Include.NON_EMPTY, content = Include.NON_EMPTY)
@JsonSerialize(using = PaginationSerializer.class)
public final class Pagination implements CanBeEmpty {
  @NotNull
  @Schema(example = "1")
  @JsonProperty("current_page")
  Integer currentPage;

  @NotNull
  @Schema(example = "10")
  @JsonProperty("per_page")
  Integer entriesPerPage;

  @NotNull
  @Schema(example = "217")
  @JsonProperty("total_pages")
  Integer totalPages;

  @NotNull
  @Schema(example = "2162")
  @JsonProperty("total_entries")
  Integer totalEntries;

  /** Empty elements will be omitted from JSON serialization. */
  @JsonIgnore
  public boolean isEmpty() {
    return ObjectUtils.isEmpty(currentPage())
        && ObjectUtils.isEmpty(entriesPerPage())
        && ObjectUtils.isEmpty(totalPages())
        && ObjectUtils.isEmpty(totalEntries());
  }
}
