package storeapp.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginSecurityRepository {

    private final Map<String, Integer> failedAttemptsByEmail = new HashMap<>();
    private final Map<String, Long> lockUntilByEmail = new HashMap<>();
    private final List<String> auditEvents = new ArrayList<>();

    public boolean isLocked(String email) {
        String key = normalize(email);
        Long lockUntil = lockUntilByEmail.get(key);
        if (lockUntil == null) {
            return false;
        }
        if (System.currentTimeMillis() >= lockUntil) {
            lockUntilByEmail.remove(key);
            failedAttemptsByEmail.remove(key);
            return false;
        }
        return true;
    }

    public long getRemainingLockSeconds(String email) {
        String key = normalize(email);
        Long lockUntil = lockUntilByEmail.get(key);
        if (lockUntil == null) {
            return 0L;
        }
        long millis = lockUntil - System.currentTimeMillis();
        return Math.max(0L, millis / 1000L);
    }

    public int registerFailure(String email, int maxAttempts, long lockMillis) {
        String key = normalize(email);
        int attempts = failedAttemptsByEmail.getOrDefault(key, 0) + 1;
        failedAttemptsByEmail.put(key, attempts);

        if (attempts >= maxAttempts) {
            lockUntilByEmail.put(key, System.currentTimeMillis() + lockMillis);
        }
        return attempts;
    }

    public void resetFailures(String email) {
        String key = normalize(email);
        failedAttemptsByEmail.remove(key);
        lockUntilByEmail.remove(key);
    }

    public int getFailedAttempts(String email) {
        return failedAttemptsByEmail.getOrDefault(normalize(email), 0);
    }

    public void addAuditEvent(String email, String role, boolean success, String reason) {
        String event = String.format("%s | email=%s | role=%s | success=%s | reason=%s",
                LocalDateTime.now(), normalize(email), role, success, reason);
        auditEvents.add(event);
    }

    public List<String> getAuditEvents() {
        return new ArrayList<>(auditEvents);
    }

    private String normalize(String email) {
        if (email == null) {
            return "";
        }
        return email.trim().toLowerCase();
    }
}

