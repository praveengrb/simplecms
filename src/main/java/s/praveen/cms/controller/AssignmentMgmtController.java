package s.praveen.cms.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import s.praveen.cms.common.Path;
import s.praveen.cms.exception.EnityNotFoundException;
import s.praveen.cms.exception.UnableToProcessException;
import s.praveen.cms.model.dto.MappingTable;
import s.praveen.cms.model.ro.AssignmentRequest;
import s.praveen.cms.model.ro.SubmitAssignmentRequest;
import s.praveen.cms.service.IAssignmentMgmtService;

/**
 * The type Assignment mgmt controller.
 */
@RestController
@RequestMapping(Path.ASSIGNMENT)
public class AssignmentMgmtController {
    private static final Logger LOG = LoggerFactory.getLogger(AssignmentMgmtController.class.getName());
    /**
     * The Assignment mgmt service.
     */
    @Autowired
    IAssignmentMgmtService assignmentMgmtService;

    /**
     * Create assignment response entity.
     *
     * @param request the request
     * @return the response entity
     */
// This method is need for Instructor
    @PostMapping
    public @ResponseBody ResponseEntity<Object> createAssignment(@RequestBody AssignmentRequest request) {
        LOG.info("Create Request");
        try {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(assignmentMgmtService.addAssignment(request));
        } catch (EnityNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }

    }

    /**
     * Submit assignment response entity.
     *
     * @param request the request
     * @return the response entity
     */
// Submission of Assignment
    @PutMapping("/submission")
    public @ResponseBody ResponseEntity<Object> submitAssignment(@RequestBody SubmitAssignmentRequest request) {
        LOG.info("Update Request");
        try {
            MappingTable assignmentResponse = assignmentMgmtService
                    .submitAssignment(request);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(assignmentResponse);
        } catch (EnityNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }

    }

    /**
     * Update request response entity.
     *
     * @param id      the id
     * @param request the request
     * @return the response entity
     */
    @PutMapping(Path.ID)
    public @ResponseBody ResponseEntity<Object> updateRequest(@PathVariable(Path.ID_PATH_VARIABLE) long id, @RequestBody AssignmentRequest request) {
        LOG.info("Update Request");
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(assignmentMgmtService.updateAssignmentById(id, request));
        } catch (EnityNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }

    }

    /**
     * Delete assignment response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @DeleteMapping(Path.ID)
    public @ResponseBody ResponseEntity<Object> deleteAssignment(@PathVariable(Path.ID_PATH_VARIABLE) long id) {
        LOG.info("Delete Request");

        try {
            if (assignmentMgmtService.deleteAssignment(id)) {
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body("Success");
            }
        } catch (EnityNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        } catch (UnableToProcessException e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
        return ResponseEntity
                .status(HttpStatus.NOT_MODIFIED)
                .body("Unable to delete");
    }

    /**
     * Gets assignment by id.
     *
     * @param id the id
     * @return the assignment by id
     */
    @GetMapping(Path.ID)
    public @ResponseBody ResponseEntity<Object> getAssignmentById(@PathVariable(Path.ID_PATH_VARIABLE) long id) {
        LOG.info("Select Request By Id");
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(assignmentMgmtService.getAssignmentById(id));
        } catch (EnityNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }

    }

    /**
     * Gets assignments.
     *
     * @return the assignments
     */
    @GetMapping
    public @ResponseBody ResponseEntity<Object> getAssignments() {
        LOG.info("Select All Request");
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(assignmentMgmtService.getAssignments());
        } catch (UnableToProcessException e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }

    }

    /**
     * Gets assignment by student id.
     *
     * @param studentId the student id
     * @return the assignment by student id
     */
    @GetMapping("/student")
    public @ResponseBody ResponseEntity<Object> getAssignmentByStudentId(@RequestParam("student_id") long studentId) {
        LOG.info("Select Request By Id");
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(assignmentMgmtService.getAssignmentByStudentId(studentId));
        } catch (UnableToProcessException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }
}
