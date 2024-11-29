package login.src;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class User {

    // Método para conectar ao banco de dados
    public Connection conectarBD(){
        Connection conexao = null;
                
        String driver = "com.mysql.cj.jdbc.Driver";
        String url="jdbc:mysql://localhost:3306/test?serverTimezone=UTC";
        String user = "root";
        String password ="Duda1879@";
        
        try{
            Class.forName(driver);
            conexao = DriverManager.getConnection(url, user, password);
        return conexao;
        }catch (Exception e) {
            System.out.println(e);
            return null;
        }
    } 

    // Variáveis de classe
    public String nome = "";
    public boolean result = false;

    // Método para verificar o usuário
    public boolean verificarUsuario(String login, String senha) {
        String sql = "SELECT nome FROM usuarios WHERE login = '" + login + "' AND senha = '" + senha + "'";
        // Estabelecendo conexão
        try (Connection conn = conectarBD();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            
            // Verificando se houve resultado
            if (rs.next()) {
                result = true;
                nome = rs.getString("nome");
            }
        } catch (Exception e) {
            System.out.println("Erro ao executar a consulta: " + e.getMessage());
        }
        return result;
    }
}
