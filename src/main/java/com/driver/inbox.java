package com.driver;

import java.util.Date;

public class inbox {

    Date date;
    String message;

    String sender;

    public inbox(Date date, String message, String sender)
    {
        this.date=date;
        this.message = message;
        this.sender=sender;
    }
}
