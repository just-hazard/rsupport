package just.hazard.rsupport.controller;

import just.hazard.rsupport.domain.Account;
import just.hazard.rsupport.domain.LoginDto;
import just.hazard.rsupport.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.security.auth.login.AccountNotFoundException;

@RestController
@RequestMapping("/rest")
public class RestAccountController {

    @Autowired
    AccountService accountService;

    @PostMapping("/login")
    public LoginDto login(@RequestBody Account account) throws AccountNotFoundException {

        Account result = accountService.findByEmail(account.getEmail());
        if(result == null || !result.getPassword().equals(account.getPassword())) {
            throw new AccountNotFoundException();
        }

        LoginDto loginDto = new LoginDto();
        loginDto.setEmail(result.getEmail());
        loginDto.setPassword(result.getPassword());

        return loginDto;
    }
}
