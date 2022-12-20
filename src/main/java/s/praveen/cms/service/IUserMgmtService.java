package s.praveen.cms.service;


import s.praveen.cms.exception.EnityNotFoundException;
import s.praveen.cms.exception.UnableToProcessException;
import s.praveen.cms.model.dto.User;
import s.praveen.cms.model.ro.UserRequest;

import java.util.List;


/**
 * The interface User mgmt service.
 */
public interface IUserMgmtService extends IManagementService {

    /**
     * Gets users.
     *
     * @return the users
     * @throws UnableToProcessException the unable to process exception
     */
    List<User> getUsers() throws UnableToProcessException;

    /**
     * Add user user.
     *
     * @param request the request
     * @return the user
     */
    User addUser(UserRequest request);

    /**
     * Gets user by id.
     *
     * @param id the id
     * @return the user by id
     * @throws EnityNotFoundException the enity not found exception
     */
    User getUserById(long id) throws EnityNotFoundException;

    /**
     * Update user by id user.
     *
     * @param id      the id
     * @param request the request
     * @return the user
     * @throws EnityNotFoundException the enity not found exception
     */
    User updateUserById(long id, UserRequest request) throws EnityNotFoundException;

    /**
     * Delete user by id boolean.
     *
     * @param id the id
     * @return the boolean
     * @throws EnityNotFoundException   the enity not found exception
     * @throws UnableToProcessException the unable to process exception
     */
    boolean deleteUserById(long id) throws EnityNotFoundException, UnableToProcessException;
}
