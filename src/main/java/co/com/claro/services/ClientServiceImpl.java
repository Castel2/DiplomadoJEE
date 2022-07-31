package co.com.claro.services;

import co.com.claro.entity.Client;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

import java.util.List;

@RequestScoped
@Transactional
public class ClientServiceImpl implements Service<Client> {

    @PersistenceContext(name = "claro")
    private EntityManager em;

    @Override
    public Client save(Client client) {
        em.persist(client);
        return client;
    }

    @Override
    public Client findById(Long id) {
        Query q =  em.createQuery("SELECT * FROM client WHERE client.id = :id");
        q.setParameter("id", id);
        Client client = (Client) q.getSingleResult();
        return client;
    }

    @Override
    public List<Client> findAll() {
        Query q =  em.createQuery("SELECT * FROM client");
        List<Client> clients = q.getResultList();
        return clients;
    }

    @Override
    public void delete(Client client) {
        em.remove(client);
    }
}
