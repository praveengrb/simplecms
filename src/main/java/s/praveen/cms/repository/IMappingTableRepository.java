package s.praveen.cms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import s.praveen.cms.model.dto.MappingTable;

import java.util.List;

/**
 * The interface Mapping table repository.
 */
public interface IMappingTableRepository extends JpaRepository<MappingTable, Long> {

    /**
     * Find by course id list.
     *
     * @param courseId the course id
     * @return the list
     */
    List<MappingTable> findByCourseId(long courseId);

    /**
     * Find by student id list.
     *
     * @param studentId the student id
     * @return the list
     */
    List<MappingTable> findByStudentId(long studentId);

    /**
     * Find by assignment id list.
     *
     * @param assignmentId the assignment id
     * @return the list
     */
    List<MappingTable> findByAssignmentId(long assignmentId);
}
