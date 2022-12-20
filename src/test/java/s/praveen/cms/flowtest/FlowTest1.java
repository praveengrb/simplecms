package s.praveen.cms.flowtest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import s.praveen.cms.exception.EnityNotFoundException;
import s.praveen.cms.exception.UnableToProcessException;
import s.praveen.cms.model.dto.*;
import s.praveen.cms.model.ro.AssignmentRequest;
import s.praveen.cms.model.ro.CourseRequest;
import s.praveen.cms.model.ro.StudentRequest;
import s.praveen.cms.model.ro.SubmitAssignmentRequest;
import s.praveen.cms.service.IAssignmentMgmtService;
import s.praveen.cms.service.ICourseMgmtService;
import s.praveen.cms.service.IStudentMgmtService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Flow test 1.
 */
@SpringBootTest
public class FlowTest1 {
    /**
     * The Student mgmt service.
     */
    @Autowired
    IStudentMgmtService studentMgmtService;
    /**
     * The Course mgmt service.
     */
    @Autowired
    ICourseMgmtService courseMgmtService;
    /**
     * The Assignment mgmt service.
     */
    @Autowired
    IAssignmentMgmtService assignmentMgmtService;

    /**
     * Positive flow 1.
     *
     * @throws UnableToProcessException the unable to process exception
     * @throws EnityNotFoundException   the enity not found exception
     */
    @Test
    public void positiveFlow1() throws UnableToProcessException, EnityNotFoundException {
        /*
            create a course
            create a student
            map student and course
            then verify if course id and student id is correctly getting mapped
         */
        CourseRequest courseRequest = new CourseRequest("course1");
        StudentRequest studentRequest = new StudentRequest("student1");
        Course course = courseMgmtService.addCourse(courseRequest);
        assertNotNull(course);
        Student student = studentMgmtService.addStudent(studentRequest);
        assertNotNull(student);
        CourseRegistration courseRegistration = courseMgmtService.registerCourse(course.getId(), student.getId());
        assertNotNull(courseRegistration);
        assertEquals(course.getId(), courseRegistration.getId().getCourseId());
        assertEquals(student.getId(), courseRegistration.getId().getStudentId());
    }

    /**
     * Positive flow 2.
     *
     * @throws UnableToProcessException the unable to process exception
     * @throws EnityNotFoundException   the enity not found exception
     */
    @Test
    public void positiveFlow2() throws UnableToProcessException, EnityNotFoundException {
        /*
            create a course
            create a student
            map student and course
            then verify if course id and student id is correctly getting mapped
            create assignment
            map assignment and course
         */
        CourseRequest courseRequest = new CourseRequest("course2");
        StudentRequest studentRequest = new StudentRequest("student2");
        Course course = courseMgmtService.addCourse(courseRequest);
        assertNotNull(course);
        Student student = studentMgmtService.addStudent(studentRequest);
        assertNotNull(student);
        CourseRegistration courseRegistration = courseMgmtService.registerCourse(course.getId(), student.getId());
        assertNotNull(courseRegistration);
        assertEquals(course.getId(), courseRegistration.getId().getCourseId());
        assertEquals(student.getId(), courseRegistration.getId().getStudentId());
        AssignmentRequest assignmentRequest = new AssignmentRequest("assignment for course 2", course.getId());
        Assignment assignment = assignmentMgmtService.addAssignment(assignmentRequest);
        assertNotNull(assignment);
        SubmitAssignmentRequest request = new SubmitAssignmentRequest(student.getId(), course.getId(), assignment.getId());
        MappingTable assignment1 = assignmentMgmtService.submitAssignment(request);
        assertNotNull(assignment1);
        List<Assignment> assignmentResult = assignmentMgmtService.getAssignmentByStudentId(student.getId());
        assertTrue(assignmentResult.size() > 0);
    }

    /**
     * Positive flow 3.
     *
     * @throws UnableToProcessException the unable to process exception
     * @throws EnityNotFoundException   the enity not found exception
     */
    @Test
    public void positiveFlow3() throws UnableToProcessException, EnityNotFoundException {
        /*
            Add 100 Courses to a student. and see how it is getting mapped.
         */
        List<Course> courseRequests = new ArrayList();
        int size = 100;
        StudentRequest studentRequest = new StudentRequest("student2");
        Student student = studentMgmtService.addStudent(studentRequest);
        assertNotNull(student);
        for (int i = 0; i < size; i++) {
            Course course = courseMgmtService.addCourse(new CourseRequest("course" + Math.random()));
            assertNotNull(course);
            courseRequests.add(course);
            CourseRegistration courseRegistration = courseMgmtService.registerCourse(course.getId(), student.getId());
            assertNotNull(courseRegistration);
        }
        assertEquals(courseMgmtService.getCoursesByStudentId(student.getId()).size(), size);

    }
}
