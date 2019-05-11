package by.weekmenu.repos;

import by.weekmenu.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepo extends JpaRepository<Account, Long> {
    Account findByName(String s);
}
