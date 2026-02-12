🍷 Adega API
Bem-vindo ao projeto Adega API! Este é um sistema de gerenciamento de bebidas desenvolvido por Caique Godinho Santo (Caigods). O projeto foi criado com fins educacionais para consolidar conhecimentos no ecossistema Spring Boot e arquitetura REST.

A aplicação permite realizar o ciclo completo de um CRUD (Create, Read, Update, Delete) de bebidas, integrando regras de negócio personalizadas para o controle de estoque e envelhecimento.


🚀 Tecnologias e Ferramentas
Java 17+: Linguagem principal do projeto.

Spring Boot 3: Framework para construção da API.

Spring Data JPA: Abstração para persistência de dados e consultas ao banco.

Lombok: Utilizado para automatizar a criação de Getters, Setters, Construtores e o padrão Builder.

Jakarta Persistence (JPA): Mapeamento das entidades para o banco de dados.


🛠️ Regras de Negócio Implementadas
Para garantir a qualidade dos dados, o BebidaService aplica as seguintes validações:

Validação de Volume: O sistema impede o cadastro ou atualização de bebidas com volume menor ou igual a zero.

Consistência Histórica: Não é permitido registrar fabricações anteriores ao ano 1500.

Buscas Flexíveis: Implementação de buscas por nome ignorando maiúsculas/minúsculas (IgnoreCase) e contendo partes do texto (Containing).

Integridade de Dados: Uso da anotação @Transactional para garantir que operações de escrita (salvar, atualizar, deletar) sejam concluídas com sucesso ou revertidas em caso de erro.


🛤️ Endpoints Principais
Método,Endpoint,Função

POST /bebidas,Cadastra uma nova bebida no sistema.

GET /bebidas,Lista todas as bebidas cadastradas.

GET /bebidas/{id},Recupera uma bebida específica pelo seu ID.

GET /bebidas/nome?nome=...,Filtra bebidas por nome ou parte dele.

PUT /bebidas/{id},Atualiza os dados de uma bebida existente.

DELETE /bebidas/{id},Remove uma bebida da base de dados.

🏗️ Como Executar
Para rodar a aplicação localmente:

Certifique-se de ter o JDK 17 instalado.

Execute o comando no terminal: ./mvnw spring-boot:run
Acesse a API em: http://localhost:8080/bebidas.

🧪 Exemplos de JSON para Testes
Cadastrar nova Bebida (POST /bebidas)
{
  "nome": "Cachaça Envelhecida Carvalho",
  "volumeMl": 750,
  "anoFabricacao": 2018,
  "emEnvelhecimento": true
}
Atualizar Bebida (PUT /bebidas/{id})
{
  "volumeMl": 1000
}

Desenvolvido por Caique Godinho (Caigods)
Estudante de Ciência da Computação.
