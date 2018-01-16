package com.greglturnquist.payroll.controllers;

import com.greglturnquist.payroll.recipes.dto.ShoppingListDTO;
import com.greglturnquist.payroll.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

/**
 * Created by Paulina on 16.01.2018.
 */
@RestController
@RequestMapping(path = "/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping(path = "/shoppingList")
    public ResponseEntity<Void> sendEmail(@RequestBody ShoppingListDTO shoppingListDTO) {

        ResponseEntity<Void> responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        try{
            messageService.sendShoppingList(shoppingListDTO.getRecipeName(), shoppingListDTO.getIngredients());
            responseEntity = new ResponseEntity<>(HttpStatus.OK);
        } catch(MessagingException e){
            e.printStackTrace();
        }

        return responseEntity;

    }


}
