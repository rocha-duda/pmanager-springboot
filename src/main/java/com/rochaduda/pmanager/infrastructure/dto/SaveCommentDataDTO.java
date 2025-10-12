package com.rochaduda.pmanager.infrastructure.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SaveCommentDataDTO {
  
  @NotNull(message = "comment cannot be empty")
  @Size(min=1, max=300, message = "Invalid comment")
  private final String description;
  
  @NotNull
  private final LocalDateTime date;

  private final String taskId;
  private final String authorId;
}
