package co.com.claro.services;

import co.com.claro.entity.Equipment;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
@Transactional
public class EquipmentServiceImpl implements Service<Equipment> {

    @PersistenceContext(name = "claro")
    private EntityManager em;

    @Override
    public Equipment save(Equipment equipment) {
        em.persist(equipment);
        return equipment;
    }

    @Override
    public Equipment findById(Long id) {
        Query q = em.createQuery("SELECT * FROM equipment WHERE equipment.id = :id");
        q.setParameter("id", id);
        Equipment equipment = (Equipment) q.getSingleResult();
        return equipment;
    }

    @Override
    public List<Equipment> findAll() {
        Query q = em.createQuery("SELECT * FROM equipment");
        List<Equipment> equipments = q.getResultList();
        return equipments;
    }

    @Override
    public void delete(Equipment equipment) {
        em.remove(equipment);
    }
}
