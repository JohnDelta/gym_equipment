package com.john_deligiannis.gym_equipment.config;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;

import com.john_deligiannis.gym_equipment.entities.User;

public class HibernateUtil {
	
	private static StandardServiceRegistry registry;
	private static SessionFactory sessionFactory;
    
	static {
        try {
        	System.out.println("Session initialized");
        	
			Map<String, String> settings = new HashMap<>();
			settings.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
			settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
			settings.put(Environment.URL, 
				"jdbc:mysql://localhost:3306/gym_equipment" + 
				"?useUnicode=true" + 
				"&characterEncoding=UTF8" + 
				"&sessionVariables=default_storage_engine=InnoDB" + 
				"&serverTimezone=UTC");
			settings.put(Environment.USER, "gym_equipment_user");
			settings.put(Environment.PASS, "gym_equipment_password");
			settings.put(Environment.SHOW_SQL, "true");
			settings.put(Environment.FORMAT_SQL, "true");
			settings.put(Environment.HBM2DDL_AUTO, "update");
			
			registry = new StandardServiceRegistryBuilder()
			                                    .applySettings(settings).build();
			
			MetadataSources metadataSources = new MetadataSources(registry);
			metadataSources.addAnnotatedClass(User.class);
			Metadata metadata = metadataSources.buildMetadata();
			
			sessionFactory = metadata.getSessionFactoryBuilder().build();
            
        } catch (Exception e) {
        	e.printStackTrace();
            if (registry != null) {
               StandardServiceRegistryBuilder.destroy(registry);
            }
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
    public static void shutdown() {
        if (registry != null) {
           StandardServiceRegistryBuilder.destroy(registry);
        }
     }
    
}
