import java.awt.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;


public class TP1 {
	public static void main(String[] args){
		String fichier ="Log-clients-themes.txt";
		String theme = "Theme.txt";
		String usager = "Usagers.txt";
		String matrice = "Matcice.txt";
		String produits = "Produits.txt";
		//lecture du fichier texte	
		try{
			InputStream ips=new FileInputStream(fichier); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			
			FileWriter fw1 = new FileWriter (theme);
			BufferedWriter bw1 = new BufferedWriter (fw1);
			PrintWriter fichierSortie1 = new PrintWriter (bw1);
			
			FileWriter fw2 = new FileWriter (usager);
			BufferedWriter bw2 = new BufferedWriter (fw2);
			PrintWriter fichierSortie2 = new PrintWriter (bw2);
			
			FileWriter fw3 = new FileWriter (matrice);
			BufferedWriter bw3 = new BufferedWriter (fw3);
			PrintWriter fichierSortie3 = new PrintWriter (bw3);
			
			int nbProducts = writeFile(br, fichierSortie1, fichierSortie2, fichierSortie3);
			
			ips=new FileInputStream(matrice); 
			ipsr=new InputStreamReader(ips);
			br=new BufferedReader(ipsr);
			
			FileWriter fw4 = new FileWriter (produits);
			BufferedWriter bw4 = new BufferedWriter (fw4);
			PrintWriter fichierSortie4 = new PrintWriter (bw4);
			
			writePoducts(br,fichierSortie4,nbProducts);
			
		}catch (Exception e){
			System.out.println(e.toString());
		}
	
	}
	
	private static void writePoducts(BufferedReader br, PrintWriter fichierSortie4, int nbProducts) {
		String ligne;
		int[] p = new int[nbProducts];
		
		try{
			while ((ligne=br.readLine())!=null){
					String[] parts = ligne.split(";");
					
					for(int i=0; i<nbProducts; i++){
						p[i] = p[i] + Integer.parseInt(parts[i]);
					}
				
			}	
			br.close();
			for(int i=0; i<nbProducts; i++){
				for(int j=0; j<nbProducts; j++){
					fichierSortie4.print(p[i]+p[j]+";");
				}	
				fichierSortie4.println();
			}
			fichierSortie4.close();
			
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}
	}

	public static int writeFile(BufferedReader br, PrintWriter fichierSortie1, PrintWriter fichierSortie2, PrintWriter fichierSortie3){
		String ligne;
		String sub = "";
		ArrayList<String> listUsagers = new ArrayList<String>();
		ArrayList<String> listThemes = new ArrayList<String>();
		HashMap<String,int[]> map = new HashMap<String,int[]>();
		
		try{
			while ((ligne=br.readLine())!=null){
					String[] parts = ligne.split(";");
					
					sub = parts[2];
					if(!listThemes.contains(sub)){
						listThemes.add(sub);
						fichierSortie1.println(sub);
					}	
				
					sub = parts[1];
					if(!listUsagers.contains(sub)){
						listUsagers.add(sub);
						fichierSortie2.println(sub);
					}
				
			}
			fichierSortie1.close();
			fichierSortie2.close();
			br.close();
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}
		
		for(String usager : listUsagers){
			map.put(usager, new int[listThemes.size()]);
		}
		
		try{
			InputStream ips=new FileInputStream("Log-clients-themes.txt"); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			br=new BufferedReader(ipsr);
			
			while ((ligne=br.readLine())!=null){
					String[] parts = ligne.split(";");
					String usager = parts[1];
					String theme = parts[2];
					
					int nTheme = listThemes.indexOf(theme);
					int[] t = map.get(usager);
					t[nTheme] = t[nTheme] + 1;
					map.put(usager,t);		
			}
			
			for (Entry<String, int[]> entry : map.entrySet()){
				int[] values = entry.getValue();
				for(int i=0; i<values.length; i++){
					System.out.print(values[i]+";");
					fichierSortie3.print(values[i]+";");
				}
			    System.out.println();
			    fichierSortie3.println();
			}
			fichierSortie3.close();
			br.close();
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}
		
		
		return listThemes.size();
	}

	
}
