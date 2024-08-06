package toughcircle.wanted_pre_onboarding_backend.exception.customExceptions;

// 사용자의 지원 내역이 이미 존재하는 경우
public class DuplicateSubmissionException extends RuntimeException {
    public DuplicateSubmissionException(String message) {
        super(message);
    }
}