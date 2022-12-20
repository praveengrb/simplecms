package s.praveen.cms.model.dto;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

/**
 * The type Cs id.
 */
@Embeddable
@NoArgsConstructor
@Setter
@Getter
@ToString
@With
@Builder
public class CSId implements Serializable {
    /**
     * The Course id.
     */
    Long courseId;
    /**
     * The Student id.
     */
    Long studentId;

    /**
     * Instantiates a new Cs id.
     *
     * @param studentId the student id
     * @param courseId  the course id
     */
    public CSId(Long studentId, Long courseId) {
        this.studentId = studentId;
        this.courseId = courseId;
    }
}