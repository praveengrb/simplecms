package s.praveen.cms.service;


import s.praveen.cms.exception.EnityNotFoundException;
import s.praveen.cms.exception.UnableToProcessException;
import s.praveen.cms.model.dto.Assignment;
import s.praveen.cms.model.dto.MappingTable;
import s.praveen.cms.model.ro.AssignmentRequest;
import s.praveen.cms.model.ro.SubmitAssignmentRequest;

import java.util.List;

/**
 * The interface Assignment mgmt service.
 */
public interface IAssignmentMgmtService extends IManagementService {

    /**
     * Gets assignments.
     *
     * @return the assignments
     * @throws UnableToProcessException the unable to process exception
     */
    List<Assignment> getAssignments() throws UnableToProcessException;

    /**
     * Add assignment assignment.
     *
     * @param request the request
     * @return the assignment
     * @throws EnityNotFoundException the enity not found exception
     */
    Assignment addAssignment(AssignmentRequest request) throws EnityNotFoundException;

    /**
     * Gets assignment by id.
     *
     * @param id the id
     * @return the assignment by id
     * @throws EnityNotFoundException the enity not found exception
     */
    Assignment getAssignmentById(long id) throws EnityNotFoundException;

    /**
     * Update assignment by id assignment.
     *
     * @param id      the id
     * @param request the request
     * @return the assignment
     * @throws EnityNotFoundException the enity not found exception
     */
    Assignment updateAssignmentById(long id, AssignmentRequest request) throws EnityNotFoundException;

    /**
     * Delete assignment boolean.
     *
     * @param id the id
     * @return the boolean
     * @throws EnityNotFoundException   the enity not found exception
     * @throws UnableToProcessException the unable to process exception
     */
    boolean deleteAssignment(long id) throws EnityNotFoundException, UnableToProcessException;

    /**
     * Submit assignment mapping table.
     *
     * @param request the request
     * @return the mapping table
     * @throws EnityNotFoundException the enity not found exception
     */
    MappingTable submitAssignment(SubmitAssignmentRequest request) throws EnityNotFoundException;

    /**
     * Gets assignment by student id.
     *
     * @param studentId the student id
     * @return the assignment by student id
     * @throws UnableToProcessException the unable to process exception
     */
    List<Assignment> getAssignmentByStudentId(long studentId) throws UnableToProcessException;
}
