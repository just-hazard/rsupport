package just.hazard.rsupport.controller;

import just.hazard.rsupport.domain.Notice;
import just.hazard.rsupport.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/rest")
public class RestBoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/board")
    public Page<Notice> restList(Pageable pageable) {
        return boardService.findAll(pageable);
    }

    @PostMapping("/board")
    @ResponseStatus(HttpStatus.CREATED)
    public Notice restWrite(@RequestBody Notice notice) {
        return boardService.save(notice);
    }

    @PutMapping("/board/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void restUpdate(@PathVariable Long id, @RequestBody Notice notice) {
        boardService.save(notice,id);
    }

    @DeleteMapping("/board/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void restDelete(@PathVariable Long id) {
        boardService.delete(id);
    }
}
