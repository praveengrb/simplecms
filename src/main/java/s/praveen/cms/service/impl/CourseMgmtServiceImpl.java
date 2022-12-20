package s.praveen.cms.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import s.praveen.cms.common.ServiceType;
import s.praveen.cms.exception.EnityNotFoundException;
import s.praveen.cms.exception.UnableToProcessException;
import s.praveen.cms.model.dto.CSId;
import s.praveen.cms.model.dto.Course;
import s.praveen.cms.model.dto.CourseRegistration;
import s.praveen.cms.model.dto.Student;
import s.praveen.cms.model.ro.CourseRequest;
import s.praveen.cms.repository.ICourseRepository;
import s.praveen.cms.service.ICourseMgmtService;
import s.praveen.cms.service.IStudentMgmtService;

import java.util.List;


/**
 * The type Course mgmt service.
 */
@Service(ServiceType.COURSE)

public class CourseMgmtServiceImpl implements ICourseMgmtService {
    private static final Logger LOG = LoggerFactory.getLogger(CourseMgmtServiceImpl.class.getName());
    /**
     * The Course repository.
     */
    @Autowired
    ICourseRepository courseRepository;
    /**
     * The Course registartion service.
     */
    @Autowired
    CourseRegistartionServiceImpl courseRegistartionService;
    /**
     * The Student mgmt service.
     */
    @Autowired
    IStudentMgmtService studentMgmtService;

    @Override
    public List<Course> getCourses() throws UnableToProcessException {
        LOG.info("Get all courses");
        try {
            LOG.info("Courses list created");
            return courseRepository.findAll();
        } catch (Exception e) {
            LOG.error("Unable to get the courses {}", e.getMessage());
            throw new UnableToProcessException(e.getMessage());
        }
    }

    @Override
    public Course addCourse(CourseRequest request) throws UnableToProcessException {
        try {
            LOG.info("Course will be added");
            return courseRepository.save(new Course(request));
        } catch (Exception e) {
            LOG.error("Unable to create the courses {}", e.getMessage());
            throw new UnableToProcessException(e.getMessage());
        }
    }

    @Override
    public Course getCourseById(long id) throws EnityNotFoundException {
        LOG.info("Getting the course for given id {}", id);
        return courseRepository
                .findById(id)
                .orElseThrow(EnityNotFoundException::new);

    }

    @Override
    public Course updateCourseById(long id, CourseRequest request) throws EnityNotFoundException {
        Course course = getCourseById(id);
        course.withCourseName(request.courseName());
        LOG.info("Updating the course for given id {}", id);
        return courseRepository.save(course);
    }

    @Override
    public boolean deleteCourseById(long id) throws UnableToProcessException {
        LOG.info("Deleting the course for given id {}", id);
        try {
            courseRepository.delete(getCourseById(id));
            LOG.info("Deleted the course for given id {}", id);
            return true;

        } catch (Exception e) {
            LOG.info("Unable to delete the course {}", e.getMessage());
            throw new UnableToProcessException(e.getMessage());
        }
    }

    @Override
    public CourseRegistration registerCourse(long courseId, long studentId) throws EnityNotFoundException {
        Course course = getCourseById(courseId);
        Student student = studentMgmtService.selectStudentById(studentId);
        LOG.info("Mapping the given course id {} with the student {}", courseId, studentId);
        return courseRegistartionService.registerCourse(
                CSId
                        .builder()
                        .courseId(courseId)
                        .studentId(studentId).build(),
                course,
                student
        );
    }

    @Override
    public List<Course> getCoursesByStudentId(long studentId) {
        LOG.info("Get courses by student id {}", studentId);
        return courseRepository.findAllById(
                courseRegistartionService.getCourseIdsByStudentId(studentId)
        );
    }
}