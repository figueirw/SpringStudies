package br.com.devdojo.awesome.repository;

import br.com.devdojo.awesome.model.SpringUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<SpringUser, Long> {

    SpringUser findByUserName(String userName);

}
