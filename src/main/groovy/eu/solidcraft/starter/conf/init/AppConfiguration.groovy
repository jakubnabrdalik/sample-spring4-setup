package eu.solidcraft.starter.conf.init

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.web.servlet.config.annotation.EnableWebMvc

@Configuration
@EnableJpaRepositories(basePackages = ["eu.solidcraft.starter"])
@ComponentScan(basePackages = ["eu.solidcraft.starter"])
@EnableWebMvc
class AppConfiguration {
}
