package main.features.dao;

import main.features.utils.HibernateUtils;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import main.features.entity.Ticket;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

interface TicketCrud {
    void save(Ticket ticket);

    Ticket findById(Long id);

    void update(Ticket ticket);

    void delete(Ticket ticket);
}

public class TicketCrudService implements TicketCrud {

    @Override
    public void save(Ticket ticket) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(ticket);
            transaction.commit();
        } catch (Exception exception) {
            System.err.println("Error occurred due to the saving " + exception.getMessage());
        }
    }

    @Override
    public Ticket findById(Long id) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            return session.get(Ticket.class, id);
        } catch (Exception ex) {
            System.err.println("Error, ticket not found");
            return null;
        }

    }

    @Override
    public void update(Ticket ticket) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(ticket);
            transaction.commit();
        } catch (Exception ex) {
            System.err.println("Error occurred due to the updating " + ex.getMessage());
        }
    }

    @Override
    public void delete(Ticket ticket) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(ticket);
            transaction.commit();
        } catch (Exception ex) {
            System.err.println("Error occurred due to the deleting " + ex.getMessage());
        }
    }

    public List<Ticket> getAllTickets() {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Ticket> criteriaQuery = builder.createQuery(Ticket.class);
            Root<Ticket> root = criteriaQuery.from(Ticket.class);
            return session.createQuery(criteriaQuery).getResultList();
        } catch (Exception ex) {
            System.err.println("There is no Tickets");
            return null;
        }
    }
}
