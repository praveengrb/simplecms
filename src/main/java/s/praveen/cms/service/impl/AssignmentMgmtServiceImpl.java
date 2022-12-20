package s.praveen.cms.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import s.praveen.cms.common.ServiceType;
import s.praveen.cms.exception.EnityNotFoundException;
import s.praveen.cms.exception.UnableToProcessException;
import s.praveen.cms.model.dto.Assignment;
import s.praveen.cms.model.dto.Course;
import s.praveen.cms.model.dto.MappingId;
import s.praveen.cms.model.dto.MappingTable;
import s.praveen.cms.model.ro.AssignmentRequest;
import s.praveen.cms.model.ro.SubmitAssignmentRequest;
import s.praveen.cms.repository.IAssignmentRepository;
import s.praveen.cms.service.IAssignmentMgmtService;
import s.praveen.cms.service.ICourseMgmtService;

import java.util.List;

/**
 * The type Assignment mgmt service.
 */
@Service(ServiceType.ASSIGNMENT)
public class AssignmentMgmtServiceImpl implements IAssignmentMgmtService {
    private static final Logger LOG = LoggerFactory.getLogger(AssignmentMgmtServiceImpl.class.getName());
    /**
     * The Assignment repository.
     */
    @Autowired
    IAssignmentRepository assignmentRepository;
    /**
     * The Mapping table service.
     */
    @Autowired
    MappingTableServiceImpl mappingTableService;
    /**
     * The Course mgmt service.
     */
    @Autowired
    ICourseMgmtService iCourseMgmtService;

    @Override
    public List<Assignment> getAssignments() throws UnableToProcessException {
        try {
            return assignmentRepository.findAll();
        } catch (Exception e) {
            throw new UnableToProcessException(e.getMessage());
        }
    }

    @Override
    public Assignment addAssignment(AssignmentRequest request) throws EnityNotFoundException {
        Course course = null;
        Assignment assignment = null;
        try {
            course = iCourseMgmtService.getCourseById(request.courseId());
            LOG.info("Adding assignment. Course Identified for the given assignment request");
            assignment = assignmentRepository.save(new Assignment(request));
            LOG.info("Assignment saved. Mapping the assignment in progress.");
            mappingTableService.submitAssignment(MappingTable.builder()
                    .assignment(assignment)
                    .id(new MappingId(request.courseId(), assignment.getId()))
                    .course(course)
                    .status("CREATED").build());
            LOG.info("Mapping of course and assignment created.");
            return assignment;
        } catch (EnityNotFoundException e) {
            LOG.error("Requested course is not available");
            throw new EnityNotFoundException("Requested course is not found");
        }

    }

    @Override
    public Assignment getAssignmentById(long id) throws EnityNotFoundException {
        LOG.info("Getting the the Assignment information {}", id);
        return assignmentRepository
                .findById(id)
                .orElseThrow(EnityNotFoundException::new);
    }

    @Override
    public Assignment updateAssignmentById(long id, AssignmentRequest request) throws EnityNotFoundException {
        Assignment assignment = getAssignmentById(id);
        LOG.info("Updating the the Assignment information {}", id);
        assignment
                .setAssignmentName(request.assignmentName());
        return assignmentRepository.save(
                assignment);
    }

    @Override
    public boolean deleteAssignment(long id) throws EnityNotFoundException, UnableToProcessException {
        Assignment assignmentData = getAssignmentById(id);
        LOG.info("Delete the the Assignment information {}", id);
        try {
            assignmentRepository.delete(assignmentData);
            LOG.info("Assignment deleted");
            return true;
        } catch (Exception e) {
            LOG.error("Unable to delete the assignment {}", e.getMessage());
            throw new UnableToProcessException(e.getMessage());
        }

    }

    @Override
    public MappingTable submitAssignment(SubmitAssignmentRequest request) throws EnityNotFoundException {
        LOG.info("Mapping of course, assignment, course id");
        try {
            LOG.info("Mapping of course and assignment is in progress.");
            return mappingTableService.submitAssignment(MappingTable.builder()
                    .id(new MappingId(request.courseId(), request.assignmentId()))
                    .course(iCourseMgmtService.getCourseById(request.courseId()))
                    .assignment(getAssignmentById(request.assignmentId()))
                    .studentId(request.studentId())
                    .status("COMPLETED").build());
        } catch (EnityNotFoundException e) {
            LOG.error("Requested course is not available");
            throw new EnityNotFoundException("Requested course is not found");
        }
    }

    @Override
    public List<Assignment> getAssignmentByStudentId(long studentId) {
        return assignmentRepository.findAllById(mappingTableService.getAssignmentByStudentId(studentId));
    }
}