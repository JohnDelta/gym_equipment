package com.john_deligiannis.gym_equipment.config;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;

import com.john_deligiannis.gym_equipment.entities.*;

public class HibernateUtil {
	
	private static StandardServiceRegistry registry;
	private static SessionFactory sessionFactory;
    
	static {
        try {
        	System.out.println("Session Factory initialized");
        	
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
			metadataSources.addAnnotatedClass(Users.class);
			metadataSources.addAnnotatedClass(Products.class);
			metadataSources.addAnnotatedClass(Offers.class);
			metadataSources.addAnnotatedClass(Orders.class);
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
    
    // TEMPORARY DATABASE FILLER
    public static boolean databaseFill() {
    	
    	Users user = new Users();
    	user.setUsername("user");
    	user.setPassword("user");
    	user.setEmail("user@gmail.com");
    	user.setName("john");
    	user.setLastname("delta");
    	user.setRole(1);
    	user.setAddress("207b baker street");
    	user.setCity("london");
    	user.setPhone("+3090909090");
    	
    	Products product1 = new Products();
    	product1.setTitle("Everfit WBK-500 Πάγκος Γυμναστικής (8029975990279)");
    	product1.setDescription("Μέγιστο βάρος αντοχής (χρήστη + φορτίου): 200 Kg, Μέγιστο βάρος χρήστη: 100 kg, Μέγιστο βάρος στους ορθοστάτες: 100 kg, Μέγιστο βάρος εκτάσεων ποδιών: 50 kg, Κάθισμα: δίχρωμη ταπετσαρία με αφρώδες υλικό\r\n" + 
    			"Διαστάσεις, καθίσματος: 29 x 30 x 4 cm, Πλάτη: δίχρωμη ταπετσαρία με αφρώδες υλικό, Διαστάσεις πλάτης: 78 x 29 x 4 cm, Κλίση πλάτης ρυθμιζόμενη σε 4 επίπεδα (0-30°), Κατασκευή: σωληνωτό πλαίσιο από ενισχυμένο χάλυβα με τετραγωνική διατομή 3,8 x 3,8 x 0,15 cm, Κύλινδροι από υψηλής πυκνότητας αφρώδες υλικό, Καθαρό βάρος: 22 Kg, Διαστάσεις όταν στηθεί: 154 x 67 x 122 cm, Διαστάσεις διπλωμένο: 74 x 67 x 170 cm, Βάρος συσκευασίας: 23 Kg, Διαστάσεις συσκευασίας: 118 x 38 x 21 cm, Πιστοποιήσεις: CE EN ISO 20957-1/957-4. Οι δίσκοι βάρους, η μπάρα, το μαξιλάρι δικεφάλων και το κιτ πεταλούδας δεν περιλαμβάνονται.\r\n");
    	product1.setPrice(125);
    	product1.setQuantity(4);
    	product1.setPhoto1("images/product1/p1.jpg");
    	product1.setPhoto2("images/product2/p2.jpg");
    	
    	EntityManager em1 = sessionFactory.createEntityManager();
    	em1.getTransaction().begin();
    	em1.persist(user);
    	em1.getTransaction().commit();
    	em1.close();
    	
    	EntityManager em2 = sessionFactory.createEntityManager();
    	em2.getTransaction().begin();
    	em2.persist(product1);
    	em2.getTransaction().commit();
    	em2.close();
    	
    	return true;
    }
    
}









