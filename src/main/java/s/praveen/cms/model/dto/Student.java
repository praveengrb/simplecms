package s.praveen.cms.model.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import s.praveen.cms.model.ro.StudentRequest;

import java.io.Serializable;

/**
 * The type Student.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@With
@Table(name = "students")
public class Student implements Serializable {
    /**
     * The Id.
     */
    @Id
    @Column(name = "student_id")
    long id;
    /**
     * The Student name.
     */
    @Column(name = "student_name")
    String studentName;

    /**
     * Instantiates a new Student.
     *
     * @param request the request
     */
    public Student(StudentRequest request) {
        this.setId(System.nanoTime());
        this.setStudentName(request.studentName());
    }
}
