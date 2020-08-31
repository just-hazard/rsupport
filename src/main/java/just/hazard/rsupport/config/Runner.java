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

        Account account1 = new Account();
        account1.setEmail("admin1");
        account1.setPassword("1234");
        accountRepository.save(account1);

        for(int i=0; i < 20; i++){
            Notice notice = new Notice();
            notice.setTitle("제목" + i);
            notice.setContent("내용");
            notice.setUser(account);
            boardRepository.save(notice);
        }

        for(int i=20; i < 40; i++){
            Notice notice = new Notice();
            notice.setTitle("제목" + i);
            notice.setContent("내용");
            notice.setUser(account1);
            boardRepository.save(notice);
        }
    }
}
