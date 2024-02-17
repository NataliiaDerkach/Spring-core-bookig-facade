package com.example;
import com.example.implementation.BookingFacadeImpl;
import com.example.implementation.Event;
import com.example.implementation.Ticket;
import com.example.implementation.User;
import com.example.interfaces.services.EventService;
import com.example.interfaces.services.TicketService;
import com.example.interfaces.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes=DemoApplication.class)
public class BookingFacadeMockTest {

    private static final Logger logger = LoggerFactory.getLogger(BookingFacadeIntegrTest.class);
    @Mock
    private UserService userService;

    @Mock
    private EventService eventService;

    @Mock
    private TicketService ticketService;

    @InjectMocks
    private BookingFacadeImpl bookingFacade;

    private User user;
    private Event event;
    private Ticket ticket;

    @BeforeEach
    public void setup() {
        logger.info("Setting up mock services and test data...");

        user = new User(1L, "New", "new@example.com");
        event = new Event(1L, "Show", "Concert Hall");
        ticket = new Ticket(1L, user.getId(), event.getId(), 555);

        when(userService.createUser(any(User.class))).thenReturn(user);
        when(eventService.createEvent(any(Event.class))).thenReturn(event);
        when(ticketService.bookTicket(any(Long.class), any(Long.class), any(Integer.class))).thenReturn(ticket);
    }

    @Test
    public void whenCreateEvent_thenEventServiceCreateEventIsCalled() {
        Event event = new Event();
        when(eventService.createEvent(any(Event.class))).thenReturn(event);

        logger.info("Testing createEvent method");

        Event createdEvent = bookingFacade.createEvent(new Event());

        verify(eventService, times(1)).createEvent(any(Event.class));

        assertSame(event, createdEvent);

        logger.info("EventService createEvent was called as expected");

    }

    @Test
    public void whenDeleteUser_thenUserServiceDeleteUserIsCalled() {
        long userId = 1L;
        when(userService.deleteUser(userId)).thenReturn(true);

        logger.info("Testing deleteUser method");

        boolean result = bookingFacade.deleteUser(userId);

        verify(userService, times(1)).deleteUser(userId);
        assertTrue(result);

        logger.info("UserService deleteUser was called as expected");
    }

    @Test
    public void whenBookTicket_thenTicketServiceBookTicketIsCalled() {
        int place = 10;
        when(ticketService.bookTicket(user.getId(), event.getId(), place)).thenReturn(ticket);

        logger.info("Testing bookTicket method");

        Ticket bookedTicket = bookingFacade.bookTicket(user.getId(), event.getId(), place);
        Ticket bookedTicket2 = bookingFacade.bookTicket(user.getId(), event.getId(), place);

        verify(ticketService, times(2)).bookTicket(user.getId(), event.getId(), place);
        assertNotNull(bookedTicket);
        assertNotNull(bookedTicket2);
        logger.info("TicketService bookTicket was called as expected: 2 times!");
    }

    @Test
    public void testBookTicket() {
        int seat = 555;
        logger.info("Testing ticket booking...");
        Ticket bookedTicket = bookingFacade.bookTicket(user.getId(), event.getId(), 555);

        assertNotNull(bookedTicket);
        assertEquals(user.getId(), bookedTicket.getUserId());
        assertEquals(event.getId(), bookedTicket.getEventId());
        assertEquals(seat, bookedTicket.getSeat());
        logger.info("Ticket booking test passed.");


    }
}
