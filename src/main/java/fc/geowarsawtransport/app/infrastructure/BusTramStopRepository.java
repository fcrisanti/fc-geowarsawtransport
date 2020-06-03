package fc.geowarsawtransport.app.infrastructure;

import fc.geowarsawtransport.app.domain.objects.BusTramStop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BusTramStopRepository extends JpaRepository<BusTramStop, Long> {
    @Override
    List<BusTramStop> findAll();

    List<BusTramStop> findAllByName(String name);

    List<BusTramStop> findAllByZespol(long zespol);
}
