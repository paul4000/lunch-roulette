package com.koziolpaulina.foodmanager.controllers;

import com.koziolpaulina.foodmanager.recipes.dto.ShoppingListDTO;
import com.koziolpaulina.foodmanager.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;


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

    @GetMapping(path ="/sharingMessage")
    public ResponseEntity<Void> sendNotification(@RequestParam(value = "username") String recipient) {

        ResponseEntity<Void> responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        try{
            messageService.sendSharingNotification(recipient);
            responseEntity = new ResponseEntity<>(HttpStatus.OK);
        } catch(MessagingException e){
            e.printStackTrace();
        }

        return responseEntity;

    }


}
