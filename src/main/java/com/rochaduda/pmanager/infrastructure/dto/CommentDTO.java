package com.rochaduda.pmanager.infrastructure.dto;

import java.time.LocalDateTime;
import java.util.Optional;

import com.rochaduda.pmanager.domain.entity.Comment;

import lombok.Data;
//Optional.ofNullable(task.getProject()).map(ProjectDTO::create).orElse(null)
@Data
public class CommentDTO {
  
  private final String id;
  private final String description;
  private final LocalDateTime date;

  private final TaskDTO task;
  private final MemberDTO author;

  public static CommentDTO create(Comment comment){
    return new CommentDTO(
      comment.getId(), 
      comment.getDescription(), 
      comment.getCommenLocalDateTime(), 
      Optional.ofNullable(comment.getAssociatedTask()).map(TaskDTO::create).orElse(null),
      Optional.ofNullable(comment.getAuthor()).map(MemberDTO::create).orElse(null)
      );

  }
}
