package br.com.gdarlan.financas.extensions

import java.math.BigDecimal
import java.text.DecimalFormat
import java.util.Locale

fun BigDecimal.formataParaBrasileiro(): String? {
    return  DecimalFormat
        .getCurrencyInstance(Locale("pt", "br"))
        .format(this)


}