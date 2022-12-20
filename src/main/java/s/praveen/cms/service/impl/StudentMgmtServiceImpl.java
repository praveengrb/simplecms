package s.praveen.cms.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import s.praveen.cms.common.ServiceType;
import s.praveen.cms.exception.EnityNotFoundException;
import s.praveen.cms.exception.UnableToProcessException;
import s.praveen.cms.model.dto.Student;
import s.praveen.cms.model.ro.StudentRequest;
import s.praveen.cms.repository.IStudentRepository;
import s.praveen.cms.service.IStudentMgmtService;

import java.util.List;


/**
 * The type Student mgmt service.
 */
@Service(ServiceType.STUDENT)
public class StudentMgmtServiceImpl implements IStudentMgmtService {
    private static final Logger LOG = LoggerFactory.getLogger(StudentMgmtServiceImpl.class.getName());
    /**
     * The Student repository.
     */
    @Autowired
    IStudentRepository studentRepository;
    /**
     * The Course registartion service.
     */
    @Autowired
    CourseRegistartionServiceImpl courseRegistartionService;

    @Override
    public List<Student> getStudents() throws UnableToProcessException {
        LOG.info("Getting  list of students...");
        try {
            return studentRepository.findAll();
        } catch (Exception e) {
            LOG.error("Unable to identify the users {}", e.getMessage());
            throw new UnableToProcessException(e.getMessage());
        }
    }

    @Override
    public Student addStudent(StudentRequest request) throws UnableToProcessException {
        try {
            LOG.info("User will be saved");
            return studentRepository.save(new Student(request));
        } catch (Exception e) {
            LOG.error("Unable to add the user {}", e.getMessage());
            throw new UnableToProcessException(e.getMessage());
        }
    }

    @Override
    public Student selectStudentById(long id) throws EnityNotFoundException {
        LOG.info("Select user by id {}", id);
        return studentRepository
                .findById(id)
                .orElseThrow(EnityNotFoundException::new);
    }

    @Override
    public Student updateStudent(long id, StudentRequest request) throws EnityNotFoundException {
        Student student = selectStudentById(id);
        LOG.info("Updating the user by id {}", id);
        student.setStudentName(request.studentName());
        return studentRepository.save(student);
    }

    @Override
    public boolean deleteStudent(long id) throws UnableToProcessException {
        try {
            studentRepository.delete(selectStudentById(id));
            LOG.info("User information is deleted ");
            return true;
        } catch (Exception e) {
            LOG.error("Unable to delete the user {}", e.getMessage());
            throw new UnableToProcessException(e.getMessage());
        }
    }

    @Override
    public List<Student> getStudentsByCourseId(long id) {
        return studentRepository.findAllById(
                courseRegistartionService.getStudentIdsByCourseId(id)
        );
    }
}
