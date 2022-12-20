package s.praveen.cms.model.ro;

import jakarta.validation.constraints.NotNull;

/**
 * The type Student request.
 */
public record StudentRequest(@NotNull String studentName) {
}
