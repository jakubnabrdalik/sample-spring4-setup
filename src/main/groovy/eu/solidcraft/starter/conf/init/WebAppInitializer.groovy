package eu.solidcraft.starter.conf.init

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer

class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer  {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return [AppConfiguration.class]
    }

    @Override
    protected String[] getServletMappings() {
        return ["/"]
    }
}
