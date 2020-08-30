package just.hazard.rsupport.service;

import just.hazard.rsupport.domain.Account;
import just.hazard.rsupport.domain.LoginDto;
import just.hazard.rsupport.mapper.LoginMapper;
import just.hazard.rsupport.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import javax.servlet.http.HttpSession;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private LoginMapper loginMapper;

    public String findByEmail(Account account, HttpSession session) {
        LoginDto dto = getLoginDto(account);
        if(!dto.getPassword().equals(account.getPassword())) {
            return "login";
        }
        session.setAttribute("user",dto);

        return "redirect:/board/list";
    }

    public LoginDto restFindByEmail(Account account) throws AccountNotFoundException {

        LoginDto dto = getLoginDto(account);

        if(dto == null || !dto.getPassword().equals(account.getPassword())) {
            throw new AccountNotFoundException();
        }

        return dto;

    }

    private LoginDto getLoginDto(Account account) {
        Account result = accountRepository.findByEmail(account.getEmail());
        LoginDto dto = loginMapper.toDto(result);
        return dto;
    }
}
