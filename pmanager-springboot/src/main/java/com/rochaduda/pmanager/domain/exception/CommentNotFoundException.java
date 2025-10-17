package com.rochaduda.pmanager.domain.exception;

import com.rochaduda.pmanager.infrastructure.exception.RequestException;

public class CommentNotFoundException extends RequestException{
      public CommentNotFoundException(String commentId){
        super("CommentNotFound", "Comment not found: " + commentId);
}
}
