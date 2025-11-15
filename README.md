 DSW API | API de Agência de Viagens

Esta é uma API RESTful desenvolvida com Spring Boot para gerenciar informações de destinos de viagem.

Como Executar o Projeto
Siga os passos abaixo para clonar o repositório e iniciar a aplicação localmente.

Pré-requisitos
Java Development Kit (JDK 17 ou superior)
Maven
Um editor de código (VS Code, IntelliJ IDEA, etc.)

Passos de Execução
Clone o Repositório:
git clone [https://github.com/jocieleciele/DSW-API.git](https://github.com/jocieleciele/DSW-API.git)
cd DSW-API

Inicie a Aplicação:
Use o Maven Wrapper na pasta raiz do projeto:
./mvnw spring-boot:run

A aplicação estará acessível em: http://localhost:8080
Como Testar a API (via cURL)
Com a aplicação rodando, você pode usar o curl no terminal para testar as principais funcionalidades. Lembre-se de que os comandos POST e PATCH precisam ser em uma única linha no CMD do Windows.

1. Listar Destinos (GET)
curl -X GET http://localhost:8080/api/destinos

2. Cadastrar Novo Destino (POST)
curl -X POST http://localhost:8080/api/destinos -H "Content-Type: application/json" -d "{\"nome\": \"Foz do Iguaçu\", \"localizacao\": \"Paraná, Brasil\", \"descricao\": \"Famosa pelas Cataratas.\"}"
O retorno deste comando fornecerá o {id} do novo destino.

3. Avaliar um Destino (PATCH)
Substitua {id} pelo ID retornado no cadastro:
curl -X PATCH http://localhost:8080/api/destinos/{id}/avaliar -H "Content-Type: application/json" -d "{\"nota\": 10}"

4. Excluir um Destino (DELETE)
Substitua {id} pelo ID do destino a ser removido:
curl -X DELETE http://localhost:8080/api/destinos/{id}
