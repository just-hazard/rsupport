package just.hazard.rsupport.controller;

import just.hazard.rsupport.domain.Account;
import just.hazard.rsupport.domain.Notice;
import just.hazard.rsupport.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/list")
    public String getList(Pageable pageable, Model model) {

        Page<Notice> list = boardService.findAll(pageable);
        model.addAttribute("list",list);

        return "/board/list";
    }


    @GetMapping("/write")
    public String getWrite() {
        return "/board/write";
    }

    @PostMapping("/write")
    public String postWrite(HttpSession session, Notice notice) {
        Account user = (Account) session.getAttribute("user");
        if(user == null){
            return "/login";
        } else {
            notice.setUser(user);
            boardService.save(notice);
            return "redirect:/board/list";
        }
    }

    @GetMapping("/read")
    public String getRead(Notice notice,Model model) {

        Optional<Notice> result = boardService.read(notice.getId());
        if(result.isPresent()) {
            model.addAttribute("result",result.get());
        }

        return "board/read";
    }

    @GetMapping("/update-read")
    public String readUpdate(Notice notice,Model model) {

        Optional<Notice> result = boardService.read(notice.getId());
        if(result.isPresent()) {
            model.addAttribute("result",result.get());
        }

        return "board/update";
    }

    @PostMapping("/update")
    public String update(HttpSession session, Notice notice) {

        Account user = (Account) session.getAttribute("user");
        if(user == null){
            return "/login";
        } else {
            notice.setUser(user);
            boardService.save(notice);
            return "redirect:/board/list";
        }
    }

    @GetMapping("/delete")
    public String delete(Notice notice, HttpSession session) {
        Account user = (Account) session.getAttribute("user");
        if(user == null){
            return "/login";
        } else {
            boardService.delete(notice.getId());
        }

        return "redirect:/board/list";
    }
}
