package eu.solidcraft.starter.conf.init
import groovy.transform.TypeChecked
import org.apache.tomcat.jdbc.pool.DataSource
import org.hsqldb.jdbc.JDBCDriver
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.JpaVendorAdapter
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.Database
import org.springframework.orm.jpa.vendor.HibernateJpaDialect
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter

import javax.persistence.EntityManagerFactory

@TypeChecked
@Configuration
class EmbeddedDatabaseConfiguration {

    @Bean
    javax.sql.DataSource dataSource() {
        DataSource dataSource = new DataSource()
        dataSource.driverClassName = JDBCDriver
        dataSource.url = "jdbc:hsqldb:mem:springStarterDevDb"
        dataSource.password = "SA"
        return dataSource
    }

    @Bean
    LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean managerFactoryBean = new LocalContainerEntityManagerFactoryBean()
        managerFactoryBean.dataSource = dataSource()
        managerFactoryBean.packagesToScan = "eu.solidcraft.starter"
        managerFactoryBean.jpaVendorAdapter = createHibernateJpaVendorAdapter()
        managerFactoryBean.jpaProperties = createJpaProperties()
        return managerFactoryBean
    }

    private Properties createJpaProperties() {
        Properties properties = new Properties()
        properties.setProperty("hibernate.cache.use_second_level_cache", "true")
        properties.setProperty("hibernate.cache.region.factory_class", "org.hibernate.cache.ehcache.EhCacheRegionFactory")
        properties.setProperty("hibernate.cache.use_query_cache", "true")
        properties.setProperty("hibernate.generate_statistics", "true")
        properties.setProperty("hibernate.hbm2ddl.auto", "create)")
        return properties
    }

    private JpaVendorAdapter createHibernateJpaVendorAdapter() {
        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter()
        jpaVendorAdapter.generateDdl = true
        jpaVendorAdapter.showSql = true
        //jpaVendorAdapter.databasePlatform = org.hibernate.dialect.HSQLDialect
        jpaVendorAdapter.database = Database.HSQL
        return jpaVendorAdapter
    }

    @Bean
    @Autowired
    JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager()
        transactionManager.entityManagerFactory = entityManagerFactory
        transactionManager.jpaDialect = new HibernateJpaDialect()
        return transactionManager
    }
}
