package com.SpringMongo.WebConfiguration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.SpringMongo.DB_Config.MongoDbConfig;
import com.SpringMongo.QuartzConfig.JobConfiguration;
import com.SpringMongo.QuartzConfig.QuartzConfiguration;


/**
 * Web project initializer class
 * 
 *
 */
public class WebAppInitializer implements WebApplicationInitializer {

    /**
     * Register servlets and listeners on startup [web.xml implementation]
     * @param servletContext
     */
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ContextLoaderListener contextLoaderListener = new ContextLoaderListener(ctx);
        servletContext.addListener(contextLoaderListener);
        ctx.register(AppConfig.class);
        ctx.register(SwaggerConfig.class);
        ctx.register(MongoDbConfig.class);
        ctx.register(QuartzConfiguration.class);
        ctx.register(JobConfiguration.class);
        ctx.setServletContext(servletContext);
        ServletRegistration.Dynamic servlet = servletContext.addServlet("dispatcher",
            new DispatcherServlet(ctx));
        servlet.setLoadOnStartup(1);
        servlet.addMapping("/");
        // For CORS Pre Filght Request
        servlet.setAsyncSupported(true);
        servlet.setInitParameter("dispatchOptionsRequest", "true");
    }

}