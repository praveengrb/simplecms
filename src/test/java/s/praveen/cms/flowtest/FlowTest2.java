package s.praveen.cms.flowtest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import s.praveen.cms.controller.AssignmentMgmtController;
import s.praveen.cms.controller.CourseMgmtController;
import s.praveen.cms.controller.StudentMgmtController;
import s.praveen.cms.controller.UserMgmtController;
import s.praveen.cms.model.dto.Assignment;
import s.praveen.cms.model.dto.Course;
import s.praveen.cms.model.dto.Student;
import s.praveen.cms.model.ro.AssignmentRequest;
import s.praveen.cms.model.ro.CourseRequest;
import s.praveen.cms.model.ro.StudentRequest;
import s.praveen.cms.model.ro.SubmitAssignmentRequest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Flow test 2.
 */
@SpringBootTest
public class FlowTest2 {
    /**
     * The Student mgmt controller.
     */
    @Autowired
    StudentMgmtController studentMgmtController;
    /**
     * The Assignment mgmt controller.
     */
    @Autowired
    AssignmentMgmtController assignmentMgmtController;
    /**
     * The Course mgmt controller.
     */
    @Autowired
    CourseMgmtController courseMgmtController;
    /**
     * The User mgmt controller.
     */
    @Autowired
    UserMgmtController userMgmtController;

    /**
     * Positive test case 1.
     */
    @Test
    public void positiveTestCase1() {
        StudentRequest studentRequest = new StudentRequest("testcaseStudent");
        CourseRequest courseRequest = new CourseRequest("Social Science");

        ResponseEntity<Object> response = studentMgmtController.addStudent(studentRequest);
        assertEquals(200, response.getStatusCode().value());
        Student student = (Student) response.getBody();
        assertTrue(((List) studentMgmtController.getStudents().getBody()).size() > 0);
        assertNotNull(studentMgmtController.getStudentById(student.getId()).getBody());
        studentMgmtController.updateStudentById(student.getId(), studentRequest);
        ResponseEntity<Object> courseResponse = courseMgmtController.createCourse(courseRequest);
        Course course = (Course) courseResponse.getBody();
        courseMgmtController.registerCourse(course.getId(), student.getId());
        assertTrue(
                courseMgmtController.getCoursesByStudentId(student.getId()).getBody().size() > 0
        );
        courseMgmtController.updateCourseById(course.getId(), courseRequest);
        //assertTrue(((List)studentMgmtController.getStudentsByCourseId(course.getId()).getBody()).size()>0);
        AssignmentRequest assignmentRequest = new AssignmentRequest("SocialTest1", course.getId());
        Assignment assignment = (Assignment) assignmentMgmtController.createAssignment(assignmentRequest).getBody();
        assignmentMgmtController.getAssignmentByStudentId(student.getId());

        assertTrue(
                ((List) assignmentMgmtController.getAssignments().getBody()).size() > 0
        );
        assignmentMgmtController.submitAssignment(new SubmitAssignmentRequest(student.getId()
                , course.getId(),
                assignment.getId()));
        //assertEquals(assignment.getId(),(assignmentMgmtController.getAssignmentById(assignment.getId()).getBody()));
        assignmentMgmtController.deleteAssignment(assignment.getId());
        courseMgmtController.deleteCourseById(course.getId());
        studentMgmtController.deleteStudentById(student.getId());
    }
}
