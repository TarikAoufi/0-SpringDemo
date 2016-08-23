package fr.aoufi.annuaire.util;

public class Lire
{
	
	private static boolean filtre = true;

	
	
	public static void Filtre ( boolean leFiltre) {
		filtre = leFiltre;
	}   
	
	
    public static void Purge() { 
    	try {
    		char car ;	
    		do {
    			car = (char) System.in.read ();
			}
    		while (car != '\n');
		} catch (java.io.IOException e)	{
			System.out.println ("Erreur de saisie");
			System.exit (0);
		}	
    }   

	
	public static char Caractere ()	{		
		char result = 0;				
		try	{
			result = (char) System.in.read ();	       
		} catch (java.io.IOException e)	{
			System.out.println ("Erreur de saisie");
			System.exit (0);
		}		
		if (filtre)	{
			Purge ();
		}
		return result;
	}

	public static char c ()	{
        return Caractere();
	}

}


