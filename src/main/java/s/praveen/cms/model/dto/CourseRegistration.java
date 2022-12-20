package s.praveen.cms.model.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The type Course registration.
 */
@Entity
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Table(name = "CourseRegistration")

public class CourseRegistration {
    /**
     * The Id.
     */
    @EmbeddedId
    CSId id;
    /**
     * The Student.
     */
    @ManyToOne
    @MapsId("studentId")
    @JoinColumn(name = "student_id")
    Student student;

    /**
     * The Course.
     */
    @ManyToOne
    @MapsId("courseId")
    @JoinColumn(name = "course_id")
    Course course;
}
