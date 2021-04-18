package apass.personapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apass.personapi.entity.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{

}
