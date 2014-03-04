package base

import eu.solidcraft.starter.conf.init.AppConfiguration
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.transaction.TransactionConfiguration
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.transaction.annotation.Transactional
import spock.lang.Specification

@Transactional
@TransactionConfiguration(defaultRollback = true)
@ContextConfiguration(classes = [AppConfiguration])
//WARNING: cannot use Profiles class here, thought this has to equal to Profiles.TEST, because: http://jira.codehaus.org/browse/GROOVY-3278
@ActiveProfiles(profiles = ['starter.test'])
@WebAppConfiguration
abstract class IntegrationSpec extends Specification {
}
