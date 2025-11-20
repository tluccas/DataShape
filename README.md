# DataShape

![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![MySQL](https://img.shields.io/badge/mysql-4479A1.svg?style=for-the-badge&logo=mysql&logoColor=white)
![Hibernate](https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=Hibernate&logoColor=white)

## Descrição do Projeto

DataShape é uma aplicação Java Spring Boot desenvolvida para processar e analisar dados abertos do governo federal. O projeto implementa um sistema de ETL (Extract, Transform, Load) que normaliza dados de vendas de livros, tratando inconsistências e organizando informações em um banco de dados relacional estruturado.

## Base de Dados Utilizada

- **Fonte**: [dados.gov.br](https://dados.gov.br)
- **Dataset**: Dados Abertos CPE - Distribuição e vendas Loja do livro 2024
- **URL**: https://dados.gov.br/dados/conjuntos-dados/dados-abertos-cpe-distribuicao-e-vendas-loja-do-livro

## Funcionalidades Implementadas

### ✅ Tratamento de Dados
- **Normalização de títulos**: Remove acentos, padroniza espaços e maiúsculas
- **Identificação de sinônimos**: Evita duplicação de obras com títulos similares
- **Tratamento de valores monetários**: Converte "R$ 1.234,56" para Double
- **Padronização de métodos de pagamento**: Enum para consistência
- **Encoding**: Suporte a caracteres especiais brasileiros (ISO-8859-1)

### ✅ Estrutura do Banco
- **Normalização**: Separação em tabelas `obras_tb` e `vendas_tb`
- **Relacionamentos**: Many-to-One entre Venda e Obra
- **Integridade**: Constraints e validações

### ✅ API REST
- **Upload de CSV**: Endpoint para processar arquivos
- **Consultas**: Endpoints para análise dos dados

## Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.5.7**
- **Spring Data JPA**
- **MySQL**
- **Apache Commons CSV**
- **Maven**

## Como Executar

### Pré-requisitos
- Java 17+
- MySQL 8.0+
- Maven 3.6+

### Configuração do Banco
1. Criar banco de dados:
```sql
CREATE DATABASE datashape;
```

2. Configurar credenciais em `application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/datashape
spring.datasource.username=root
spring.datasource.password=1234567
```

### Executar a Aplicação
```bash
# Clonar o repositório
git clone <url-do-repositorio>
cd DataShape

# Compilar e executar
mvn spring-boot:run
```

### Usar a API

#### Upload de CSV
```bash
POST http://localhost:8080/data/upload
Content-Type: multipart/form-data
Parameter: csvFile (arquivo CSV)
```

#### Consultar Vendas
```bash
GET http://localhost:8080/vendas/all-vendas
GET http://localhost:8080/vendas/total-vendas
GET http://localhost:8080/vendas/vendas-por-mes
```

## Estrutura do Projeto

```
src/main/java/com/academico/DataShape/
├── config/          # Configurações customizadas
├── controllers/     # Endpoints REST
├── model/
│   ├── entity/      # Entidades JPA
│   └── dto/         # Data Transfer Objects
├── repository/      # Repositórios JPA
└── services/        # Lógica de negócio
```

## Equipe

- [Caio César]
- [Juan Gabriel]
- [Lucas Alves]

## Demonstração

O projeto resolve o problema de análise de vendas de livros governamentais, permitindo:
- Identificar obras mais vendidas
- Analisar tendências mensais
- Comparar métodos de pagamento
- Detectar inconsistências nos dados originais
