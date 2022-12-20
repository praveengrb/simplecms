package s.praveen.cms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import s.praveen.cms.model.dto.Student;

/**
 * The interface Student repository.
 */
@Repository("studentRepository")
public interface IStudentRepository extends JpaRepository<Student, Long> {

}
