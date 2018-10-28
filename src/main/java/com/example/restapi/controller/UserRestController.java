package com.example.restapi.controller;

import com.example.restapi.model.UserModel;
import com.example.restapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserRestController {

    @Autowired
    private UserService service;

    /* список имеющихся пользователей */
    @RequestMapping(value = "/show/users", method = RequestMethod.GET)
    public List<UserModel> userLoginList() {
        List<UserModel> users = service.getAllUsers();
        return users;
    }

    @RequestMapping(value = "/ajax/show/users", method = RequestMethod.GET)
    public List<UserModel> showUsersForAjava(HttpServletRequest request) {
        String[] shownIds = request.getParameter("shownIds").split(",");
        List<UserModel> users = service.getAllUsers();
        if (shownIds.length > 0) {
            users = users
                    .stream()
                    .filter(userModel -> {
                        for (int i = 0; i < shownIds.length; i++) {
                            if (shownIds[i].equals(userModel.getId().toString())) {
                                return false;
                            }
                        }
                        return true;
                    })
                    .collect(Collectors.toList());
        }
        return users;
    }


//    /* отображение пользователей по ID */
//    @RequestMapping(value = "/show/user/{id}", method = RequestMethod.GET)
//    public UserModel userLoginIDList(@PathVariable("id") int user_id) {
//        return service.retrieveUser(user_id);
//    }
//
//    /* изменения статуса у пользователей */
//    @RequestMapping(value = "/show/user/{id}/{status}", method = RequestMethod.GET)
//    public HashMap<String, String> retrieveStatus(@PathVariable("id") int id, @PathVariable("status") String status_user) {
//        UserModel userLogins;
//        userLogins = service.retrieveUser(id);
//        if (userLogins != null) {
//            String statis_iser = userLogins.getStatus();
//            userLogins.setStatus(status_user);
//            service.updateUserLogin(userLogins);
//            return new UserModel(id, statis_iser);
//        }
//        return new UserModel("can't found id = " + id);
//    }


}
