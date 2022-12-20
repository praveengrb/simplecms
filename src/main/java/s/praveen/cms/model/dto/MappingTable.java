package s.praveen.cms.model.dto;

import jakarta.persistence.*;
import lombok.*;

/**
 * The type Mapping table.
 */
@Entity
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
@With
@Table(name = "MappingTable")

public class MappingTable {
    /**
     * The Id.
     */
    @EmbeddedId
    MappingId id;
    /**
     * The Student id.
     */
    long studentId;
    /**
     * The Status.
     */
    String status;
    /**
     * The Course.
     */
    @ManyToOne
    @MapsId("courseId")
    @JoinColumn(name = "course_id")
    Course course;
    /**
     * The Assignment.
     */
    @ManyToOne
    @MapsId("assignmentId")
    @JoinColumn(name = "assignment_id")
    Assignment assignment;
}
