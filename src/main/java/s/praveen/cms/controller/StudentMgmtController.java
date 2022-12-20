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
import s.praveen.cms.model.dto.Student;
import s.praveen.cms.model.ro.GenericResponse;
import s.praveen.cms.model.ro.StudentRequest;
import s.praveen.cms.service.IStudentMgmtService;


/**
 * The type Student mgmt controller.
 */
@RestController
@RequestMapping(Path.STUDENT)
public class StudentMgmtController {
    private static final Logger LOG = LoggerFactory.getLogger(StudentMgmtController.class.getName());
    /**
     * The Student mgmt service.
     */
    @Autowired
    IStudentMgmtService studentMgmtService;


    /**
     * Add student response entity.
     *
     * @param request the request
     * @return the response entity
     */
    @PostMapping

    public @ResponseBody ResponseEntity<Object> addStudent(@RequestBody StudentRequest request) {
        LOG.info("Create Request");
        try {
            return ResponseEntity.ok(studentMgmtService.addStudent(request));
        } catch (UnableToProcessException e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    /**
     * Update student by id response entity.
     *
     * @param id      the id
     * @param request the request
     * @return the response entity
     */
    @PutMapping(Path.ID)

    public @ResponseBody ResponseEntity<Object> updateStudentById(@PathVariable(Path.ID_PATH_VARIABLE) long id, @RequestBody StudentRequest request) {
        LOG.info("Update Request");

        try {
            return ResponseEntity.ok(studentMgmtService.updateStudent(id, request));
        } catch (EnityNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    /**
     * Delete student by id response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @DeleteMapping(Path.ID)

    public @ResponseBody ResponseEntity<Object> deleteStudentById(@PathVariable(Path.ID_PATH_VARIABLE) long id) {
        LOG.info("Delete Request");
        GenericResponse<Student> studentResponse = null;
        try {
            if (studentMgmtService.deleteStudent(id)) {
                return ResponseEntity.ok("Student deleted successfully");
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
                .body("Nothing to delete");
    }

    /**
     * Gets student by id.
     *
     * @param id the id
     * @return the student by id
     */
    @GetMapping(Path.ID)
    public @ResponseBody ResponseEntity<Object> getStudentById(@PathVariable(Path.ID_PATH_VARIABLE) long id) {
        LOG.info("Select Request By Id");
        try {
            return ResponseEntity.ok(studentMgmtService.selectStudentById(id));
        } catch (EnityNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    /**
     * Gets students.
     *
     * @return the students
     */
    @GetMapping
    public @ResponseBody ResponseEntity<Object> getStudents() {
        try {
            return ResponseEntity.ok(studentMgmtService.getStudents());
        } catch (UnableToProcessException e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    /**
     * This should be accessible only to the
     *
     * @param courseId passed CourseID
     * @return the students by course id
     */
    @GetMapping("/course_id/"+Path.COURSE_ID_PATH_VARIABLE)
    public @ResponseBody ResponseEntity getStudentsByCourseId(@PathVariable(Path.COURSE_ID_PATH_VARIABLE) long courseId) {
        return ResponseEntity.ok(studentMgmtService.getStudentsByCourseId(courseId));
    }
}
