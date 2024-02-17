package com.example;

import com.example.interfaces.BookingFacade;
import com.example.interfaces.services.EventService;
import com.example.interfaces.services.TicketService;
import com.example.interfaces.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DemoApplication {


    private static final Logger logger = LoggerFactory.getLogger(DemoApplication.class);

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        BookingFacade bookingFacade = context.getBean("bookingFacade", BookingFacade.class);


        logger.info("Found Event: " + bookingFacade.getEventById(1) );

    }
}