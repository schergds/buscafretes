package schergds.com.buscafretes.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import schergds.com.buscafretes.entity.Freight;
import schergds.com.buscafretes.entity.User;
import schergds.com.buscafretes.entity.enums.StatusFrete;
import schergds.com.buscafretes.entity.enums.TipoCaminhao;
import schergds.com.buscafretes.repository.FreightRepository;

import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FreightService {

    private final FreightRepository freightRepository;

    public FreightService(FreightRepository freightRepository) {
        this.freightRepository = freightRepository;
    }

    public Page<Freight> findAvailableFreights(String origem, String destino, TipoCaminhao tipoCaminhao, Pageable pageable) {
        Specification<Freight> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(cb.equal(root.get("status"), StatusFrete.ABERTO));

            if (origem != null && !origem.isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("origem")), "%" + origem.toLowerCase() + "%"));
            }
            if (destino != null && !destino.isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("destino")), "%" + destino.toLowerCase() + "%"));
            }
            if (tipoCaminhao != null) {
                predicates.add(cb.equal(root.get("tipoCaminhao"), tipoCaminhao));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };

        return freightRepository.findAll(spec, pageable);
    }

    public List<Freight> findUserFreights(User user) {
        return freightRepository.findByUserOrderByCreatedAtDesc(user);
    }

    public Optional<Freight> findById(Long id) {
        return freightRepository.findById(id);
    }

    @Transactional
    public Freight save(Freight freight) {
        return freightRepository.save(freight);
    }

    @Transactional
    public void delete(Long id) {
        freightRepository.deleteById(id);
    }
}
