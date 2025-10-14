package com.rochaduda.pmanager.infrastructure.controller;

import java.net.URI;



import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rochaduda.pmanager.domain.applicationservice.CommentService;
import com.rochaduda.pmanager.domain.entity.Comment;
import com.rochaduda.pmanager.infrastructure.dto.CommentDTO;
import com.rochaduda.pmanager.infrastructure.dto.SaveCommentDataDTO;


import jakarta.validation.Valid;

import static com.rochaduda.pmanager.infrastructure.controller.RestConstants.PATH_COMMENTS;



import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(PATH_COMMENTS)
@RequiredArgsConstructor
public class CommentRestResource {

  private final CommentService commentService;
  
  @PostMapping
  public ResponseEntity<CommentDTO> createComment(@RequestBody @Valid SaveCommentDataDTO saveCommentData){
      
    Comment comment = commentService.createComment(saveCommentData);

     return ResponseEntity
    .created(URI.create(PATH_COMMENTS + "/" + comment.getId()))
    .body(CommentDTO.create(comment));
    }
  @GetMapping
  public ResponseEntity<CommentDTO> loadComment(@PathVariable("id") String commentId){
    Comment comment = commentService.loadComment(commentId);
    return ResponseEntity.ok(
      CommentDTO.create(comment)
    );
  }
  
}
