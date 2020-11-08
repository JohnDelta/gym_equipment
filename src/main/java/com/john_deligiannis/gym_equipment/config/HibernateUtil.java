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
			settings.put(Environment.SHOW_SQL, "false");
			settings.put(Environment.FORMAT_SQL, "true");
			settings.put(Environment.HBM2DDL_AUTO, "create-drop");
			
			registry = new StandardServiceRegistryBuilder()
			                                    .applySettings(settings).build();
			
			MetadataSources metadataSources = new MetadataSources(registry);
			metadataSources.addAnnotatedClass(Users.class);
			metadataSources.addAnnotatedClass(Products.class);
			metadataSources.addAnnotatedClass(Offers.class);
			metadataSources.addAnnotatedClass(Categories.class);
			metadataSources.addAnnotatedClass(Orders.class);
			metadataSources.addAnnotatedClass(OrdersItems.class);
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
    
    // TEMPORARY DATABASE FILLER - this is temporary and highly repetitive. Warning: Do not look at this!
    public static boolean databaseFill() {
    	
    	Categories category = new Categories();
    	category.setTitle(ECategories.GYM_EQUIPMENT.toString());
    	
    	Users user = new Users();
    	user.setUsername("user");
    	user.setPassword("user");
    	user.setEmail("user@gmail.com");
    	user.setName("john");
    	user.setLastname("delta");
    	user.setRole(1l);
    	user.setAddress("207b baker street");
    	user.setCity("london");
    	user.setPhone("+3090909090");
    	
    	Products product1 = new Products();
    	product1.setTitle("Everfit WBK-500 Πάγκος Γυμναστικής");
    	product1.setDescription("Μέγιστο βάρος αντοχής (χρήστη + φορτίου): 200 Kg, Μέγιστο βάρος χρήστη: 100 kg, Μέγιστο"
    			+ " βάρος στους ορθοστάτες: 100 kg, Μέγιστο βάρος εκτάσεων ποδιών: 50 kg, Κάθισμα: δίχρωμη ταπετσαρία με αφρώδες υλικό\r\n" + 
    			"Διαστάσεις, καθίσματος: 29 x 30 x 4 cm, Πλάτη: δίχρωμη ταπετσαρία με αφρώδες υλικό, Διαστάσεις πλάτης: 78 x 29 x 4 cm,"
    			+ " Κλίση πλάτης ρυθμιζόμενη σε 4 επίπεδα (0-30°), Κατασκευή: σωληνωτό πλαίσιο από ενισχυμένο χάλυβα με"
    			+ " τετραγωνική διατομή 3,8 x 3,8 x 0,15 cm, Κύλινδροι από υψηλής πυκνότητας αφρώδες υλικό, Καθαρό βάρος: 22 Kg,"
    			+ " Διαστάσεις όταν στηθεί: 154 x 67 x 122 cm, Διαστάσεις διπλωμένο: 74 x 67 x 170 cm, Βάρος συσκευασίας: 23 Kg,"
    			+ " Διαστάσεις συσκευασίας: 118 x 38 x 21 cm, Πιστοποιήσεις: CE EN ISO 20957-1/957-4. Οι δίσκοι βάρους, η μπάρα,"
    			+ " το μαξιλάρι δικεφάλων και το κιτ πεταλούδας δεν περιλαμβάνονται.\r\n");
    	product1.setPrice(125d);
    	product1.setQuantity(4l);
    	product1.setPhoto1("/images/product1/p1.jpg");
    	product1.setPhoto2("/images/product1/p2.jpg");
    	product1.setCategories(category);
    	
    	Products product2 = new Products();
    	product2.setTitle("TOORX - Πάγκος Ασκήσεων");
    	product2.setDescription("Πολυπάγκος ασκήσεων που περιλαμβάνει εκτάσεις ποδιών.Αναδιπλούμενος. Μέγιστο βάρος αντοχής (χρήστη + φορτίου):"
    			+ " 240 Kg, Μέγιστο βάρος χρήστη: 120 kg, Μέγιστο βάρος στους ορθοστάτες: 120 kg, Μέγιστο βάρος εκτάσεων ποδιών: 50 kg, Κάθισμα:"
    			+ " δίχρωμη ταπετσαρία με αφρώδες υλικό\r\n" + 
    			"Διαστάσεις, καθίσματος: 29 x 30 x 5 cm, Κλίση καθίσματος ρυθμιζόμενη σε 3 επίπεδα (0-30°), Πλάτη: δίχρωμη ταπετσαρία"
    			+ " με αφρώδες υλικό, Διαστάσεις πλάτης: 78 x 29 x 5 cm, Κλίση πλάτης ρυθμιζόμενη σε 4 επίπεδα (0-90°), Κατασκευή:"
    			+ " σωληνωτό πλαίσιο από ενισχυμένο χάλυβα με τετραγωνική διατομή 5 x 5 x 0,15 cm\r\n" + 
    			"Κύλινδροι από υψηλής πυκνότητας αφρώδες υλικό, Καθαρό βάρος: 29 Kg, Διαστάσεις όταν στηθεί: 159 x 82 x 140 cm,"
    			+ " Διαστάσεις διπλωμένο: 91 x 82 x 149 cm. Βάρος συσκευασίας: 30 Kg\r\n" + 
    			"Διαστάσεις συκευασίας: 110 x 48 x 21 cm. Πιστοποιήσεις: CE EN ISO 20957-1/957-4. Οι δίσκοι βάρους, η μπάρα"
    			+ " και το μαξιλάρι δικεφάλων δεν περιλαμβάνονται.\r\n" + 
    			"");
    	product2.setPrice(185d);
    	product2.setQuantity(4l);
    	product2.setPhoto1("/images/product2/p1.jpg");
    	product2.setPhoto2("/images/product2/p2.jpg");
    	product2.setCategories(category);
    	
    	Products product3 = new Products();
    	product3.setTitle("ΤΡΟΧΑΛΙΑ ΠΛΑΤΗΣ VIKING BG-13");
    	product3.setDescription("Ρυθμιζόμενο ύψος καθίσματος\r\n" + 
    			"Διαστάσεις καθίσματος:30(Μ)Χ21(Π)cm\r\n" + 
    			"Με πρόσθετους προσαρμογείς (\"μανίκια\") για ολυμπιακά κιλά\r\n" + 
    			"Διαστάσεις σιδηροδοκού 50Χ50Χ1,5mm\r\n" + 
    			"Μέγιστο φορτίο κιλών (Δίσκων) : 100 κιλά\r\n" + 
    			"Διαστάσεις μηχανήματος: 121(Μ)Χ61(Π)Χ193(Υ)cm\r\n" + 
    			"Διαστάσεις συσκευασίας: 192(Μ)Χ38(Π)Χ52,5(Υ)cm\r\n" + 
    			"Καθαρό βάρος: 28 κιλά\r\n" + 
    			"Βάρος συσκευασίας: 30 κιλά\r\n" + 
    			"Εγγύηση: 2 χρόνια (εφαιρούνται ταπετσαρίες και συρματόσχοινο)");
    	product3.setPrice(208d);
    	product3.setQuantity(4l);
    	product3.setPhoto1("/images/product3/p1.jpeg");
    	product3.setPhoto2("/images/product3/p2.jpeg");
    	product3.setCategories(category);
    	
    	Products product4 = new Products();
    	product4.setTitle("ΠΟΛΥΟΡΓΑΝΟ Viking Elite Gym");
    	product4.setDescription("Χαρακτηριστικά: 100 κιλά ενσωματωμένα βάρη, πλαστικοποιημένα για να μη θορυβούν και προστατευμένα"
    			+ " από μεταλλικό κλωβό. Ψηλή τροχαλία κυματοειδούς μορφής για έλξεις πλάτης. Μηχανή πιέσεων στήθους / κωπηλατικής πλάτης"
    			+ ". Υπάρχει δυνατότητα ρύθμισης της θέσης έναρξης για την πίεση ή την έλξη των λαβών. Πεταλούδα εκτάσεων,"
    			+ " με λαβές μεγάλης ελευθερίας κινήσεων, για δυναμικές εκτάσεις στήθους και ανάστροφες εκτάσεις για εκγύμναση"
    			+ " των οπίσθιων δελτοειδών και ραχιαίων μυών Ξεχωριστή τροχαλία εκγύμνασης κοιλιακών και τρικέφαλων,"
    			+ " πάνω από το ύψος της κεφαλής. Μηχανή εκτάσεων πρόσθιων μηριαίων και κάμψεων οπίσθιων μηριαίων. Χαμηλή"
    			+ " τροχαλία έλξεων με κόντρες ποδιών για εκγύμναση χεριών, πλάτης, τραπεζοειδών, μηριαίων προσαγωγών"
    			+ " – απαγωγών. Συμπεριλαμβάνονται τα ανάλογα προσαρτήματα – λαβές. Το κάθισμα ρυθμίζεται κατά ύψος"
    			+ " για την εύρεση της ιδανικής θέσης, ενώ όλα τα μαξιλάρια κατασκευάζονται από πυκνό – ορθοπεδικό υλικό για"
    			+ " την σωστή στήριξη του σώματος. Η ηλεκτροστατική βαφή του σκελετού περιορίζει τις γρατζουνιές"
    			+ " και την οξείδωση, ενώ το βιομηχανικό πολυπροπυλαίνιο που περιβάλλει τα συρματόσχοινα εξασφαλίζει"
    			+ " τη καλή ροή και την αντοχή στο χρόνο. Μέγιστο βάρος ασκούμενου: 150 κιλά Διαστάσεις"
    			+ " μηχανήματος Μ/Π/Υ : 195/162/210 εκ. Βάρος μηχανήματος : 165 κιλά. Διαστάσεις συσκευασίας"
    			+ " : 1η κούτα 203/42/21, 2η κούτα 113/104/15\r\n");
    	product4.setPrice(617d);
    	product4.setQuantity(4l);
    	product4.setPhoto1("/images/product4/p1.jpeg");
    	product4.setPhoto2("/images/product4/p2.jpg");
    	product4.setCategories(category);
    	
    	Products product5 = new Products();
    	product5.setTitle("Πολυόργανο γυμναστικής από την ENERGETICS");
    	product5.setDescription("Με το πολυόργανο MG 10 έχετε την δυνατότητα να κάνετε πληθώρα ασκήσεων όπως πεκ-ντεκ,"
    			+ " υψηλή τροχαλία πλάτης, χαμηλή τροχαλία για ασκήσεις χεριών και ποδιών, εκτάσεις ποδιών,"
    			+ " πιέσεις στήθους, εκτάσεις προσαγωγών/απαγωγών, εκτάσεις ώμων, κωπηλατική, έλξεις δικέφαλων,"
    			+ " τρικέφαλων κ.α. Xαρακτηριστικά : Βάρος κορμού : 70 kg. Διαστάσεις : 149.5 x 111 x 203 cm."
    			+ " Μέγιστο βάρος χρήστη : 115 kg. Oδηγίες χρήσης : Περιλαμβάνονται\r\n");
    	product5.setPrice(449d);
    	product5.setQuantity(4l);
    	product5.setPhoto1("/images/product5/p1.jpeg");
    	product5.setPhoto2("/images/product5/p2.jpg");
    	product5.setCategories(category);
    	
    	Products product6 = new Products();
    	product6.setTitle("Toorx MSX-70 100kg Πολυόργανο");
    	product6.setDescription("Διαστάσεις καθίσματος: 37 x 33 x 6.5 cm (πτυσσόμενο). Ρύθμιση καθίσματος κάθετα σε 2 επίπεδα."
    			+ " Διαστάσεις πλάτης: 68 x 33 x 6,5 cm. Ρύθμιση πλάτης κάθετα σε 6 επίπεδα. Ρύθμιση επέκτασης ποδιών οριζόντια"
    			+ " σε 3 επίπεδα. Διπλή λειτουργία πεκ ντεκ και κωπηλατικής. Αφρώδες υλικό υψηλής πυκνότητας από οικολογικό δέρμα."
    			+ " Σύστημα με τροχαλίες και ρουλεμάν\r\n" + 
    			"Χαλύβδινα καλώδια με επικάλυψη πολυουρεθάνης και αντοχή σε εφελκυσμό μέχρι 2.000 kg. Χαλύβδινο πλαίσιο με"
    			+ " ορθογώνια διατομή 50x70 mm, ανθεκτικό στις γρατσουνιές. Στοίβα κιλών: 102 kg. Μέγιστο βάρος χρήστη:"
    			+ " 135 kg. Βάρος προϊόντος: 170 kg. Διαστάσεις όταν στηθεί (ΜxΠxΥ): 146 x 92 x 213,5 cm\r\n" + 
    			"Διαστάσεις αναδίπλωσης (ΜxΠxΥ): 122,5 x 92 x 213,5 cm. Βάρος συσκευασίας: 185 kg."
    			+ " Διαστάσεις συσκευασίας (6 πακέτα): 194 x 52 x 19 cm + 53 x 44 x 18,5 cm + 180 x 28 x 22,5 cm + 49,5"
    			+ " x 29 x 19,5 cm + 49,5 x 29 x 19,5 cm + 49,5 x 29 x 19,5 cm.\r\n" + 
    			"Μέγιστο φορτίο ανά άσκηση: Πιέσεις στήθους: 118 kg. Πεκ ντεκ: 43 x 2 kg. Τροχαλία: 100 kg. Χαμηλή κωπηλατική:"
    			+ " 100 kg. Πιέσεις πλάτης: 95 kg\r\n");
    	product6.setPrice(784d);
    	product6.setQuantity(4l);
    	product6.setPhoto1("/images/product6/p1.jpeg");
    	product6.setPhoto2("/images/product6/p2.jpeg");
    	product6.setCategories(category);
    	
    	Products product7 = new Products();
    	product7.setTitle("VIKING F-8000 Multi Function Smith Machine");
    	product7.setDescription("Συνδυασμός functional πολυοργάνου και Smith μηχανής οικιακής/ημιεπαγγελματικής χρήσης σε πολύ προσιτή τιμή."
    			+ " Εκτελεί πλήθος ασκήσεων και περιλαμβάνει πάγκο με μαξιλάρι δικεφάλων. Ισχυρός σκελετός για μεγάλη σταθερότητα"
    			+ " και ασφάλεια Ψηλές έλξεις (αυτόνομη διπλή τροχαλία πλάτης). Η κάθε τροχαλία μπορεί να λειτουργήσει"
    			+ " ανεξάρτητα και μπορεί να περιστραφεί 270 μοίρες όταν την τραβήξει ο χρήστης Χαμηλές έλξεις Πεταλούδα"
    			+ " (pec-dec) Πιέσεις στήθους Μηχανισμό για την εκγύμναση των ποδιών (τετρακέφαλων και δικέφαλων μηριαίων)"
    			+ " Κάμψεις δικεφάλων Smith μηχανή για απόλυτη ασφάλεια όταν γυμνάζεστε μόνοι σας Πάγκος ίσιος/επικλινής"
    			+ " με μαξιλάρι δικεφάλων. Δέχεται δίσκους Φ28 και Φ50 (ολυμπιακού τύπου) Διαστάσεις: Μ155xΠ177xΥ220"
    			+ " cm Διαστάσεις πάγκου: Μ175xΠ45 cm Διαστάσεις συσκευασίας 3 τεμ: 1/3: 210x56x21 cm, 2/3: 139x45x17"
    			+ " cm, 3/3:186x23x7 cm Βάρος με συσκευασία: 1/3: 75 κιλά, 2/3: 33 κιλά, 3/3: 22 κιλά Συνολικό"
    			+ " Καθαρό βάρος: 122 κιλά Συνολικό βάρος με συσκευασία: 130 κιλά Εγγύηση: 2 έτη (εξαιρούνται"
    			+ " συρματόσχοινα και ταπετσαρίες) Υπάρχει η επιλογή μετατροπής του μηχανισμού Smith, με"
    			+ " 4 παλινδρομικά ρουλεμάν επαγγ/κού τύπου, για άψογη επαγγ/κή ροή (επιπλέον κόστος 150,00€)\r\n");
    	product7.setPrice(665d);
    	product7.setQuantity(4l);
    	product7.setPhoto1("/images/product7/p1.jpeg");
    	product7.setPhoto2("/images/product7/p2.jpg");
    	product7.setCategories(category);
    	
    	Products product8 = new Products();
    	product8.setTitle("Πάγκος άρσης βαρών Amila 44754");
    	product8.setDescription("Πάγκος άρσης βαρών Amila με ορθοστάτες και ρυθμιζόμενη πλάτη. Περιγραφή Προϊόντος"
    			+ " : Πλάτη: ρυθμιζόμενη, Πάγκος: Άρσης βαρών με ορθοστάτες, Σωλήνας: Οβάλ 76?1,5mm, Μέγιστο βάρος χρήστη:"
    			+ " 150kg, Διαστάσεις: 183x106x115~147cm, Βάρος: 36kg\r\n");
    	product8.setPrice(223.50d);
    	product8.setQuantity(4l);
    	product8.setPhoto1("/images/product8/p1.jpg");
    	product8.setPhoto2("/images/product8/p2.jpg");
    	product8.setCategories(category);
    	
    	Products product9 = new Products();
    	product9.setTitle("ΠΟΛΥΧΡΗΣΤΙΚΟ ΠΟΛΥΟΡΓΑΝΟ (ΗΡΑΚΛΗΣ) JX?DS926 ΤΗΣ FORCE USA");
    	product9.setDescription("Το βαρέως τύπου Πολυμηχάνημα JX-DS926 είναι εργονομικά σχεδιασμένο για να παρέχει τη δυνατότητα"
    			+ " ταυτόχρονης άσκησης έως και 3 ατόμων! Η ισχυρή δομή του το καθιστά ιδανικό για οικιακή χρήση υψηλών"
    			+ " απαιτήσεων αλλά και για χρήση σε studios, συλλόγους ή ξενοδοχεία.\r\n");
    	product9.setPrice(2000d);
    	product9.setQuantity(4l);
    	product9.setPhoto1("/images/product9/p1.jpeg");
    	product9.setPhoto2("/images/product9/p2.jpeg");
    	product9.setCategories(category);
    	
    	Products product10 = new Products();
    	product10.setTitle("ΠΟΛΥΟΡΓΑΝΟ PEGASUS HG 950");
    	product10.setDescription("Το Pegasus HG-950 είναι ένα πολυόργανο εξαιρετικής ποιότητας και ανθεκτικότητας για"
    			+ " το σπίτι με πληθώρα ασκήσεων εξαιρετικό σχεδιασμό πλάκες συνολικού βάρους 70 κιλών και ρυθμιζόμενο"
    			+ " κάθισμα. Το πολυόργανο Pegasus HG-950 είναι ιδανικό για χρήστες που ζητούν το κάτι παραπάνω στην"
    			+ " άσκησή τους στο σπίτι. Προσφέρει σημαντική ποικιλία ασκήσεων όπως pec deck τροχαλία πλάτης κωπηλασία"
    			+ " πιέσεις στήθους κάμψεις δικεφάλων και τρικεφάλων και εκγύμναση προσαγωγών απαγωγών. Διαθέτει"
    			+ " επίσης ενσωματωμένη λειτουργία εκτάσεων ποδιών ενώ η στιβαρή κατασκευή και το ρυθμιζόμενο κάθισμα"
    			+ " εγγυώνται σταθερότητα και ασφάλεια κατά τη διάρκεια της προπόνησής σας. \r\n" + 
    			"ΧΑΡΑΚΤΗΡΙΣΤΙΚΑ - Βάρη 70 κιλών (σε πλάκες)\r\n" + 
    			"- Στιβαρή κατασκευή\r\n" + 
    			"- Με συρματόσχοινα αεροπορικού τύπου και δύναμη έλξης έως 900 κιλά\r\n" + 
    			"- Αναλογία επιβάρυνσης αντίστασης βάρους 2 προς 1\r\n" + 
    			"- Σφραγισμένες τροχαλίες με ρουλεμάν\r\n" + 
    			"- Ενδεικτικές ασκήσεις: Pec deck τροχαλία πλάτης κοιλιακοί πιέσεις στήθους εκτάσεις και κάμψεις ποδιών"
    			+ " κωπηλασία απαγωγοί/προσαγωγοί με ιμάντα δικέφαλοι/τετρακέφαλοι χεριών με περιστρεφόμενη μπάρα\r\n" + 
    			"- Δυνατότητα ρύθμισης καθίσματος καθ' ύψος\r\n" + 
    			"- Πλαστικοποιημένα βάρη\r\n" + 
    			"- Μέγιστο βάρος ασκούμενου: 130 κιλά\r\n" + 
    			"- Διαστάσεις (Συναρμολογημένο): 168 (Μ) x 100 (Π) x 210 (Υ) εκατοστά");
    	product10.setPrice(399d);
    	product10.setQuantity(4l);
    	product10.setPhoto1("/images/product10/p1.jpg");
    	product10.setPhoto2("/images/product10/p2.jpeg");
    	product10.setCategories(category);
    	
    	Offers offer1 = new Offers();
    	offer1.setProducts(product1);
    	offer1.setPrice(product1.getPrice() - 20);
    	
    	Offers offer2 = new Offers();
    	offer2.setProducts(product2);
    	offer2.setPrice(product2.getPrice() - 12);
    	
    	Offers offer3 = new Offers();
    	offer3.setProducts(product5);
    	offer3.setPrice(product5.getPrice() - 56);
    	
    	EntityManager em = sessionFactory.createEntityManager();
    	em.getTransaction().begin();
    	em.persist(category);
    	em.getTransaction().commit();
    	em.close();
    	
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
    	
    	EntityManager em3 = sessionFactory.createEntityManager();
    	em3.getTransaction().begin();
    	em3.persist(product2);
    	em3.getTransaction().commit();
    	em3.close();
    	
    	EntityManager em4 = sessionFactory.createEntityManager();
    	em4.getTransaction().begin();
    	em4.persist(product3);
    	em4.getTransaction().commit();
    	em4.close();
    	
    	EntityManager em5 = sessionFactory.createEntityManager();
    	em5.getTransaction().begin();
    	em5.persist(product4);
    	em5.getTransaction().commit();
    	em5.close();
    	
    	EntityManager em6 = sessionFactory.createEntityManager();
    	em6.getTransaction().begin();
    	em6.persist(product5);
    	em6.getTransaction().commit();
    	em6.close();
    	
    	EntityManager em7 = sessionFactory.createEntityManager();
    	em7.getTransaction().begin();
    	em7.persist(product6);
    	em7.getTransaction().commit();
    	em7.close();
    	
    	EntityManager em8 = sessionFactory.createEntityManager();
    	em8.getTransaction().begin();
    	em8.persist(product7);
    	em8.getTransaction().commit();
    	em8.close();
    	
    	EntityManager em9 = sessionFactory.createEntityManager();
    	em9.getTransaction().begin();
    	em9.persist(product8);
    	em9.getTransaction().commit();
    	em9.close();
    	
    	EntityManager em10 = sessionFactory.createEntityManager();
    	em10.getTransaction().begin();
    	em10.persist(product9);
    	em10.getTransaction().commit();
    	em10.close();
    	
    	EntityManager em11 = sessionFactory.createEntityManager();
    	em11.getTransaction().begin();
    	em11.persist(product10);
    	em11.getTransaction().commit();
    	em11.close();
    	
    	EntityManager em12 = sessionFactory.createEntityManager();
    	em12.getTransaction().begin();
    	em12.persist(offer1);
    	em12.getTransaction().commit();
    	em12.close();
    	
    	EntityManager em13 = sessionFactory.createEntityManager();
    	em13.getTransaction().begin();
    	em13.persist(offer2);
    	em13.getTransaction().commit();
    	em13.close();
    	
    	EntityManager em14 = sessionFactory.createEntityManager();
    	em14.getTransaction().begin();
    	em14.persist(offer3);
    	em14.getTransaction().commit();
    	em14.close();
    	
    	return true;
    }
    
}









