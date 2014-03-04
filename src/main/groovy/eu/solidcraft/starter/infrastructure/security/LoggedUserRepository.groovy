package eu.solidcraft.starter.infrastructure.security

import org.springframework.stereotype.Component

@Component
class LoggedUserRepository {
    String getLoggedUserName() {
        return "John"
    }
}
