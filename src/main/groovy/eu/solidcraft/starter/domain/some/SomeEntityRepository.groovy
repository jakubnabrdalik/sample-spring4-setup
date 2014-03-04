package eu.solidcraft.starter.domain.some

import groovy.transform.TypeChecked
import org.springframework.data.jpa.repository.JpaRepository

@TypeChecked
interface SomeEntityRepository extends JpaRepository<SomeEntity, Long> {
    List<SomeEntity> findByUsername(String username)
}