package s.praveen.cms.common;

/**
 * The type Path.
 */
public class Path {
    /**
     * The constant USER.
     */
    public static final String USER = "/user";
    /**
     * The constant STUDENT.
     */
    public static final String STUDENT = "/student";
    /**
     * The constant COURSE.
     */
    public static final String COURSE = "/course";
    /**
     * The constant ASSIGNMENT.
     */
    public static final String ASSIGNMENT = "/assignment";
    /**
     * The constant ID_PATH_VARIABLE.
     */
    public static final String ID_PATH_VARIABLE = "id";
    /**
     * The constant ID.
     */
    public static final String ID = "/{" + ID_PATH_VARIABLE + "}";

    /**
     * The constant COURSE_ID_PATH_VARIABLE.
     */
    public static final String COURSE_ID_PATH_VARIABLE = "/{course_id}";
    /**
     * The constant STD_ID_PATH_VARIABLE.
     */
    public static final String STD_ID_PATH_VARIABLE = "/{student_id}";
}
