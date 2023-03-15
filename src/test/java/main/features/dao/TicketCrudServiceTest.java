package main.features.dao;

import lombok.SneakyThrows;
import main.features.entity.Client;
import main.features.entity.Planet;
import main.features.entity.Ticket;
import main.features.migration.Migration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class TicketCrudServiceTest {

    TicketCrudService crudService = new TicketCrudService();


    @BeforeEach
    public void setup(){
        Migration.migrate();
    }

    @SneakyThrows
    @AfterEach
    public void AfterEach(){
        Connection connection = DriverManager.getConnection("jdbc:h2:./test", "sa", "");
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("DROP ALL OBJECTS DELETE FILES");
        connection.close();
    }

    @SneakyThrows
    Ticket getTicket(){
        Planet planet = new PlanetCrudService().getAllPlanets().get(1);
        Planet planet1 = new PlanetCrudService().getAllPlanets().get(2);
        Client client = new ClientCrudService().findById(1L);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = dateFormat.parse(new Date());
        return new Ticket(date,client,planet,planet1);
    }

    @Test
    void testThatSaveWorks() {
        Ticket ticket = getTicket();
        crudService.save(ticket);
        System.out.println("ticket.toString() = " + ticket.toString());
        System.out.println("crudService.findById(ticket.getId()) = " + crudService.findById(ticket.getId()).toString());

        assertEquals(ticket, crudService.findById(ticket.getId()));
    }

    @Test
    void testThatFindByIdWorks() {
    }

    @Test
    void testThatUpdateWorks() {
    }

    @Test
    void testThatDeleteWorks() {
    }
}