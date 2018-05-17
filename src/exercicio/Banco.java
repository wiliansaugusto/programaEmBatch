
package exercicio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Locale;
import org.eclipse.persistence.jpa.jpql.parser.ResultVariable;

/**
 *
 * @author Home
 */
class Banco {
      Connection conexao ;
        public static void Conexao()throws SQLException, ClassNotFoundException{
    try {
  Class.forName("com.mysql.jdbc.Driver");
  Connection conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/exercicios","root","785412Wiza");
  System.out.println("Conectado.");

    }catch(SQLException e) {
    System.out.println(e);
    throw new RuntimeException(e);
    }
 
}
        
         public void adiciona(Cliente c1 ) throws SQLException{
             try{
        Connection conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/exercicios","root","785412Wiza");

        String sql = "INSERT INTO tb_customer_account ( cpf_cnpj, nm_customer, is_active, vl_total)"+"values(?,?,?,?)";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setString(1, c1.getCpf_cnpj());
        stmt.setString(2,c1.getNome());
        stmt.setBoolean(3,c1.getisIsActive());
        stmt.setDouble(4,c1.getSaldo());
        stmt.execute();
        System.out.println("Gravado!");
        conexao.close();
	
    }catch(NullPointerException n){
                 System.out.println("exercicio.Banco.adiciona().erro");
    }
          
}
         
         
         public double mediaFinal() throws SQLException{
            double media;
             String sql ="SELECT AVG(vl_total) FROM tb_customer_account WHERE  `id_customer` BETWEEN 1500 AND 2700 AND `vl_total` > 560;";
             Connection conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/exercicios","root","785412Wiza");
             PreparedStatement stmt = conexao.prepareStatement(sql);
             stmt.execute();
             ResultSet result = stmt.executeQuery(sql);
             
             if(result !=null && result.next()){
                     media = result.getDouble("AVG(vl_total)");
                 }else{
                 media=0;
                }
             
            media = Double.valueOf(String.format(Locale.UK, "%.2f", media));

             return media;
         
         }

         public  ArrayList<Cliente> listarClientes() throws SQLException{
             ArrayList<Cliente> c = new ArrayList();
             String sql ="SELECT  *  FROM `tb_customer_account` WHERE `id_customer` BETWEEN 1500 AND 2700 AND `vl_total` > 560 ORDER BY vl_total DESC ;";
                try{
                Connection conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/exercicios","root","785412Wiza");
                PreparedStatement stmt = conexao.prepareStatement(sql);
                stmt.execute();
                ResultSet result = stmt.executeQuery(sql);
                while(result.next()){
                    Cliente cliente = new Cliente();
                    cliente.setId_customer(result.getInt("id_customer"));
                    cliente.setCpf_cnpj(result.getString("cpf_cnpj"));
                    cliente.setNome(result.getString("nm_customer"));
                    cliente.setSaldo(result.getDouble("vl_total"));
                    cliente.setIsActive(result.getBoolean("is_active"));
                    
                    //System.out.println(cliente.getNome());
                   // System.out.println(cliente.getSaldo());

                    c.add(cliente);
                    
                }
            }catch(SQLException e){
			e.printStackTrace();
            }
             catch(NullPointerException e){
                 e.printStackTrace();
             }
                int i;
              System.out.println("Quantidade de pessoas cadastradas no sistema que tem saldo acima de R$560.00");
              System.out.println(" ID  CPF/CNPJ    NOME     SALDO   ATIVO ");  
             for(i =0; i < c.size() ;i++){
                 Cliente cliente = c.get(i);
                 System.out.println(cliente.getId_customer()+" "+cliente.getCpf_cnpj()+" "+
                         cliente.getNome()+ " " + "R$"+cliente.getSaldo()+" " +cliente.getisIsActive());
             
         }
             System.out.println("\n\n Quantidade de linhas retornado pelo busca: "+(i));
         return c;
        }
}
