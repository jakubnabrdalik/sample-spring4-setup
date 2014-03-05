package eu.solidcraft.starter.conf.init

import groovy.transform.TypeChecked
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.Database
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement

import javax.sql.DataSource

@TypeChecked
@Configuration
@EnableJpaRepositories(basePackages = ["eu.solidcraft.starter"])
@EnableTransactionManagement
class EmbeddedDatabaseConfiguration {
    private final static String PACKAGES_WITH_JPA_ENTITIES = "eu.solidcraft.starter.domain"

    private DataSource dataSource() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder()
        return builder.setType(EmbeddedDatabaseType.HSQL).build()
    }

    @Bean
    LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean()
        factory.setJpaVendorAdapter(hibernateJpaVendorAdapter())
        factory.setPackagesToScan(PACKAGES_WITH_JPA_ENTITIES)
        factory.setDataSource(dataSource())
        return factory
    }

    private HibernateJpaVendorAdapter hibernateJpaVendorAdapter() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter()
        vendorAdapter.setDatabase(Database.HSQL)
        vendorAdapter.setGenerateDdl(true)
        vendorAdapter.setShowSql(true)
        return vendorAdapter
    }

    @Bean
    PlatformTransactionManager transactionManager() {
        JpaTransactionManager txManager = new JpaTransactionManager()
        txManager.setEntityManagerFactory(entityManagerFactory().getObject())
        return txManager
    }
}
