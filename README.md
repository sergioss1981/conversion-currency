# Currency Conversion API
Este projeto é uma aplicação Java baseada no framework Quarkus para conversão de moedas. Essa API permite obter taxas de câmbio e realizar conversões de valores de uma moeda para outra, integrando com um serviço de taxa de câmbio externa.

## Pré-requisitos
- **Java 17+**
- **Maven** para gestão de dependências e build
- **Quarkus CLI** para desenvolvimento com Quarkus
- **Docker** para quem deseja criar e executar a imagem Docker do projeto.
- **ExchangeRate-API** Efetuar cadastro em https://www.exchangerate-api.com/ e gerar uma API-KEY. Após gerada, deverá ser o valor da variável de ambiente API_KEY_EXCHANGE_RATE.

## 1. Instalação

Clone o repositório:
   ```
   git clone https://github.com/sergioss1981/conversion-currency.git
   ```
Navegar até o diretório conversion-currency:
   ```
   cd conversion-currency
   ```   

### Maven
Execute o comando para rodar o projeto
   ```
   ./mvnw compile quarkus:dev
   ```
### Docker
Crie uma imagem Docker a partir do código-fonte:
   ```
   docker build -t currency-converter-api.
   ```
Crie e execute o container a partir da imagem criada no passo 1.
   ```
   docker run -p 8080:8080 currency-converter-api
   ```

## 2. Documentação e testes
Swagger e Teste da aplicação: http://localhost:8080/index/

## 3. Endpoints
A API tem os endpoints para converter a taxa e outro para listagem das conversoes feitas.

### `GET /convert/rate`
Realiza a conversão de moeda usando uma taxa específica.

### `GET /convert/rates`
Retorna uma lista de taxas de conversão para um par de moedas especificado.
