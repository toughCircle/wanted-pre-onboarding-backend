package toughcircle.wanted_pre_onboarding_backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import toughcircle.wanted_pre_onboarding_backend.exception.customExceptions.DuplicateSubmissionException;
import toughcircle.wanted_pre_onboarding_backend.exception.customExceptions.ResourceNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException exception) {
        // 존재하지 않는 데이터를 요청
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler(DuplicateSubmissionException.class)
    public ResponseEntity<String> handleDuplicateSubmissionException(DuplicateSubmissionException exception) {
        // 이미 데이터가 존재하여 리소스가 충돌
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
    }
}
