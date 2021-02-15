package br.com.gdarlan.financas.ui

import android.view.View
import br.com.gdarlan.financas.extensions.formataParaBrasileiro
import br.com.gdarlan.financas.model.Resumo
import br.com.gdarlan.financas.model.Tipo.DESPESA
import br.com.gdarlan.financas.model.Transacao
import kotlinx.android.synthetic.main.resumo_card.view.*
import java.math.BigDecimal

class ResumoView(
    private val view: View,
    transacoes: List<Transacao>
) {

    private val resumo = Resumo(transacoes)

    fun adicionaReceita() {
        val totalReceita = resumo.receita()
        view.resumo_card_receita.text = totalReceita.formataParaBrasileiro()
    }

    fun adicionaDespesa() {
        val totalDespesa = resumo.despesa()
        view.resumo_card_despesa.text = totalDespesa.formataParaBrasileiro()
    }

    fun adicionaTotal() {
        val total = resumo.total()
        view.resumo_card_total.text = total.formataParaBrasileiro()
    }

}