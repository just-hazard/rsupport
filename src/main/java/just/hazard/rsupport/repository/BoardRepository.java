package just.hazard.rsupport.repository;

import just.hazard.rsupport.domain.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Notice, Long> {
}
