package com.niit.configuration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.niit.collaborationconfig.DBConfig;
import com.niit.collaborationconfig.WebSocketConfiguration;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[]{DBConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[]{WebAppConfig.class,WebSocketConfiguration.class};	
	}

	@Override
	protected String[] getServletMappings() {
		return new String[]{"/"}; 
	}

}
