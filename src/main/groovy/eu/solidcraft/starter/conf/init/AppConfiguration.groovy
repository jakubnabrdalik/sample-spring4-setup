package eu.solidcraft.starter.conf.init

import groovy.transform.TypeChecked
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.ScopedProxyMode
import org.springframework.web.servlet.config.annotation.EnableWebMvc

@Configuration
@ComponentScan(basePackages = ["eu.solidcraft.starter"], scopedProxy = ScopedProxyMode.TARGET_CLASS)
@EnableWebMvc
@TypeChecked
class AppConfiguration {
}
