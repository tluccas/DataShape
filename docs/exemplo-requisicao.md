# Como Usar a API DataShape

## Upload de CSV via Postman/Insomnia

### Endpoint
```
POST http://localhost:8080/data/upload
```

### Configuração da Requisição
1. **Method**: GET (conforme implementado)
2. **Content-Type**: multipart/form-data
3. **Parameter**: 
   - Key: `csvFile`
   - Type: File
   - Value: Selecionar arquivo CSV

### Exemplo de Estrutura CSV Esperada
```csv
Data;Mês;Obra Vendida;Quantidade;Valor Pago;Forma de Pagamento
01/01/2024;JANEIRO;Dom Casmurro;2;R$ 45,90;PIX
15/02/2024;FEVEREIRO;O Cortiço;1;R$ 32,50;Cartão de Crédito
```

### Resposta Esperada
```json
{
  "obrasProcessadas": 15,
  "vendasProcessadas": 127
}
```

## Consultar Vendas
```
GET http://localhost:8080/data/all-vendas
```