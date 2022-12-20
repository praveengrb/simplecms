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
import s.praveen.cms.model.dto.Course;
import s.praveen.cms.model.ro.CourseRequest;
import s.praveen.cms.model.ro.GenericResponse;
import s.praveen.cms.service.ICourseMgmtService;

import java.util.List;

/**
 * The type Course mgmt controller.
 */
@RestController
@RequestMapping(Path.COURSE)
public class CourseMgmtController {
    private static final Logger LOG = LoggerFactory.getLogger(CourseMgmtController.class.getName());
    /**
     * The Course mgmt service.
     */
    @Autowired
    ICourseMgmtService iCourseMgmtService;

    /**
     * Create course response entity.
     *
     * @param request the request
     * @return the response entity
     */
    @PostMapping
    public @ResponseBody ResponseEntity<Object> createCourse(@RequestBody CourseRequest request) {
        LOG.info("Create Request");
        try {
            return ResponseEntity.ok(iCourseMgmtService.addCourse(request));
        } catch (UnableToProcessException e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    /**
     * Update course by id response entity.
     *
     * @param id      the id
     * @param request the request
     * @return the response entity
     */
    @PutMapping(Path.ID)
    public @ResponseBody ResponseEntity<Object> updateCourseById(@PathVariable(Path.ID_PATH_VARIABLE) long id, @RequestBody CourseRequest request) {
        LOG.info("Update Request");
        try {
            return ResponseEntity.ok(iCourseMgmtService.updateCourseById(id, request));
        } catch (EnityNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }

    }

    /**
     * Delete course by id response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @DeleteMapping(Path.ID)

    public @ResponseBody ResponseEntity deleteCourseById(@PathVariable(Path.ID_PATH_VARIABLE) long id) {
        LOG.info("Delete Request");

        try {
            return ResponseEntity.ok(iCourseMgmtService.deleteCourseById(id));
        } catch (EnityNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        } catch (UnableToProcessException e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }

    }

    /**
     * Gets course by id.
     *
     * @param id the id
     * @return the course by id
     */
    @GetMapping(Path.ID)
    public @ResponseBody ResponseEntity<Object> getCourseById(@PathVariable(Path.ID_PATH_VARIABLE) long id) {
        LOG.info("Select Request By Id");
        GenericResponse<Course> courseResponse = null;
        try {
            return ResponseEntity.ok(iCourseMgmtService.getCourseById(id));
        } catch (EnityNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    /**
     * Gets all courses.
     *
     * @return the all courses
     */
    @GetMapping
    public @ResponseBody ResponseEntity<Object> getAllCourses() {
        LOG.info("Select All Request");
        try {
            return ResponseEntity.ok(iCourseMgmtService.getCourses());
        } catch (UnableToProcessException e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    /**
     * Gets courses by student id.
     *
     * @param studentId the student id
     * @return the courses by student id
     */
    @GetMapping("/student")
    public @ResponseBody ResponseEntity<List<Course>> getCoursesByStudentId(@RequestParam("student_id") long studentId) {
        return ResponseEntity.ok(iCourseMgmtService.getCoursesByStudentId(studentId));
    }

    /**
     * Register course response entity.
     *
     * @param course_id  the course id
     * @param student_id the student id
     * @return the response entity
     */
    @GetMapping("/register")
    public @ResponseBody ResponseEntity<Object> registerCourse(@RequestParam("course_id") long course_id,
                                                               @RequestParam("student_id") long student_id) {
        try {
            return ResponseEntity.ok(iCourseMgmtService.registerCourse(course_id, student_id));
        } catch (EnityNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }
}
