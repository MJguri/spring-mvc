package spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import spring.intercepter.AuthCheckIntercepter;

@Configuration
@EnableWebMvc   // annotation-driven
public class MvcConfig extends WebMvcConfigurerAdapter{
					//  WebMvcConfigurer 인터페이스가 구현

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		// mvc:default-servlet-handler
		configurer.enable();
	}

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		// mvc:view-resolvers
		//registry.jsp().prefix("/WEB-INF/views/").suffix(".jsp");
		registry.jsp().prefix("/WEB-INF/views/");
	}
	
	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource msgSrc = new ResourceBundleMessageSource();
		
		msgSrc.setBasename("message.label");
		msgSrc.setDefaultEncoding("UTF-8");
		
		return msgSrc;
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		// mvc:view-controller
		registry.addViewController("/main").setViewName("main");
		registry.addViewController("/").setViewName("main");
	}

//	@Bean   //사용할 인터셉터 빈
//	public AuthCheckIntercepter authCheckIntercepter() {
//		return new AuthCheckIntercepter();
//	}
	@Autowired
	private AuthCheckIntercepter authCheckIntercepter;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//registry.addInterceptor(authCheckIntercepter()).addPathPatterns("/edit/**");
		registry.addInterceptor(authCheckIntercepter).addPathPatterns("/edit/**");
	}
	
	
	
	
}

