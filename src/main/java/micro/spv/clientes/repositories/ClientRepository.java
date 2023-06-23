package micro.spv.clientes.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import micro.spv.clientes.entities.ClientEntity;
import micro.spv.clientes.entities.PhoneEntity;

@Repository
public class ClientRepository {
    
    @PersistenceContext
    EntityManager entityManager;


    public <T> void saveEntity(T entityClass){
        entityManager.persist(entityClass);
    }

    public <T> void updateEntity(T entityClass){
        entityManager.merge(entityClass);
    }

    public List<ClientEntity> findClientByFullName(String firsName, String secondName, String firstLastName, String secondLastName){
        var criteriaBuilder = entityManager.getCriteriaBuilder();
        var criteriaQuery = criteriaBuilder.createQuery(ClientEntity.class);
        var rootQuery = criteriaQuery.from(ClientEntity.class);

        var predicate = criteriaBuilder.like(rootQuery.get("firstname"),"%" + firsName + "%");
        predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(rootQuery.get("secondName"), "%" + secondName + "%"));
        predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(rootQuery.get("firstLastName"), "%" + firstLastName + "%"));
        predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(rootQuery.get("secondLastName"), "%" + secondLastName + "%"));
        
        criteriaQuery.where(predicate);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    public ClientEntity findClientById(int clientId){
        var criteriaBuilder = entityManager.getCriteriaBuilder();
        var criteriaQuery = criteriaBuilder.createQuery(ClientEntity.class);
        var rootQuery = criteriaQuery.from(ClientEntity.class);

        var predicate = criteriaBuilder.equal(rootQuery.get("identityclient"), clientId);
        criteriaQuery.where(predicate);

        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }

    public List<PhoneEntity> findPhoneByIdentityClient(int idClient){
        var criteriaBuilder = entityManager.getCriteriaBuilder();
        var criteriaQuery = criteriaBuilder.createQuery(PhoneEntity.class);
        var rootQuery = criteriaQuery.from(PhoneEntity.class);

        var predicate = criteriaBuilder.equal(rootQuery.get("identityClient"), idClient);
        criteriaQuery.where(predicate);
        return entityManager.createQuery(criteriaQuery).getResultList();
     }

     public PhoneEntity findPhoneByIdentityPhone(int idPhone){
        var criteriaBuilder = entityManager.getCriteriaBuilder();
        var criteriaQuery = criteriaBuilder.createQuery(PhoneEntity.class);
        var rootQuery = criteriaQuery.from(PhoneEntity.class);

        var predicate = criteriaBuilder.equal(rootQuery.get("identityPhone"), idPhone);
        
        criteriaQuery.where(predicate);
        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }
    
}
