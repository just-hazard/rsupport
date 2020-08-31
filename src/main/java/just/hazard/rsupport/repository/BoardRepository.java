package just.hazard.rsupport.repository;

import just.hazard.rsupport.domain.Notice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BoardRepository extends JpaRepository<Notice, Long> {

    @EntityGraph(attributePaths = "user")
    @Query("select a from Notice a")
    Page<Notice> findAllJoinFetch(Pageable pageable);
}
