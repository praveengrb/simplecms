package s.praveen.cms.service;


import s.praveen.cms.exception.EnityNotFoundException;
import s.praveen.cms.exception.UnableToProcessException;
import s.praveen.cms.model.dto.Course;
import s.praveen.cms.model.dto.CourseRegistration;
import s.praveen.cms.model.ro.CourseRequest;

import java.util.List;

/**
 * The interface Course mgmt service.
 */
public interface ICourseMgmtService extends IManagementService {

    /**
     * Gets courses.
     *
     * @return the courses
     * @throws UnableToProcessException the unable to process exception
     */
    List<Course> getCourses() throws UnableToProcessException;

    /**
     * Add course course.
     *
     * @param request the request
     * @return the course
     * @throws UnableToProcessException the unable to process exception
     */
    Course addCourse(CourseRequest request) throws UnableToProcessException;

    /**
     * Gets course by id.
     *
     * @param id the id
     * @return the course by id
     * @throws EnityNotFoundException the enity not found exception
     */
    Course getCourseById(long id) throws EnityNotFoundException;

    /**
     * Update course by id course.
     *
     * @param id      the id
     * @param request the request
     * @return the course
     * @throws EnityNotFoundException the enity not found exception
     */
    Course updateCourseById(long id, CourseRequest request) throws EnityNotFoundException;

    /**
     * Delete course by id boolean.
     *
     * @param id the id
     * @return the boolean
     * @throws EnityNotFoundException   the enity not found exception
     * @throws UnableToProcessException the unable to process exception
     */
    boolean deleteCourseById(long id) throws EnityNotFoundException, UnableToProcessException;

    /**
     * Register course course registration.
     *
     * @param course_id  the course id
     * @param student_id the student id
     * @return the course registration
     * @throws EnityNotFoundException the enity not found exception
     */
    CourseRegistration registerCourse(long course_id, long student_id) throws EnityNotFoundException;

    /**
     * Gets courses by student id.
     *
     * @param studentId the student id
     * @return the courses by student id
     */
    List<Course> getCoursesByStudentId(long studentId);
}
