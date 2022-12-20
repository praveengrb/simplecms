package s.praveen.cms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import s.praveen.cms.model.dto.Assignment;

/**
 * The interface Assignment repository.
 */
@Repository("assignmentRepository")
public interface IAssignmentRepository extends JpaRepository<Assignment, Long> {
}
