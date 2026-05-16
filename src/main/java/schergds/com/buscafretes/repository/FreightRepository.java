package schergds.com.buscafretes.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import schergds.com.buscafretes.entity.Freight;
import schergds.com.buscafretes.entity.User;
import schergds.com.buscafretes.entity.enums.StatusFrete;

import java.util.List;

@Repository
public interface FreightRepository extends JpaRepository<Freight, Long>, JpaSpecificationExecutor<Freight> {
    Page<Freight> findByStatus(StatusFrete status, Pageable pageable);
    List<Freight> findByUser(User user);
    List<Freight> findByUserOrderByCreatedAtDesc(User user);
}
