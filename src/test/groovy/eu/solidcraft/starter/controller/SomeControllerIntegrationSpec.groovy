package eu.solidcraft.starter.controller
import base.IntegrationSpec
import eu.solidcraft.starter.domain.some.SomeEntity
import eu.solidcraft.starter.domain.some.SomeEntityRepository
import eu.solidcraft.starter.infrastructure.security.LoggedUserRepository
import org.springframework.beans.factory.annotation.Autowired

class SomeControllerIntegrationSpec extends IntegrationSpec {
    @Autowired SomeEntityRepository someEntityRepository
    @Autowired LoggedUserRepository loggedUserRepository
    @Autowired SomeController someController
    BigDecimal amount = new BigDecimal(1000)

    Closure matchAmountUserName = {it.someAmount == amount && it.username == loggedUserRepository.getLoggedUserName()}

    def "add should save entity to DB"() {
        when:
            someController.add(amount)

        then:
            List<SomeEntity> entities = someEntityRepository.findByUsername(loggedUserRepository.getLoggedUserName())
            entities.count {matchAmountUserName} == 1
            }

    def "should show my entities"() {
        given:
            someController.add(amount)

        when:
            Map response = someController.mine()

        then:
            response.entities.count {matchAmountUserName } == 1
    }
}
