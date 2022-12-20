package s.praveen.cms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import s.praveen.cms.model.dto.CourseRegistration;

import java.util.List;

/**
 * The interface Course registration repository.
 */
public interface ICourseRegistrationRepository extends JpaRepository<CourseRegistration, Long> {
    /**
     * Find by course id list.
     *
     * @param courseId the course id
     * @return the list
     */
//    @Query(value= "SELECT s FROM CourseRegistration s " +
//            "WHERE s.id.course_id = :courseId")
    List<CourseRegistration> findByCourseId(long courseId);

    /**
     * Find by student id list.
     *
     * @param studentId the student id
     * @return the list
     */
//    @Query(value= "SELECT s FROM CourseRegistration s " +
//            "WHERE s.id.student_id = :studentId")
    List<CourseRegistration> findByStudentId(long studentId);
}
