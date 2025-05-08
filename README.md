# Curso Spring Boot - Dev Dojo

## Anotações além do código
- Bean é qualquer objeto que o contêiner do Spring IoC (Inversion of Control) instancia, configura e gerencia durante o ciclo de vida da aplicação.
  - Em outras palavras, é um componente “gerenciado” pelo framework, pronto para ser injetado (injected) onde for necessário.
  - Anotações que identificam a classe como um Bean: @Component, @Service, @Repository, ...
 
- Sempre versione as URLs das suas aplicações
- O nome que indica os recursos da API devem ser definidos como objetivos no plural

- O uso de threads virtuais pode ser fundamental para diminuir o tempo de resposta da API em ambientes com muitas requisições simultâneas, fazendo com que não seja necessário o aumento do nº das threads de plataforma do servidor. (Utilizar Visual VM e POSTMAN para realizar os testes, visualizando o tempo de resposta e a memória da JVM).

- Uso do @RequestParam: filtrar, ordenação, requisitar informações adicionais.
- Uso do @PathVariable: identificar um determinado recurso.

- Semântica de operações HTTP:
  - Idempotente: O resultado da requisição não muda, mesmo que ela seja chamada várias vezes. Exemplos de métodos idempotentes são GET, PUT e DELETE.
  - Não idempotente: O resultado pode mudar com chamadas repetidas. O método POST é um exemplo disso.

- Use o @JsonProperty quando precisar alterar o nome de uma propriedade Java para algo diferente no JSON, seja para garantir a conformidade com o formato de dados esperado ou para resolver conflitos de nomenclatura.

- Separar as classes de domínio das classes de requisição/reposta (Padrão DTO).
  - Alguns atributos são apenas para uso interno no sistema e não devem ser expostos aos usuários.
