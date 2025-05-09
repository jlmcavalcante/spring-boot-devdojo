# Curso Spring Boot - Dev Dojo

## Anotações além do código
- Bean é qualquer objeto que o contêiner do Spring IoC (Inversion of Control) instancia, configura e gerencia durante o ciclo de vida da aplicação.
  - Em outras palavras, é um componente “gerenciado” pelo framework, pronto para ser injetado (injected) onde for necessário.
  - Anotações que identificam a classe como um Bean: @Component, @Service, @Repository, ...
 
- Versionar URLs (ex.: /api/v1/produtos) é a abordagem mais comum e recomendada quando você precisa garantir que clientes não sofram impactos ao migrar para novas funcionalidades.
- O nome que indica os recursos da API devem ser definidos como substantivos no plural e em letras minúsculas.
- Utilizar recursos aninhados na URL para indicar relacionamentos (ex.: /projects/{projId}/tasks).

- O uso de threads virtuais pode ser fundamental para diminuir o tempo de resposta da API em ambientes com muitas requisições simultâneas, fazendo com que não seja necessário o aumento do nº das threads de plataforma do servidor. (Utilizar Visual VM e POSTMAN para realizar os testes, visualizando o tempo de resposta e a memória da JVM).

- Uso do @RequestParam: filtrar, ordenação, requisitar informações adicionais.
- Uso do @PathVariable: identificar um determinado recurso.

- Semântica de operações HTTP:
  - Idempotente: O resultado da requisição não muda, mesmo que ela seja chamada várias vezes. Exemplos de métodos idempotentes são GET, PUT e DELETE.
  - Não idempotente: O resultado pode mudar com chamadas repetidas. O método POST é um exemplo disso.

- Use o @JsonProperty quando precisar alterar o nome de uma propriedade Java para algo diferente no JSON, seja para garantir a conformidade com o formato de dados esperado ou para resolver conflitos de nomenclatura.

- Separar as classes de domínio das classes de requisição/reposta (Padrão DTO).
  - Alguns atributos são apenas para uso interno no sistema e não devem ser expostos aos usuários.
  - Mudanças no domínio não influencia o contrato com os clientes (ex.: a adição de um novo atributo numa entidade, não deve mudar a forma da requisição diretamente).

- Documento que define o padrão da WEB: https://datatracker.ietf.org/doc/html/rfc7231.

- PUT x PATCH:
  - @PutMapping: Usado quando você quer atualizar todo o recurso. Quando você usa PUT, espera-se que o recurso seja totalmente substituído pela nova versão que está sendo enviada na requisição.
  - @PatchMapping: Usado quando você quer atualizar somente algumas partes do recurso, sem substituir todo o objeto. O corpo da requisição normalmente contém apenas os campos que precisam ser modificados.

- O padrão MVC (no spring) organiza o código em:
  - Controller → Recebe e responde requisições.
  - Service → Executa regras de negócio.
  - Repository → Acessa o banco de dados.
  - Model → Representa os dados da aplicação.
  - A View, em APIs REST, é substituída por JSON/XML e geralmente consumida por um front-end.
