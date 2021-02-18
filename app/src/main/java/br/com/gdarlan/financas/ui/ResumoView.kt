package br.com.gdarlan.financas.ui

import android.content.Context
import android.view.View
import androidx.core.content.ContextCompat
import br.com.gdarlan.financas.R
import br.com.gdarlan.financas.extensions.formataParaBrasileiro
import br.com.gdarlan.financas.model.Resumo
import br.com.gdarlan.financas.model.Transacao
import kotlinx.android.synthetic.main.resumo_card.view.*
import java.math.BigDecimal

class ResumoView(
    private val context: Context,
    private val view: View,
    transacoes: List<Transacao>
) {

    private val resumo = Resumo(transacoes)
    private val corReceita = ContextCompat.getColor(context, R.color.receita)
    private val corDespesa = ContextCompat.getColor(context, R.color.despesa)

    fun atualiza(){
        adicionaDespesa()
        adicionaReceita()
        adicionaTotal()
    }

    private fun adicionaReceita() {
        val totalReceita = resumo.receita
        with(view.resumo_card_receita) {
            setTextColor(corReceita)
            text = totalReceita.formataParaBrasileiro()
        }

    }

    private fun adicionaDespesa() {
        val totalDespesa = resumo.despesa
        with(view.resumo_card_despesa) {
            setTextColor(corDespesa)
            text = totalDespesa.formataParaBrasileiro()
        }
    }

    private fun adicionaTotal() {
        val total = resumo.total
        val cor = corPorTipo(total)
        with(view.resumo_card_total){
            setTextColor(cor)
            text = total.formataParaBrasileiro()
        }
    }

    private fun corPorTipo(valor: BigDecimal): Int = if (valor >= BigDecimal.ZERO) {
        corReceita
    } else {
        corDespesa
    }

}