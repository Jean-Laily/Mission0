public class Commune{
    private String codePostal;
    private String nomCommune;
    private String nbreHabitants;

    public Commune(){
        this.codePostal = null;
        this.nomCommune = null;
        this.nbreHabitants = null;
    }

    

    public Commune(String codPost, String nomCom, String nbHab){
        this.codePostal = codPost;
        this.nomCommune = nomCom;
        this.nbreHabitants = nbHab; 
    }


    //// GETTER
    public String getCodePostal(){
        return codePostal;
    }

    public String getNomCommune(){
        return  nomCommune;
    }

    public String getNbreHabitants(){
        return nbreHabitants;
    }


    //// SETTER

    public void setCodePostal(String codPost){
        codePostal = codPost;
    }
    public void setNomCommune(String nomCom){
        nomCommune = nomCom;
    }
    public void setNbreHabitants(String nbHab){
        nbreHabitants = nbHab;
    }


    public void decrisToi(){
        System.out.println("Voici le code postal "+this.codePostal+", le nom de commune "+nomCommune+" et le nombre d'habitant "+nbreHabitants); // reste a modifier
    }






}