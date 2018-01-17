package com.greglturnquist.payroll.services;

import com.greglturnquist.payroll.auth.SecurityService;
import com.greglturnquist.payroll.auth.login.User;
import com.greglturnquist.payroll.emails.EmailSender;
import com.greglturnquist.payroll.emails.ShoppingListSender;
import com.greglturnquist.payroll.recipeUtils.UsersRegister;
import com.greglturnquist.payroll.recipes.Ingredient;
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
