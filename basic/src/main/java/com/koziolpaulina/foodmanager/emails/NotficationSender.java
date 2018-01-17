package com.koziolpaulina.foodmanager.emails;


public class NotficationSender implements EmailParser {

    private String sender;

    public NotficationSender(String sender) {
        this.sender = sender;
    }

    @Override
    public String getEmailTitle() {
        return "Masz nowy przepis!";
    }

    @Override
    public String getEmailBody() {
        return "<div>Użytkownik "+sender+" udostępnił Ci przepis!</div>\n" +
                "<div style=\"margin-top:40px;\">Smacznego życzy LunchRouletteTeam</div>";
    }
}
