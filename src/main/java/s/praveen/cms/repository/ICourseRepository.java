package s.praveen.cms.repository;

import org.springframework.stereotype.Repository;
import s.praveen.cms.model.dto.Course;

/**
 * The interface Course repository.
 */
@Repository("courseRepository")
public interface ICourseRepository extends org.springframework.data.jpa.repository.JpaRepository<Course, Long> {

}
