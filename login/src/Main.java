package login.src;
public class Main {
    public static void main(String[] args) {
        User user = new User();
        boolean autenticado = user.verificarUsuario("joao", "1234");
        if (autenticado) {
            System.out.println("Usuário autenticado com sucesso! Nome: " + user.nome);
        } else {
            System.out.println("Falha na autenticação.");
        }
    }
}



