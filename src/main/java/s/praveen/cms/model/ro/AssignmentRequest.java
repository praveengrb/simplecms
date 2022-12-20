package s.praveen.cms.model.ro;

import jakarta.validation.constraints.NotNull;

/**
 * The type Assignment request.
 */
public record AssignmentRequest(@NotNull String assignmentName, @NotNull long courseId) {

}
