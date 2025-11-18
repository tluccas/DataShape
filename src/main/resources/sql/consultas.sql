-- Todas as obras + data de venda + valor pago
SELECT o.titulo_obra AS Obra,
		v.data_venda,
        v.valor_pago
FROM vendas_tb v
JOIN obras_tb o
ON o.id = v.obra_id;

-- Valor total de vendas por Obra
SELECT 
o.titulo_obra AS Obra,
SUM(v.valor_pago) AS lucro_total
FROM vendas_tb v
JOIN obras_tb o 
ON o.id = v.obra_id GROUP BY o.titulo_obra ORDER BY lucro_total DESC;

-- Total de vendas/mes por obra
SELECT 
	YEAR(v.data_venda) AS Ano,
	v.mes_venda AS Mes,
    o.titulo_obra AS Obra,
    SUM(v.valor_pago) AS lucro_mes
FROM vendas_tb v
JOIN obras_tb o ON o.id = v.obra_id
GROUP BY Ano, Mes, Obra
ORDER BY FIELD(Mes,
	 'JANEIRO', 'FEVEREIRO', 'MARÇO', 'ABRIL',
        'MAIO', 'JUNHO', 'JULHO', 'AGOSTO',
        'SETEMBRO', 'OUTUBRO', 'NOVEMBRO', 'DEZEMBRO')
, lucro_mes DESC;

-- Quantidade total de vendas por Obra
SELECT o.titulo_obra AS Obra,
SUM(v.quantidade_venda) AS Quantidade
FROM vendas_tb v
JOIN obras_tb o 
WHERE o.id = v.obra_id
GROUP BY Obra
ORDER BY Quantidade DESC;
