package br.com.gdarlan.financas.model

import java.math.BigDecimal
import java.util.Calendar

data class Transacao(
    val valor: BigDecimal,
    val categoria: String = "Indefinida",
    val tipo: Tipo,
    val data: Calendar = Calendar.getInstance()
)