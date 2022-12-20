package s.praveen.cms.model.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import s.praveen.cms.model.ro.CourseRequest;

/**
 * The type Course.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@With
@Table(name = "courses")
public class Course {
    /**
     * The Id.
     */
    @Id
    @Column(name = "course_id")
    long id;
    /**
     * The Course name.
     */
    @Column(name = "course_name")
    String courseName;

    /**
     * Instantiates a new Course.
     *
     * @param courseRequest the course request
     */
    public Course(CourseRequest courseRequest) {
        this.setId(System.nanoTime());
        this.setCourseName(courseRequest.courseName());
    }
}
