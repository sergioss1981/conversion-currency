# Currency Conversion API
Este projeto é uma aplicação Java baseada no framework Quarkus para conversão de moedas. Essa API permite obter taxas de câmbio e realizar conversões de valores de uma moeda para outra, integrando com um serviço de taxa de câmbio externa.

## Pré-requisitos
- **Java 11** ou superior
- **Maven** para gestão de dependências e build
- **Quarkus CLI** para desenvolvimento com Quarkus
- **Docker** para quem deseja criar e executar a imagem Docker do projeto.
-**ExchangeRate-API** Cadastro e API-KEY no serviço https://www.exchangerate-api.com/

## 1. Instalação
1. Clone o repositório:
   ```bash
   git clone https://github.com/sergioss1981/conversion-currency.git
   cd conversion-currency
   ./mvnw compile quarkus:dev

## 2. Documentação e testes
Swagger e Teste da aplicação: http://localhost:8080/index/

## 3. Endpoints
A API tem os endpoints para converter a taxa e outro para listagem das conversoes feitas.

### `GET /convert/rate`
Realiza a conversão de moeda usando uma taxa específica.

### `GET /convert/rates`
Retorna uma lista de taxas de conversão para um par de moedas especificado.

## 4. Variáveis de Ambiente
Deverá ser definida uma API-KEY em https://www.exchangerate-api.com/ para consumo de API externa.
Então a variável de ambiente API_KEY_EXCHANGE_RATE deverá receber a chave gerada.
