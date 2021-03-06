package just.hazard.rsupport.service;

import just.hazard.rsupport.domain.Notice;
import just.hazard.rsupport.domain.NoticeListDto;
import just.hazard.rsupport.mapper.NoticeListMapper;
import just.hazard.rsupport.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private NoticeListMapper noticeListMapper;

    public Notice save(Notice notice) {
        boardRepository.save(notice);
        return notice;
    }

    public Notice save(Notice notice, Long id) {
        Notice user = boardRepository.getOne(id);
        notice.setId(id);
        notice.setUser(user.getUser());
        return boardRepository.save(notice);
    }

    public Page<NoticeListDto> findAll(Pageable pageable) {
        Page<Notice> result = boardRepository.findAllJoinFetch(pageable);
        return result.map(notice -> noticeListMapper.toDto(notice));
    }

    public Optional<Notice> read(Long id) {
        return boardRepository.findById(id);
    }

    public void delete(Long id) {
        boardRepository.deleteById(id);
    }
}
