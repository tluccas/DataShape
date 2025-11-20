# Fonte dos Dados - DataShape

## Base de Dados Utilizada
- **Fonte**: [dados.gov.br](https://dados.gov.br)
- **Dataset**: Dados Abertos CPE - Distribuição e vendas Loja do livro 2024
- **URL**: https://dados.gov.br/dados/conjuntos-dados/dados-abertos-cpe-distribuicao-e-vendas-loja-do-livro
- **Arquivo**: Dados_Abertos_CPE_Distribuição_e_vendas_Loja_do_livro_2024.csv

## Estrutura Original do CSV
Colunas identificadas no arquivo original:
- Data
- Mês  
- Obra Vendida
- Quantidade
- Valor Pago
- Forma de Pagamento

## Problemas Identificados nos Dados Originais
1. **Títulos duplicados com variações**: Acentos, espaços extras
2. **Métodos de pagamento inconsistentes**: "Cartão de Crédito" vs "CARTAO_CREDITO"
3. **Formatação de valores**: "R$ 1.234,56" precisa ser normalizado
4. **Encoding**: Arquivo em ISO-8859-1 (caracteres especiais brasileiros)

## Transformações Aplicadas
- Normalização de títulos para evitar duplicatas
- Padronização de métodos de pagamento via enum
- Conversão de valores monetários para Double
- Parsing de datas no formato dd/MM/yyyy