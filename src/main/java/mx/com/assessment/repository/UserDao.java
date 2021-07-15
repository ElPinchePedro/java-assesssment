package mx.com.assessment.repository;

import org.springframework.data.repository.CrudRepository;

import mx.com.assessment.model.User;

public interface UserDao extends CrudRepository<User, Long> {

}
