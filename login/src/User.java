package login.src;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
/**
 * Classe User responsável por gerenciar a conexão com o banco de dados
 * e realizar operações de autenticação de usuários.
 */
public class User {
 /**
     * Método para estabelecer uma conexão com o banco de dados MySQL.
     * 
     * @return Objeto Connection caso a conexão seja bem-sucedida, ou null em caso de falha.
     */
    // Método para conectar ao banco de dados
    public Connection conectarBD(){
        Connection conexao = null;
      // Parâmetros para conexão          
        String driver = "com.mysql.cj.jdbc.Driver";// Driver JDBC
        String url="jdbc:mysql://localhost:3306/test?serverTimezone=UTC";// URL de conexão com o banco
        String user = "root";// Usuário do banco
        String password ="Duda1879@";// Senha do banco
        
        try{
             // Carrega o driver JDBC
            Class.forName(driver);
            // Estabelece a conexão
            conexao = DriverManager.getConnection(url, user, password);
        return conexao;// Retorna a conexão estabelecida
        }catch (Exception e) {
              // Exibe o erro caso a conexão falhe
            System.out.println(e);// Retorna null em caso de falha
            return null;
        }
    } 

     // Variáveis de classe para armazenar os resultados da autenticação
    /**
     * Armazena o nome do usuário autenticado.
     */
    public String nome = "";
    /**
     * Indica o resultado da autenticação do usuário.
     * True se a autenticação for bem-sucedida, false caso contrário.
     */
    public boolean result = false;
/**
     * Método para verificar se o login e a senha fornecidos correspondem
     * a um usuário válido no banco de dados.
     * 
     * @param login Login do usuário.
     * @param senha Senha do usuário.
     * @return Retorna true se o usuário for autenticado com sucesso, false caso contrário.
     */
    // Método para verificar o usuário
    public boolean verificarUsuario(String login, String senha) {
        // Consulta SQL para verificar o usuário
        String sql = "SELECT nome FROM usuarios WHERE login = '" + login + "' AND senha = '" + senha + "'";
       // Tentativa de conexão e execução da consulta
        try (Connection conn = conectarBD();// Estabelece a conexão com o banco
             Statement st = conn.createStatement();// Cria um Statement para a execução da consulta
             ResultSet rs = st.executeQuery(sql)) {// Executa a consulta e armazena o resultado

                // Verifica se há resultados na consulta
            
            // Verificando se houve resultado
            if (rs.next()) {
                result = true; // Usuário autenticado com sucesso
                nome = rs.getString("nome");// Armazena o nome do usuário autenticado
            }
        } catch (Exception e) {
             // Exibe uma mensagem caso ocorra algum erro na execução da consulta
            System.out.println("Erro ao executar a consulta: " + e.getMessage());
        }
        return result;// Retorna o resultado da autenticação
    }
}
