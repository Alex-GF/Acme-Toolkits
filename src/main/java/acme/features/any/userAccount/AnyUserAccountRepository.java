package acme.features.any.userAccount;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.entities.UserAccount;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnyUserAccountRepository extends AbstractRepository{
    @Query("SELECT ua FROM UserAccount ua JOIN ua.roles r WHERE ua.enabled = true AND "
    	+ "(type(r) = Inventor or type(r) = Patron) AND "
    	+ "Administrator NOT IN (SELECT type(r) FROM UserAccount ua2 JOIN ua2.roles r "
    	+ "WHERE ua2.id = ua.id)")
    Collection<UserAccount> findAllUserAccounts();

    @Query("SELECT ua FROM UserAccount ua WHERE ua.id = :userAccountId")
    UserAccount findUserAccountById(int userAccountId);
}
