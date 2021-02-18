package br.com.gdarlan.financas.model

import br.com.gdarlan.financas.model.Tipo.DESPESA
import br.com.gdarlan.financas.model.Tipo.RECEITA
import java.math.BigDecimal

class Resumo(private val transacoes: List<Transacao>) {

    val receita get() = somaPorTipo(RECEITA)

    val despesa get() = somaPorTipo(DESPESA)

    val total get() = receita.subtract(despesa)

    private fun somaPorTipo(tipo: Tipo): BigDecimal {
        val somaDeTransacoesPeloTipo = transacoes
            .filter { it.tipo == tipo }
            .sumByDouble { it.valor.toDouble() }
        return BigDecimal(somaDeTransacoesPeloTipo)
    }
}

