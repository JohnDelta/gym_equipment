package com.john_deligiannis.gym_equipment.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
  
public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer { 
  
    @Override
    protected Class<?>[] getRootConfigClasses() 
    { 
        return null; 
    } 
  
    @Override
    protected Class<?>[] getServletConfigClasses() 
    {  
        return new Class[] { MVCconfig.class }; 
    } 
  
    @Override
    protected String[] getServletMappings() 
    { 
        return new String[] { "/" }; 
    } 
} 