package eu.solidcraft.starter.domain.some
import org.springframework.data.jpa.repository.JpaRepository

interface SomeEntityRepository extends JpaRepository<SomeEntity, Long> {
    List<SomeEntity> findByUsername(String username)
}