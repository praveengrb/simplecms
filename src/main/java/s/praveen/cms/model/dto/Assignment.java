package s.praveen.cms.model.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import s.praveen.cms.model.ro.AssignmentRequest;

/**
 * The type Assignment.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "assignments")
@With
public class Assignment {
    /**
     * The Id.
     */
    @Id
    @Column(name = "assignment_id")
    long id;
    /**
     * The Assignment name.
     */
    @Column(name = "assignment_name")
    String assignmentName;

    /**
     * Instantiates a new Assignment.
     *
     * @param request the request
     */
    public Assignment(AssignmentRequest request) {
        this.setId(System.nanoTime());
        this.setAssignmentName(request.assignmentName());
    }
}
