package eu.solidcraft.starter.controller
import base.IntegrationSpec
import eu.solidcraft.starter.domain.some.SomeEntity
import eu.solidcraft.starter.domain.some.SomeEntityRepository
import org.springframework.beans.factory.annotation.Autowired

class SomeControllerIntegrationSpec extends IntegrationSpec {
    @Autowired SomeController someController
    @Autowired SomeEntityRepository someEntityRepository
    BigDecimal amount = new BigDecimal(1000)

    def "add should save entity to DB"() {
        when:
            someController.add(amount)

        then:
            List<SomeEntity> entities = someEntityRepository.findByUsername(user.username)
            entities.size() == 1
            SomeEntity someEntity = entities.first()
            someEntity.someAmount == amount
            someEntity.username == user.username
    }

    def "should show my entities"() {
        given:
            someController.add(amount)

        when:
            Map response = someController.mine()

        then:
            SomeEntity someEntity = response.entities.first()
            someEntity.someAmount == amount
            someEntity.username == user.username
    }
}
