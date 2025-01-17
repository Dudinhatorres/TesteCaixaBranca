# Teste Caixa Branca
# Análise do Código - Classe User

Este repositório contém o código da classe `User` em Java, utilizado para verificar usuários em um banco de dados MySQL. A análise deste código identificou problemas e melhorias que podem ser implementadas para torná-lo mais seguro e eficiente.

---

## Estrutura do Código

O código realiza as seguintes operações principais:
1. **Conexão com o Banco de Dados**:
   - O método `conectarBD()` estabelece a conexão com o banco de dados MySQL.
   - As credenciais do banco (usuário e senha) estão embutidas na string de conexão.

2. **Verificação de Usuário**:
   - O método `verificarUsuario()` executa uma consulta SQL para verificar se um usuário com o login e senha fornecidos existe na tabela `usuarios`.
   - Caso o usuário seja encontrado, a variável `nome` é preenchida com o valor retornado pelo banco.

---

## Erros Identificados e Soluções

### 1. **Exposição de Credenciais**
- **Problema**: As credenciais do banco de dados (usuário e senha) estão expostas diretamente no código.
- **Solução**:
  - Utilize variáveis de ambiente para armazenar as credenciais e carregue-as no código usando bibliotecas como `System.getenv()`.

### 2. **Vulnerabilidade a SQL Injection**
- **Problema**: A consulta SQL é construída concatenando diretamente os valores de `login` e `senha`, deixando o sistema vulnerável a ataques de SQL Injection.
- **Solução**:
  - Substitua o uso de `Statement` por **PreparedStatement**, que evita a concatenação direta e protege contra SQL Injection.
  - Exemplo:
    ```java
    String sql = "SELECT nome FROM usuarios WHERE login = ? AND senha = ?";
    PreparedStatement ps = conn.prepareStatement(sql);
    ps.setString(1, login);
    ps.setString(2, senha);
    ResultSet rs = ps.executeQuery();
    ```

### 3. **Falta de Logs**
- **Problema**: Os blocos `catch` não fornecem informações sobre as exceções capturadas, dificultando a identificação de problemas.
- **Solução**:
  - Adicione logs para registrar as exceções. Exemplo:
    ```java
    catch (Exception e) {
        e.printStackTrace();
    }
    ```

### 4. **Recursos Não Fechados**
- **Problema**: A conexão com o banco de dados, o `Statement` e o `ResultSet` não são fechados após o uso, o que pode causar vazamento de recursos.
- **Solução**:
  - Utilize `try-with-resources` para garantir que os recursos sejam fechados automaticamente.
  - Exemplo:
    ```java
    try (Connection conn = conectarBD();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        // Código
    } catch (Exception e) {
        e.printStackTrace();
    }
    ```

---

## Melhorias Sugeridas

1. **Centralizar a Conexão com o Banco**:
   - Crie uma classe separada para gerenciar conexões, centralizando o código e facilitando a manutenção.

2. **Implementar Validações**:
   - Valide os campos de entrada (`login` e `senha`) antes de enviar a consulta para o banco.

3. **Uso de Variáveis de Ambiente**:
   - Evite deixar informações sensíveis diretamente no código.

4. **Adicionar Tratamento de Exceções Personalizado**:
   - Implemente mensagens claras para diferentes cenários de erro.

---

## Como Executar o Código

1. Configure um banco de dados MySQL com uma tabela chamada `usuarios`.
   - Exemplo da tabela:
     ```sql
     CREATE TABLE usuarios (
         id INT AUTO_INCREMENT PRIMARY KEY,
         login VARCHAR(50) NOT NULL,
         senha VARCHAR(50) NOT NULL,
         nome VARCHAR(100)
     );
     ```

2. Atualize a string de conexão no método `conectarBD()` para corresponder ao seu ambiente.

3. Compile e execute o código:
   ```bash
   javac User.java
   java User
## Complexidade Ciclomática

A complexidade ciclomática do método `verificarUsuario` foi calculada como **3**, com base nos seguintes caminhos:

1. Conexão bem-sucedida → Consulta executada → `rs.next() == true`.
2. Conexão bem-sucedida → Consulta executada → `rs.next() == false`.
3. Conexão falhou.
4. Erro durante a execução da consulta.

### Caminhos Identificados

- **Caminho 1:** Conexão bem-sucedida → Executa a consulta → `rs.next() == true`.
- **Caminho 2:** Conexão bem-sucedida → Executa a consulta → `rs.next() == false`.
- **Caminho 3:** Conexão falhou.
- **Caminho 4:** Erro no bloco `try-catch` durante a execução.
![Descrição do erro]("C:\Users\55159\OneDrive - Centro Universitário Facens\TesteCaixaBranca\Captura de tela 2024-11-27 210741.png")
