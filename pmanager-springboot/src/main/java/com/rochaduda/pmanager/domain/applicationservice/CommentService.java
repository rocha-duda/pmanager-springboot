package com.rochaduda.pmanager.domain.applicationservice;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.rochaduda.pmanager.domain.entity.Comment;
import com.rochaduda.pmanager.domain.entity.Member;
import com.rochaduda.pmanager.domain.entity.Task;
import com.rochaduda.pmanager.domain.exception.CommentNotFoundException;
import com.rochaduda.pmanager.domain.repository.CommentRepository;
import com.rochaduda.pmanager.infrastructure.config.AppConfigProperties;
//import com.rochaduda.pmanager.infrastructure.config.AppConfigProperties;
import com.rochaduda.pmanager.infrastructure.dto.SaveCommentDataDTO;
import com.rochaduda.pmanager.infrastructure.util.PaginationHelper;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final MemberService memberService;
    private final TaskService taskService;
    private final CommentRepository commentRepository;
    private final AppConfigProperties props;

    @Transactional
    public Comment createComment(SaveCommentDataDTO saveCommentData){
      Task task = getTaskIfPossible(saveCommentData.getTaskId());
      Member author = getMemberIfPossible(saveCommentData.getAuthorId());

      Comment comment = Comment.builder()
                        .description(saveCommentData.getDescription())
                        .createdAt(saveCommentData.getCreatedAt())
                        .associatedTask(task)
                        .author(author).build();
      commentRepository.save(comment);
      return comment;
    }

    public Comment loadComment(String commentId){
        return commentRepository
            .findById(commentId)
            .orElseThrow(() -> new CommentNotFoundException(commentId));
    }
    @Transactional
    public void delete(String commentId){
       Comment comment = loadComment(commentId);
       commentRepository.delete(comment);
    }
    
    
    private Member getMemberIfPossible(String memberId) {
        Member member = null;
         if (!Objects.isNull(memberId)){
            member = memberService.loadMemberById(memberId);
         }
        return member;
    }
    
    private Task getTaskIfPossible(String taskId) {
    Task task = null;
    if (!Objects.isNull(taskId)) {
        task = taskService.loadTask(taskId);
    }
    return task;
}
 
    @Transactional
    public Comment updateComment(String commentId, SaveCommentDataDTO saveCommentData){
        Task task = getTaskIfPossible(saveCommentData.getTaskId());
        Member member = getMemberIfPossible(saveCommentData.getAuthorId());

        Comment comment = loadComment(commentId);
        comment.setAuthor(member);
        comment.setAssociatedTask(task);
        comment.setCreatedAt(saveCommentData.getCreatedAt());
        comment.setDescription(saveCommentData.getDescription());

        return comment;

    }

    public Page<Comment> findComments(
        String taskId,
        String author, 
        
        String description,
        Integer page,
        String direction,
        LocalDateTime createdAt,
        List<String> properties
    ){


       return commentRepository.find(
            taskId, 
            author, 
            description, 
            createdAt, 
            PaginationHelper.createPageable(page, props.getGeneral().getPageSize(), direction, properties));
    }



}
