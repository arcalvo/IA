package streams;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author arlettecalvo
 */
public class Streams {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
              
        
        //------------------ palabra que se desea procesar -----------------------
        System.out.print("Ingrese la palabra que desea buscar: "); 
        Scanner dataEnter = new Scanner(System.in);
        String word = dataEnter.nextLine();
        
        //----------- variable que contiene los elementos del archivo -------------
        ArrayList<String> data = new ArrayList<>();
        
        //------------------- procesar los datos del archivo ----------------------
        File archivo = new File ("words.txt");
        FileReader fr = new FileReader (archivo);
        BufferedReader br = new BufferedReader(fr);
        String newData;

        while (Optional.ofNullable(newData = br.readLine()).isPresent()){
            data.add(newData);
        }
        //----------------------- cerrar el archivo -------------------------------
        br.close();
        
        
        long runCountLetter = data.stream()
            .filter(s -> {
                int lengthWord = word.length();
                int cont = 0;
                boolean resultData = false;
                while(lengthWord > 0){
                    String wordValue = word.substring(cont, cont+1);
                    resultData = s.matches("(.*)"+wordValue+"(.*)");
                    lengthWord--;
                    cont++;
                }                
                return resultData; 
            })
            .count();  
        
        System.out.println("La cantidad de palabras que contienen las letras de la palabra buscada son: " + runCountLetter);        
        
        
        //------- buscar el numero de palabras que tienen la palabra buscada ------
        long runCount = data.stream()
            .filter(s -> {
                boolean resultData = s.matches("(.*)"+word+"(.*)");
                return resultData;  
            })
            .count();
        
        System.out.println("La cantidad de palabras que contienen la palabra buscada son: " + runCount); 
        
        
        //----------- buscar la cantidad de palabras igual a la buscada -----------
        /*long runCountSame = data.stream()
            .filter(line -> word.equals(line)) 
            .count(); 
        
        System.out.println("La cantidad de palabras igual a la palabra buscada son: " + runCountSame);*/
        
        
        //------------------------ busqueda de vocales ----------------------------
        
        long runCountVowals = data.stream()
            .filter(s -> {
                int lengthWord = word.length();
                int cont = 0;
                boolean resultData = false;
                while(lengthWord > 0){
                    String wordValue = word.substring(cont, cont+1);
                    switch (wordValue) {
                        case "a":resultData = s.matches("(.*)"+wordValue+"(.*)");break; 
                        case "e":resultData = s.matches("(.*)"+wordValue+"(.*)");break; 
                        case "i":resultData = s.matches("(.*)"+wordValue+"(.*)");break; 
                        case "o":resultData = s.matches("(.*)"+wordValue+"(.*)");break; 
                        case "u":resultData = s.matches("(.*)"+wordValue+"(.*)");break;  
                    } 
                    lengthWord--;
                    cont++;
                }                
                return resultData; 
            })
            .count();  
        
        System.out.println("La cantidad de palabras que contienen la misma cantidad de vocales de la palabra buscada son: " + runCountVowals);
    }
}
