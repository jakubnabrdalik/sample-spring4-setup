package eu.solidcraft.starter.infrastructure.security

import groovy.transform.TypeChecked
import org.springframework.stereotype.Component

@Component
@TypeChecked
class LoggedUserRepository {
    String getLoggedUserName() {
        return "John"
    }
}
