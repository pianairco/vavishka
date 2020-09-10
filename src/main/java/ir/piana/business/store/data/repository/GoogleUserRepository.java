package ir.piana.business.store.data.repository;

import ir.piana.business.store.data.entity.GoogleUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoogleUserRepository extends JpaRepository<GoogleUserEntity, Long> {
    GoogleUserEntity findByUsername(String email);
}
