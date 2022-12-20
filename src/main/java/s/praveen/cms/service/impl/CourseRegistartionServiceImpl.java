package s.praveen.cms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import s.praveen.cms.model.dto.CSId;
import s.praveen.cms.model.dto.Course;
import s.praveen.cms.model.dto.CourseRegistration;
import s.praveen.cms.model.dto.Student;
import s.praveen.cms.repository.ICourseRegistrationRepository;
import s.praveen.cms.repository.ICourseRepository;

import java.util.List;

/**
 * The type Course registartion service.
 */
@Service
public class CourseRegistartionServiceImpl {
    /**
     * The Course repository.
     */
    @Autowired
    ICourseRepository courseRepository;


    /**
     * The Course registration repository.
     */
    @Autowired
    ICourseRegistrationRepository courseRegistrationRepository;


    /**
     * Register course course registration.
     *
     * @param id      the id
     * @param course  the course
     * @param student the student
     * @return the course registration
     */
    public CourseRegistration registerCourse(CSId id, Course course, Student student) {
        return
                courseRegistrationRepository.save(
                        new CourseRegistration(
                                id,
                                student,
                                course
                        )
                );
    }

    /**
     * Gets student ids by course id.
     *
     * @param courseId the course id
     * @return the student ids by course id
     */
    public List<Long> getStudentIdsByCourseId(long courseId) {
        return courseRegistrationRepository
                .findByCourseId(courseId)
                .stream()
                .map(CourseRegistration::getId)
                .map(CSId::getCourseId)
                .toList();
    }

    /**
     * Gets course ids by student id.
     *
     * @param studentId the student id
     * @return the course ids by student id
     */
    public List<Long> getCourseIdsByStudentId(long studentId) {
        return courseRegistrationRepository
                .findByStudentId(studentId)
                .stream()
                .map(CourseRegistration::getId)
                .map(CSId::getCourseId)
                .toList();
    }
}