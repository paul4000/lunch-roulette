package com.koziolpaulina.foodmanager.services;

import com.koziolpaulina.foodmanager.auth.SecurityService;
import com.koziolpaulina.foodmanager.auth.login.User;
import com.koziolpaulina.foodmanager.emails.EmailSender;
import com.koziolpaulina.foodmanager.emails.ShoppingListSender;
import com.koziolpaulina.foodmanager.recipeUtils.UsersRegister;
import com.koziolpaulina.foodmanager.recipes.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.List;

@Service
public class MessageService {

    @Autowired
    private UsersRegister usersRegister;

    @Autowired
    private SecurityService securityService;


    public void sendShoppingList(String recipeName, List<Ingredient> ingredientList) throws MessagingException{

        String loggedInUsername = securityService.findLoggedInUsername();

        User currentUser = usersRegister.getUser(loggedInUsername);

        EmailSender.sendEmail(currentUser.getEmail(),
                new ShoppingListSender(recipeName, ingredientList));

    }


}
