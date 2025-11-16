package com.academico.DataShape.model.entity;

public enum MetodoPagamento {
    PIX,
    CARTAO_CREDITO,
    CARTAO,
    DESCONHECIDO,
    GRU;


    // Normalizar os metodos de pagamento da base de dados
    public static MetodoPagamento fromCsv(String raw) {
    if (raw == null) return null;

    String normalizado = raw
            .toUpperCase()
            .replace("Ã", "A")
            .replace("Á", "A")
            .replace("É", "E")
            .replace("Í", "I")
            .replace("Ó", "O")
            .replace("Ú", "U")
            .replace("Ç", "C")
            .replace(" DE ", " ")  // remove o DE do "Cartão DE Crédito"
            .replace(" ", "_"); // Normaliza os espaços
    try{
        return MetodoPagamento.valueOf(normalizado);
    } catch(Exception e){
        return DESCONHECIDO; // Uma coluna de metodo de pagamento está em inteiro, isso evita a linha nao ser lida ao processar vendas
    }
}
}


