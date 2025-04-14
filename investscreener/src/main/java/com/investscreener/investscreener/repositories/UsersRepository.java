package com.investscreener.investscreener.repositories;


import com.investscreener.investscreener.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
     Users findByUsername(String username);
}

// Persistent context (PC): container for entities that should be tracked
// and managed by hibernate
// The lifetime of a PC is tight to a transaction. It is started  when a
// transaction is created and it ends.

// States:
// Transient(temporary) -> create an entity using the new keyword

// Persistent: call the save method of the repository. The entity is added to the persistent context.
// It is the working memory of hibernate; objects are tracked and managed by hibernate) ->

// Detached: The job is done, the PC gets cleared and the entity becomes detached.
// The entity is no longer tracked by hibernate. The entity has a db identifier
// If you make changes to the detached entity, it will be added to the PC
// and go through the same life cycle

// Removed-> Delete a persistent entity, it transitions to the removed state.
// Hibernate deletes the entity from the db, and the entity becomes transient again.

// Managing transactions:
// Spring has a special annotation @Transactional that allows us
// to change the transaction boundary to the entire method.
// You can't apply the @Transactional method to static methods

