package spring.sse;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class DataRepository {

    private final EntityManager em;

    public Long getNumber() {
        Optional<DataEntity> optionalDataEntity = Optional.ofNullable(em.find(DataEntity.class, 1));
        if (optionalDataEntity.isEmpty()) {
            em.merge(DataEntity.builder().number(0L).build());
        }
        return 0L;
    }

    public Long addNumber() {
        DataEntity dataEntity = em.find(DataEntity.class, 1);
        dataEntity.addNumber();
        return dataEntity.getNumber();
    }
}
