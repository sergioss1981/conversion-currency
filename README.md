# Currency Conversion API
Uma API para conversão de moedas que permite calcular taxas de câmbio e realizar conversões entre diferentes moedas. Utiliza Quarkus e inclui endpoints RESTful para interação com dados de taxa de câmbio.

## Sumário
1. [Pré-requisitos](#pré-requisitos)
2. [Instalação](#instalação)
3. [Configuração](#configuração)
4. [Endpoints](#endpoints)
5. [Testes](#testes)
6. [Contribuição](#contribuição)
7. [Licença](#licença)

## Pré-requisitos
- **Java 11** ou superior
- **Maven** para gestão de dependências e build
- **Quarkus CLI** para desenvolvimento com Quarkus

## Instalação
1. Clone o repositório:
   ```bash
   xxx colocar aqui os comandos. 
   Ex:  git clone https://github.com/seu-usuario/seu-projeto.git
        cd seu-projeto
        ./mvnw compile quarkus:dev



### 5. Configuração
Inclua detalhes de configurações específicas, como arquivos `application.properties` e variáveis de ambiente:

## Configuração 
No arquivo `application.properties`, configure as propriedades da aplicação:
quarkus.datasource.jdbc.url=jdbc:h2:mem:test
quarkus.datasource.username=sa
quarkus.datasource.password=password


### 6. Endpoints

### `GET /convert/rate`
Realiza a conversão de moeda usando uma taxa específica.

- **Parâmetros**
  - `from` (String): Moeda de origem (exemplo: "USD")
  - `to` (String): Moeda de destino (exemplo: "EUR")
  - `amount` (Double): Quantidade a ser convertida

- **Resposta**
  - Status: `200 OK`
  - Exemplo de corpo de resposta:
    ```json
    {
      "fromCurrency": "USD",
      "toCurrency": "EUR",
      "rate": 0.85,
      "convertedAmount": 85.0
    }
    ```

## Testes
Para executar os testes, use o comando:
```bash
./mvnw test

```

### 8. Contribuição
Instruções para quem deseja contribuir com o projeto.
```markdown
## Contribuição
1. Faça um fork do projeto
2. Crie uma nova branch (`git checkout -b feature/nova-funcionalidade`)
3. Commit suas mudanças (`git commit -am 'Adiciona nova funcionalidade'`)
4. Envie o push para a branch (`git push origin feature/nova-funcionalidade`)
5. Crie um novo Pull Request
