package com.revature.ECommerce.Repositories;

import com.revature.ECommerce.Model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findAccountByAccountId(long accountId);

    Account findAccountByEmail(String email);

    Account findAccountByEmailAndPassword(String email, String password);

}
