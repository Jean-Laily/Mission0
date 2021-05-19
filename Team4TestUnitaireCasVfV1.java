public class Team4TestUnitaireCasVfV1
{
	// test unitaire
	public static void main(String[] args)
	{
		String tabNoms[] = {"","Deux","","Quatre","","Six","","Huit","","Dix","","Douze","","Quartorze","","Seize","","Dix-huit","","Vingt"};
		byte tabFautes[] = {0,1,0,2,0,3,0,4,0,0,0,1,0,2,0,3,0,4,0,0};

		casVf(tabNoms, tabFautes);
	}

	/*
	procédure casVf()
	M : fabriquer la liste de joueurs selon le nombre de fautes
	O : néant
	I : tabNoms, tabFautes
	*/
	public static void casVf(String tabNoms[], byte tabFautes[])
	{
		byte i;	// itérations gérant le classement par nombre de fautes
		byte j;	// itérations permettant l'accès aux données de l'équipe par numéro de joueurs
		byte tabNbJoueursSelonFautes[] = {0,0,0,0,0,0};	// tableau contenant le nombre de joueurs ayant fait un même nombre de fautes
														// (l'indice 5 correspond au nombre de joueurs ayant fait cinq fautes, etc)
		String tabNomsJoueursSelonFautes[] = {"","","","","",""};	// tableau contenant la concaténation des noms de joueurs ayant fait un même nombre de fautes 
																	// (l'indice 5 correspond aux noms de joueurs ayant fait cinq fautes, etc)
		String ligneResultatFinal;	// chaîne de caracatère contenant une ligne du résultat final à afficher
		String faute; // chaîne de caractères permettant de gérer l'accord du mot "faute"

		// calcule le nombre de joueurs total selon le nombre de fautes, et enregistre les noms des joueurs correspondant
		for (i = 5 ; i >= 0 ; i--)
		{
			for(j = 0 ; j < tabFautes.length ; j++)
			{
				if (!tabNoms[j].equals("") && tabFautes[j] == i)
				{
					tabNbJoueursSelonFautes[i]++;
					tabNomsJoueursSelonFautes[i] += tabNoms[j] + ", ";
				}
			}
		}

		// permet l'affichage du résultat final
		for (i = 5 ; i >= 0 ; i--)
		{
			faute = (i == 0 || i == 1) ? " faute : " : " fautes : ";
			ligneResultatFinal = "- " + i + faute + tabNbJoueursSelonFautes[i] + " : " + tabNomsJoueursSelonFautes[i];

			System.out.println(ligneResultatFinal.substring(0, ligneResultatFinal.length() - 2)); // enlève les deux derniers caractères de la ligne à afficher pour se débarrasser des "," et ":" en trop
		}
	}
}