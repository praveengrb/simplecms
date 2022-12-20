package s.praveen.cms.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import s.praveen.cms.common.ServiceType;
import s.praveen.cms.exception.EnityNotFoundException;
import s.praveen.cms.exception.UnableToProcessException;
import s.praveen.cms.model.dto.User;
import s.praveen.cms.model.ro.UserRequest;
import s.praveen.cms.repository.IUserRepository;
import s.praveen.cms.service.IUserMgmtService;

import java.util.List;


/**
 * The type User mgmt service.
 */
@Service(ServiceType.USER)
public class UserMgmtServiceImpl implements IUserMgmtService {
    private static final Logger LOG = LoggerFactory.getLogger(UserMgmtServiceImpl.class.getName());
    /**
     * The User repository.
     */
    @Autowired
    IUserRepository userRepository;

    @Override
    public List<User> getUsers() throws UnableToProcessException {
        try {
            return userRepository.findAll();
        } catch (Exception e) {
            LOG.error("Unable to get all the user information {}", e.getMessage());
            throw new UnableToProcessException(e.getMessage());
        }
    }

    @Override
    public User addUser(UserRequest request) {
        LOG.info("Adding the the user information {}", request.toString());
        return userRepository.save(new User(request));
    }

    @Override
    public User getUserById(long id) throws EnityNotFoundException {
        LOG.info("Getting the the user information {}", id);
        return userRepository
                .findById(id)
                .orElseThrow(EnityNotFoundException::new);
    }

    @Override
    public User updateUserById(long id, UserRequest request) throws EnityNotFoundException {
        User user = getUserById(id);
        LOG.info("Updating the user information for the given id {}", id);
        user.setUserName(request.userName());
        return userRepository.save(user);
    }

    @Override
    public boolean deleteUserById(long id) throws UnableToProcessException {

        LOG.info("Deleting the user information for the given id {}", id);
        try {
            userRepository.delete(getUserById(id));
            return true;
        } catch (Exception e) {
            LOG.error("Unable to delete the user information {}", e.getMessage());
            throw new UnableToProcessException(e.getMessage());
        }

    }
}