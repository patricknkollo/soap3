
package com.spring.soap3.soap3.repositories;

import com.spring.soap3.soap3.entities.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface PersonRepositoryDAO extends CrudRepository<PersonEntity, Integer>, JpaRepository<PersonEntity, Integer> {
}

