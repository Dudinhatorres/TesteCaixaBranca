package login.src;
/**
 * Classe principal responsável por executar a aplicação.
 * 
 * Esta classe utiliza a classe {@code User} para realizar a verificação de
 * autenticação de um usuário, fornecendo login e senha diretamente no código.
 */
public class Main {
      /**
     * Método principal que inicia a aplicação.
     * 
     * @param args Argumentos da linha de comando (não utilizados neste programa).
     */
    public static void main(String[] args) {
         // Cria uma instância da classe User para autenticação
        User user = new User();
         // Realiza a verificação do usuário com login e senha fixos
        boolean autenticado = user.verificarUsuario("joao", "1234");
         // Exibe o resultado da autenticação no console
        if (autenticado) {
            System.out.println("Usuário autenticado com sucesso! Nome: " + user.nome);
        } else {
            System.out.println("Falha na autenticação.");
        }
    }
}



