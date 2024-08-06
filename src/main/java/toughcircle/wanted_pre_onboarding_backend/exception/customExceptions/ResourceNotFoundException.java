package toughcircle.wanted_pre_onboarding_backend.exception.customExceptions;

// 조회 결과 존재하지 않는 경우
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
