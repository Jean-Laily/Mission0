public class Mission0_C1_1{
  private static String choixValide, codePostalValide;
  

    //Création du squelette
    public static void main(String[] args) { 

      /* selon ce que vaut @args on lance un module ou une erreur */ 
      if(args.length == 0){                                           // si @args vaut 0 alors fait instruction
      afficher("ERREUR: aucun paramètre est fourni !");
      }else {
        choixValide = controleChoix(args[0].toUpperCase());
        if(args.length == 1){                                         // si @args vaut 1 alors lance le module 
          moduleDeParametre(choixValide);
        }else if(args.length == 2){                                   // si @args vaut 2 alors lance le module a 2 para
          moduleDeParametre(choixValide, args[1]);
        }else if(args.length == 3){                                   // si @args vaut 3 alors fait instruction
          if(choixValide.equals("C") || choixValide.equals("U") && !(args[1].equals(" "))){
            afficher("ERREUR: Le dernier paramètre nbHabitant est manquant !");
            if(!(args[1].length() == 5)){ // on traite l'erreur du CP erroné
              codePostalValide = controleCodePostal(args[1]);
            }
          }else if(choixValide.equals("R1") || choixValide.equals("R") || choixValide.equals("D") && !(args[1].equals(" "))){
            afficher("ERREUR: Vous utilisez trop de paramètre pour ce module !");
            if(!(args[1].equals(" "))){ // on traite l'erreur du CP erroné
              codePostalValide = controleCodePostal(args[1]);
            }
          }
        }else if(args.length == 4){                                    // si @args vaut 4 alors lance le module a 3 para
          moduleDeParametre(choixValide, args[1], args[2], args[3]);
        }else{                                                        // sinon si @args plus de 4 alors fait instruction
           afficher("ERREUR: Vous avez entré trop de paramètre !");
        }
      }

    }//Fin de main

     /** 
     * M: D'aiguiller vers le module qui utlise qu'un parametre et affiche des erreurs personnalisé selon cas
     * O: @return NULL 
     * I: @param param0     = choixModule
     * 
    */
    public static void moduleDeParametre(String param0) {//prendra 1 parametre valide et 3 parametre non valide ou pas de parametre
        
      if(param0.equals("R")){
        listerCommune();
      }else if(param0.equals("C") || param0.equals("U")){
        afficher("ERREUR: Il manque 3 autre paramètres pour utiliser ce module !");
      }else if(param0.equals("D") || param0.equals("R1")){
        afficher("ERREUR: Il manque 1 autre paramètre pour utiliser ce module !");
      }
    }
    
      /** 
     * M: D'aiguiller vers les module qui utlise 2 parametre et affiche des erreurs personnalisé selon cas
     * O: @return NULL 
     * I: @param param0     = choixModule
     *    @param param1     = codePostal
    */
    public static void moduleDeParametre(String param0,String param1) {//surchage de la methode avec 2 parametre valide et 2 paramettre non valide ou pas de parametre
      
      if(param0.equals("R") && !(param1.equals(" "))){ //si la fonction R est appeler et on lui fourni un 2eme parametre on fait exucte l'instruction
        afficher("ERREUR: Cette fonction ne prend pas de paramètre !");
        if(!(param1.equals(" "))){ // Même si la fonction R ne prend pas de parametre on traite quand meme l'erreur du CP erroné
          codePostalValide = controleCodePostal(param1);
        }
      }else{
        codePostalValide = controleCodePostal(param1);   
        
        if(param0.equals("C") || param0.equals("U")){
          afficher("ERREUR: Il manque 2 autre paramètres pour utiliser ce module !");
        }else if(param0.equals("D") && codePostalValide.length() == 5){
          supprimerCommune(codePostalValide);
        }else if(param0.equals("R1") && codePostalValide.length() == 5){
          voirCommune(codePostalValide);
        }else{
          afficher("ERREUR: Veuillez verifier vos paramètres!");
        }
      }
    }

      /** 
     * M: D'aiguiller vers le module qui utlise 3 parametre et affiche des erreurs personnalisé selon cas
     * O: @return NULL 
     * I: @param param0     = choixModule
     *    @param param1     = codePostal
     *    @param param2     = nomCommune
     *    @param param3     = nbHabitant
    */
    public static void moduleDeParametre(String param0 ,String param1, String param2, String param3) {// surcharge de methode avec 2 parametre valide et 2 paramettre non valide ou pas de parametre
      
      if(param0.equals("R") || param0.equals("D") || param0.equals("R1") && !(param1.equals(" "))){ //si la fonction R est appeler et on lui fourni un 2eme parametre on fait exucte l'instruction
      afficher("ERREUR: Vous avez entrer beaucoup trop de paramètre pour utiliser ce module !");
        if(!(param1.equals(" "))){ // Même si la fonction R ne prend pas de parametre on traite quand meme l'erreur du CP erroné
          codePostalValide = controleCodePostal(param1);
        }
      }else{
        codePostalValide = controleCodePostal(param1);

        if(param0.equals("C") && codePostalValide.length() == 5){
          creerCommune(codePostalValide, param2, param3);
        }else if(param0.equals("U") && codePostalValide.length() == 5){
          modifierCommune(codePostalValide, param2, param3);
        }else{
          afficher("ERREUR: Vérifier les paramètres utilisé!");
        }
      }
    }
        
    /**
     * M: Permet la création d'une commune dans la BDD
     * O: @return NULL
     * I: @param param0     = choixModule
     *    @param param1     = codePostal
     *    @param param2     = nomCommune
     *    @param param3     = nbHabitant
      */
    public static void creerCommune(String param1, String param2, String param3) {//Correspondant au Create de CRUD
      afficher("\nVous avez créer une nouvelle commune dont le code postal est "+param1+", avec comme nom "+param2+", contenant "+param3+" habitants");
    }

    /**
     * M: Permet d'afficher la liste de toute les communes enregistrer dans la BDD
     * O: @return NULL
     * I: @param NULL 
      */
    public static void listerCommune() {//Correspondant au Read
      afficher("Voici la liste des communes demandées");

    }

     /**
     * M: Permet la modification d'une commune ciblé dans la BDD depuis son codePostal 
     * O: @return NULL
     * I: @param param1     = codePostal
     *    @param param2     = nomCommune
     *    @param param3     = nbHabitant
      */
    public static void modifierCommune(String param1, String param2, String param3) {//Correspondant au Update
      afficher("Vous avez choisi de modifier la commune "+param1+", le nouveau nom de commune sera "+param2+", le nouveau nombre d'habitant est de "+param3);
    }

     /**
     * M: Permet la suppression d'une commune ciblé dans la BDD despuis son codePostal
     * O: @return NULL
     * I: @param param1     = codePostal
      */
    public static void supprimerCommune(String param1) {//Correspondant au Delete 
      afficher("\nSuppression des données pour le code postal suivant :"+ param1 +" dans la base de donnée");
    }

     /**
     * M: Permet la lecture d'information d'une commune en particulier dans la BDD grace à son codePostal
     * O: @return NULL
     * I: @param param1     = codePostal
      */
    public static void voirCommune(String param1) {//Correspondant au R1 => Voir
      afficher("\nVoici les informations pour le code postal "+param1+" :"); 
    }
  
    /*////////////////////////////////////////////   Utilitaire Tools   \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\ */
    public static void afficher(Object obj) {
        System.out.println(obj);
    }

    /**
     * M: Permet de controler la valeur de @var codePostal, si la valeur est inf ou sup à 5 chiffre alors on affiche un msgErreur
     * O: @return codPost
     * I: @param codPost   = code postal
      */
    public static String controleCodePostal(String codPost) {//type String
      if(codPost.length() < 5 || codPost.length() > 5){
        afficher("\n##### Erreur, le code postal est incorrect, il doit comporter 5 chiffres/lettres !");
      }
      return codPost;
    }

    /**
     * M: Permet de controler la valeur de @var choix, si la valeur n'est pas egals a ""on affiche un msgErreur
     * O: @return choix
     * I: @param choix      = choixModule
      */
    public static String controleChoix(String choix) {//type String
      if(!(choix.equals("C")) && !(choix.equals("R")) && !(choix.equals("U")) && !(choix.equals("D")) && !(choix.equals("R1"))){
        afficher("\n##### Erreur, le paramètre est inexistant !");
      }
      return choix;
    }

}