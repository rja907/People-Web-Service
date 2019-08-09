package com.appsheet.people.controller;

import com.appsheet.people.controller.views.User;
import com.appsheet.people.controller.views.UserList;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;

@RestController
public class UserController {
    private String appsheetBaseUrl = "https://appsheettest1.azurewebsites.net/sample/";
    private RestTemplate restTemplate = new RestTemplate();
    private Gson gson = new Gson();

    private ArrayList<Integer> getUserList() {
        UserList userList = restTemplate.getForObject(appsheetBaseUrl+"list", UserList.class);
        String latestToken = userList.getToken();
        while ( latestToken != null) {
            UserList userListWithToken = restTemplate.getForObject(appsheetBaseUrl+"list?token="+latestToken, UserList.class);
            userList.getResult().addAll(userListWithToken.getResult());
            latestToken = userListWithToken.getToken();
        }
        return userList.getResult();
    }

    private ArrayList<User> getUserDetails(ArrayList<Integer> userList) {
        ArrayList<User> users = new ArrayList<>();
        for (int i = 0; i < userList.size(); i++) {
            try {
                users.add(restTemplate.getForObject(appsheetBaseUrl + "/detail/" + userList.get(i), User.class));
            } catch (Exception e) {
                continue;
            }
        }
        return users;
    }

    private ArrayList<User> getValidUsers(ArrayList<User> users) {
        ArrayList<User> validUsers = new ArrayList<>();
        String validPhoneNumberPattern = "\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}";
        for (int i = 0; i < users.size(); i++) {
            if (Pattern.compile(validPhoneNumberPattern).matcher(users.get(i).getNumber()).matches()) {
                validUsers.add(users.get(i));
            }
        }
        return validUsers;
    }

    private List<User> getYoungestUsers(List<User> users) {
        users.sort(Comparator.comparing(User::getAge));
        return users.subList(0,5);
    }

    private List<User> sortUserByName(List<User> youngestUsers) {
        youngestUsers.sort(Comparator.comparing(User::getName));
        return youngestUsers;
    }

    /*
        START HERE
    */

    // This method goes through all the steps needed to get the required users.
    @CrossOrigin
    @GetMapping(path = "/api/requiredUsers")
    public String getRequiredUsers() {

        // Fetches the user list from /sample/list
        ArrayList<Integer> userList = getUserList();

        // Fetches user details like id, name, photo, bio, phone
        ArrayList<User> users = getUserDetails(userList);

        // This method filters all the users who don't have valid phone numbers.
        ArrayList<User> validUsers = getValidUsers(users);

        // This is used to get the 5 youngest users.
        List<User> youngestUsers = getYoungestUsers(validUsers);

        // This is used to sort the youngest users by name.
        List<User> sortedYoungestUsers = sortUserByName(youngestUsers);

        // Serializes the Java object to a JsonElement so that it can be consumed by the front end.
        JsonElement requiredUsers = gson.toJsonTree(sortedYoungestUsers);

        // Returns the final JsonElement which has the answer.
        return requiredUsers.toString();
    }

}
