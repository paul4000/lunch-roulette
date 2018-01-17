package com.greglturnquist.payroll.emails;


public interface EmailParser {
    String getEmailTitle();
    String getEmailBody();
}
