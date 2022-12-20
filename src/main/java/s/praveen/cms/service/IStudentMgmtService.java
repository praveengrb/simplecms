package s.praveen.cms.service;


import s.praveen.cms.exception.EnityNotFoundException;
import s.praveen.cms.exception.UnableToProcessException;
import s.praveen.cms.model.dto.Student;
import s.praveen.cms.model.ro.StudentRequest;

import java.util.List;

/**
 * The interface Student mgmt service.
 */
public interface IStudentMgmtService extends IManagementService {
    /**
     * Gets students.
     *
     * @return the students
     * @throws UnableToProcessException the unable to process exception
     */
    List<Student> getStudents() throws UnableToProcessException;

    /**
     * Add student student.
     *
     * @param request the request
     * @return the student
     * @throws UnableToProcessException the unable to process exception
     */
    Student addStudent(StudentRequest request) throws UnableToProcessException;

    /**
     * Select student by id student.
     *
     * @param id the id
     * @return the student
     * @throws EnityNotFoundException the enity not found exception
     */
    Student selectStudentById(long id) throws EnityNotFoundException;

    /**
     * Update student student.
     *
     * @param id      the id
     * @param request the request
     * @return the student
     * @throws EnityNotFoundException the enity not found exception
     */
    Student updateStudent(long id, StudentRequest request) throws EnityNotFoundException;

    /**
     * Delete student boolean.
     *
     * @param id the id
     * @return the boolean
     * @throws EnityNotFoundException   the enity not found exception
     * @throws UnableToProcessException the unable to process exception
     */
    boolean deleteStudent(long id) throws EnityNotFoundException, UnableToProcessException;

    /**
     * Gets students by course id.
     *
     * @param id the id
     * @return the students by course id
     */
    List<Student> getStudentsByCourseId(long id);
}
