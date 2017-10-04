package rs5.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import rs5.persistence.service.UserService;
import rs5.persistence.service.UserServiceImpl;

@Configuration
@EnableWebMvc
@ComponentScan("rs5")
public class WebAppConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/pages/**").addResourceLocations("/pages/");
    }

    @Bean
    public InternalResourceViewResolver setupViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/pages/");
        resolver.setSuffix(".jsp");
        resolver.setViewClass(JstlView.class);
        return resolver;
    }

    @Bean
    public Jaxb2RootElementHttpMessageConverter getXmlMessageConverter(){
        return new Jaxb2RootElementHttpMessageConverter();
    }

    @Bean
    public MappingJackson2HttpMessageConverter getJsonMessageConverter(){
        return new MappingJackson2HttpMessageConverter();
    }

//    @Bean
//    public UserService userService(){
//        return new UserServiceImpl();
//    }
}
