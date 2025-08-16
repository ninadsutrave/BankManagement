package com.bankmanagement.dao.interfaces;

import com.bankmanagement.entity.account.AccountDTO;
import java.sql.Connection;

public interface AccountDAO {
    boolean createAccount(AccountDTO account);
}
