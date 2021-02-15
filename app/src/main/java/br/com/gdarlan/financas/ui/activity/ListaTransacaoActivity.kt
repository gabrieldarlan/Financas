package br.com.gdarlan.financas.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.gdarlan.financas.R
import br.com.gdarlan.financas.model.Tipo.DESPESA
import br.com.gdarlan.financas.model.Tipo.RECEITA
import br.com.gdarlan.financas.model.Transacao
import br.com.gdarlan.financas.ui.ResumoView
import br.com.gdarlan.financas.ui.adapter.ListaTransacoesAdapter
import kotlinx.android.synthetic.main.activity_lista_transacoes.*
import java.math.BigDecimal
import java.util.*

class ListaTransacaoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)

        val transacoes: List<Transacao> = transacoesDeExemplo()

        configuraResumo(transacoes)

        configuraLista(transacoes)
    }

    private fun configuraResumo(transacoes: List<Transacao>) {
        val view = window.decorView
        val resumoView = ResumoView(this,view, transacoes)
        resumoView.adicionaReceita()
        resumoView.adicionaDespesa()
        resumoView.adicionaTotal()
    }


    private fun configuraLista(transacoes: List<Transacao>) {
        lista_transacoes_listview.adapter = ListaTransacoesAdapter(transacoes, this)
    }

    private fun transacoesDeExemplo() = listOf(
        Transacao(
            valor = BigDecimal(500.0),
            tipo = DESPESA,
            categoria = "Almoço de final de semana",
            data = Calendar.getInstance()
        ), Transacao(
            valor = BigDecimal(100.0),
            tipo = RECEITA,
            categoria = "Economia"
        ), Transacao(
            valor = BigDecimal(200.0),
            tipo = DESPESA,
            data = GregorianCalendar(2021, 1, 12)
        ),
        Transacao(
            valor = BigDecimal(500.0),
            categoria = "Prêmio",
            data = GregorianCalendar(2021, 4, 4),
            tipo = RECEITA
        )
    )
}