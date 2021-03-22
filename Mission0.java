import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//* prévoir les différentes version C1(blindé) C2(blindé) C3(blindé) ....*//
public class Mission0{
    //init @var global ( idée interessant de seb créer une class objet connexion)
    private static Connection cn = null;

    public static void main(String[] args){
        
        //init de données de connexion
        final String DRIVER = "com.mysql.jdbc.Driver";          //driver historique msql
        final String URL    = "jdbc:mysql://localhost:3306";    //protocole, server, port
        final String DBNAME = "missionZero";                       // nom de la BDD créer depuis phpmyadmin
        final String USER   = "root";                           
        final String PWD    = "";
 
        try{    // try catch pour la connexion vers le BDD en sql

            //init var local
            String choixValide, cpControler;

            demandeConnection(URL, DRIVER, DBNAME, USER, PWD);

            //refaire ce parti pour un controle au cas par cas, pour chaque choix donner en parametre
            // blindé au maximun son code
            if(args.length == 0){
                afficher("\n##### Erreur, pas de parametre entrer");
            }else{
                choixValide = controleChoix(args[0]);
                
                if(args.length == 1){
                    lancerModule(choixValide);                                                                                                                          
                }
                else if(args.length == 2){
                    cpControler = controleValeurCp(args[1]);
                    if(cpControler.length() == 5){
                        lancerModule(choixValide,cpControler); 
                    }                                                                                                                 
                }

                if(args.length == 3){               //si on entre 3 parametre on affiche une erreur
                    if(choixValide.equals("C") && choixValide.equals("U")){
                        afficher("\n##### Erreur, 3ème parametre manquant");
                    }else if(choixValide.equals("D") || choixValide.equals("R1")){
                        afficher("\n##### Erreur, parametre invalide");
                    }
                     
                }
                else if(args.length > 3){          //sinon si on entre + de 3 parametre alors on lance le module
                    cpControler = controleValeurCp(args[1]);
                    if(cpControler.length() == 5){
                        lancerModule(choixValide, cpControler, args[2], args[3]);
                    }                                                                                                       
                }
                
            }
             

            cn.close();                                                     //Si bug ..... SQLException


        }catch (SQLException e){
            afficher("\nProbleme de type SQL !!!");
            afficher("- SQLException..." + e.getMessage());
            afficher("- SQLState..." + e.getSQLState());
            afficher("- VendorError..." + e.getErrorCode());
            e.printStackTrace();
        
        }    

        /* A revoir plutard */
        //int codPostal ,nbHab ; //a revoir
        //codPostal = Integer.parseInt(cpControler);              
        //nbHab = Integer.parseInt(args[3]);
        
    }
    

    public static void demandeConnection(String url, String driver, String bdd, String user, String pwd){
        
        try {
            //afficher("\n///////////// Chargement du driver!");
            Class.forName(driver);                                          //Si bug ..... ClassNotFoundException
            //afficher("\n///// chargement OK");

            //afficher("\n///////////// Recherche du driver");
            cn = DriverManager.getConnection(url+"/"+bdd,user,pwd);      //Si bug ..... SQLException
            if (cn != null){
                //afficher("\n///// Load driver succes !");
            }else{
                //afficher("\n///// Load driver Erreur !");
            }
  
        } catch (ClassNotFoundException e){
            afficher("\nSouci de chargement du MYSQL Driver class!!!");
            //e.printStackTrace();
            return;
        } catch (SQLException e){
            afficher("\nProbleme de type SQL !!!");
            afficher("- SQLException..." + e.getMessage());
            afficher("- SQLState..." + e.getSQLState());
            afficher("- VendorError..." + e.getErrorCode());
            //e.printStackTrace();
        }
    }

    public static void lancerModule(String param0){//surchage de methode avec 1 para
        // ajout des autres cas afin de fournir des messages personnaliser
        switch(param0){ // faire en if avec else if et else affiche erreur tout public
            case "R":
                afficher("\n##### Appelle methode 'Liste' communes ");
                lecture();  
                break;
        }
        
    }

    public static void lancerModule(String param0,String param1){// surchage avec 2 para
        
        switch(param0){
            case "R1":
                afficher("\n##### Appelle methode 'Voir' communes ");
                decrisToi(param1);
                break;
            case "D":
                afficher("\n##### Appelle methode 'Supprimer' communes ");
                decrisToi(param1);
                break;
        }
        
    }

    public static void lancerModule(String param0,String param1,String param2,String param3){// Surchage avec 3 parametre
        
        switch(param0){
            case "C":
                afficher("\n##### Appelle methode 'Creer' communes");
                decrisToi(param1,param2,param3);
                break;
            case "U":
                afficher("\n##### Appelle methode 'Modifier' communes");
                decrisToi(param1,param2,param3);
                break;
        }
        
    }

    public static void lecture(){//sp3
       
        try{
            afficher("\n///// Lecture des data de la BDD");
            // requete SQL "SELECT" pour la lecture BDD
            String sql = "SELECT * FROM communes ";                                                         //instruction SQL qui dit "Cherche moi toute les lignes et les colonne qu'il y a dans cette table"
            //String sql = "SELECT * FROM commune WHERE nombreHabitant >= 500000";                          //permet de filtrer selon le nbreHab
            
            //afficher("Entrer un code postal !");
            //saisi = sc.nextLine()
            //String sql = "SELECT * FROM commune WHERE '"+ saisi +"' nonCommune ";                         //test avec une entrer lireclav (non fini)
    
            // envoi de requete via stmt.... objet instruction
            Statement smt = cn.createStatement();
            ResultSet rs  = smt.executeQuery(sql);
    
            // exploiter les data recues
            while(rs.next()){
                String codePost = rs.getString("codePostal");                                               //instruction qui lis en String la colonne codePostal de la table communes 
                String nomCom = rs.getString("nomCommune");                                                 // idem  "                  "              nomCommune            "
                String nbreHab = rs.getString("nbHabitants");                                               // idem  "                  "              nombreHabitant          "
                afficher("\n"+codePost + ", "+ nomCom +", "+ nbreHab+" habitant dans cette ville");
            }
        
        } catch (SQLException e){
            afficher("\nProbleme de type SQL !!!");
            afficher("- SQLException..." + e.getMessage());
            afficher("- SQLState..." + e.getSQLState());
            afficher("- VendorError..." + e.getErrorCode());
            e.printStackTrace();
       }
    
    }


    ////////////////////////////////////// Utilitaire Tools \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    public static void decrisToi(String para1, String para2, String para3){
        afficher("\nje prend le "+para1+" en 1er parametre, "+para2+" en 2eme parametre, "+para3+" en 3eme parametre");
    }

    public static void decrisToi(String para1){
        afficher("\n##### je prend le "+para1+" en 1er parametre");
    }

    public static void afficher(Object obj){
        System.out.println(obj);
    }

    public static String controleChoix(String choix){// controle la donnée entrer dans le parametre choix 
        
        if(!(choix.equals("C")) && !(choix.equals("R")) && !(choix.equals("U")) && !(choix.equals("D")) && !(choix.equals("R1"))){
            afficher("\n##### Erreur, parametre invalide");
        }
        return choix;
    }

    public static String controleValeurCp(String codPost){// controle le valeur entrer dans le paramatre codPost

        if(codPost.length() < 5 || codPost.length() > 5){
            afficher("\n##### Erreur, le code postal invalide ");
        }
        return codPost;
    }

}