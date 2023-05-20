package micro.spv.clientes.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import micro.spv.clientes.entities.ClientEntity;

@Repository
public class ClientRepository {
    
    @PersistenceContext
    EntityManager entityManager;


    public <T> void saveEntity(T entityClass){
        entityManager.persist(entityClass);
    }

    public List<ClientEntity> findClientByFullName(String firsName, String secondName, String firstLastName, String secondLastName){
        var criteriaBuilder = entityManager.getCriteriaBuilder();
        var criteriaQuery = criteriaBuilder.createQuery(ClientEntity.class);
        var rootQuery = criteriaQuery.from(ClientEntity.class);

        var predicate = criteriaBuilder.equal(rootQuery.get("firstname"), firsName);
        predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(rootQuery.get("secondName"), secondName));
        predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(rootQuery.get("firstLastName"), firstLastName));
        predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(rootQuery.get("secondLastName"), secondLastName));
        
        criteriaQuery.where(predicate);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    public List<ClientEntity> findAllClients(){
        var criteriaBuilder = entityManager.getCriteriaBuilder();
        var criteriaQuery = criteriaBuilder.createQuery(ClientEntity.class);
        var rootQuery = criteriaQuery.from(ClientEntity.class);

        criteriaQuery.select(rootQuery);

        return entityManager.createQuery(criteriaQuery).getResultList();
    }
    
}
