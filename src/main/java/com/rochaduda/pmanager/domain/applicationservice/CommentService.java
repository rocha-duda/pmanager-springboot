package com.rochaduda.pmanager.domain.applicationservice;

import java.util.Objects;

import org.springframework.stereotype.Service;

import com.rochaduda.pmanager.domain.entity.Comment;
import com.rochaduda.pmanager.domain.entity.Member;
import com.rochaduda.pmanager.domain.entity.Task;
import com.rochaduda.pmanager.domain.repository.CommentRepository;
//import com.rochaduda.pmanager.infrastructure.config.AppConfigProperties;
import com.rochaduda.pmanager.infrastructure.dto.SaveCommentDataDTO;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final MemberService memberService;
    private final TaskService taskService;
    private final CommentRepository commentRepository;
    //private final AppConfigProperties props;

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


}
