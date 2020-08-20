package just.hazard.rsupport.config;

import just.hazard.rsupport.domain.Account;
import just.hazard.rsupport.domain.Notice;
import just.hazard.rsupport.repository.AccountRepository;
import just.hazard.rsupport.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class Runner implements ApplicationRunner {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        Account account = new Account();
        account.setEmail("admin");
        account.setPassword("1234");
        accountRepository.save(account);

        for(int i=0; i < 50; i++){
            Notice notice = new Notice();
            notice.setTitle("제목" + i);
            notice.setContent("내용");
            notice.setUser(account);
            boardRepository.save(notice);
        }
    }
}
