package just.hazard.rsupport.service;

import just.hazard.rsupport.domain.Account;
import just.hazard.rsupport.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    public Account findByEmail(String email) {
        return accountRepository.findByEmail(email);
    }
}
