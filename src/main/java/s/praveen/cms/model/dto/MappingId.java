package s.praveen.cms.model.dto;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.With;

import java.io.Serializable;


/**
 * The type Mapping id.
 */
@Embeddable
@NoArgsConstructor
@Setter
@Getter
@With
public class MappingId implements Serializable {

    /**
     * The Course id.
     */
    Long courseId;
    /**
     * The Assignment id.
     */
    Long assignmentId;

    /**
     * Instantiates a new Mapping id.
     *
     * @param courseId     the course id
     * @param assignmentId the assignment id
     */
    public MappingId(Long courseId, Long assignmentId) {
        this.courseId = courseId;
        this.assignmentId = assignmentId;
    }
}