package s.praveen.cms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import s.praveen.cms.model.dto.MappingTable;
import s.praveen.cms.repository.IMappingTableRepository;

import java.util.List;

/**
 * The type Mapping table service.
 */
@Service
public class MappingTableServiceImpl {
    /**
     * The Mapping table repository.
     */
    @Autowired
    IMappingTableRepository mappingTableRepository;

    /**
     * Submit assignment mapping table.
     *
     * @param mappingTable the mapping table
     * @return the mapping table
     */
    public MappingTable submitAssignment(MappingTable mappingTable) {
        return mappingTableRepository.save(mappingTable);
    }

    /**
     * Gets assignment by student id.
     *
     * @param studentId the student id
     * @return the assignment by student id
     */
    public List<Long> getAssignmentByStudentId(long studentId) {
        List<Long> studentIds = mappingTableRepository
                .findByStudentId(studentId)
                .stream()
                .map(MappingTable::getStudentId)
                .toList();
        return studentIds;
    }
}