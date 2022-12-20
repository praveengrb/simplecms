package s.praveen.cms.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import s.praveen.cms.common.Path;
import s.praveen.cms.exception.EnityNotFoundException;
import s.praveen.cms.exception.UnableToProcessException;
import s.praveen.cms.model.dto.User;
import s.praveen.cms.model.ro.UserRequest;
import s.praveen.cms.service.IUserMgmtService;

import java.util.List;

/**
 * The type User mgmt controller.
 */
@RestController
@RequestMapping(Path.USER)
public class UserMgmtController {
    private static final Logger LOG = LoggerFactory.getLogger(UserMgmtController.class.getName());
    /**
     * The User mgmt service.
     */
    @Autowired
    IUserMgmtService userMgmtService;

    /**
     * Create user response entity.
     *
     * @param request the request
     * @return the response entity
     */
    @PostMapping

    public @ResponseBody ResponseEntity<User> createUser(@RequestBody UserRequest request) {
        LOG.info("Create Request");
        return ResponseEntity.ok(userMgmtService.addUser(request));
    }

    /**
     * Update request response entity.
     *
     * @param id      the id
     * @param request the request
     * @return the response entity
     */
    @PutMapping(Path.ID)

    public @ResponseBody ResponseEntity<Object> updateRequest(@PathVariable(Path.ID_PATH_VARIABLE) long id, @RequestBody UserRequest request) {
        LOG.info("Update Request");
        try {
            User user = userMgmtService.updateUserById(id, request);
            return ResponseEntity.ok(user);
        } catch (EnityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }

    /**
     * Delete request response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @DeleteMapping(Path.ID)

    public @ResponseBody ResponseEntity<Object> deleteRequest(@PathVariable(Path.ID_PATH_VARIABLE) long id) {
        LOG.info("Delete Request");
        try {
            if (userMgmtService.deleteUserById(id))
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body("Success");
        } catch (EnityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (UnableToProcessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
        return ResponseEntity
                .status(HttpStatus.NOT_MODIFIED)
                .body("Unable to delete");
    }

    /**
     * Select request by id response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @GetMapping(Path.ID)

    public @ResponseBody ResponseEntity<Object> selectRequestById(@PathVariable(Path.ID_PATH_VARIABLE) long id) {
        LOG.info("Select Request By Id");
        try {
            User user = userMgmtService.getUserById(id);
            return ResponseEntity.ok(user);
        } catch (EnityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }

    /**
     * Select all request response entity.
     *
     * @return the response entity
     */
    @GetMapping

    public @ResponseBody ResponseEntity<Object> selectAllRequest() {
        LOG.info("Select all");

        try {
            List<User> users = userMgmtService.getUsers();
            return ResponseEntity.ok(users);
        } catch (UnableToProcessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }
}
