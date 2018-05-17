package exercicio;

import java.math.RoundingMode;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Random;
public class pricipal {
    
    
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        
    
    Banco con = new Banco();
    Cliente c = new  Cliente();
     
       
       
             
   
    /*
    * Criando um for para inserir as tuplas no banco de dados
    
    for(int i=0;i<=2700;i++){
     double start = 200.00;
     double end = 600.00;
     double random = new Random().nextDouble(); 
     double result = start + (random * (end - start));
     result = Double.valueOf(String.format(Locale.UK, "%.2f", result));
    c.setCpf_cnpj("30986293806");
    c.setNome("Test"+i);
    c.setIsActive(true);
    c.setSaldo(result);
    con.adiciona(c);
        System.out.println("numero de tupla add" +i);
    }
    */  
        
        System.out.println("a media final do saldo dos Clientes : "+ con.mediaFinal()+"\n\n");
        con.listarClientes();
}
    
    
    }

