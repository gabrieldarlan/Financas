package br.com.gdarlan.financas.ui.activity

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import br.com.gdarlan.financas.R
import br.com.gdarlan.financas.delegate.TransacaoDelegate
import br.com.gdarlan.financas.model.Tipo
import br.com.gdarlan.financas.model.Tipo.DESPESA
import br.com.gdarlan.financas.model.Tipo.RECEITA
import br.com.gdarlan.financas.model.Transacao
import br.com.gdarlan.financas.ui.ResumoView
import br.com.gdarlan.financas.ui.adapter.ListaTransacoesAdapter
import br.com.gdarlan.financas.ui.dialog.AdicionaTransacaoDialog
import br.com.gdarlan.financas.ui.dialog.AlteraTransacaoDialog
import kotlinx.android.synthetic.main.activity_lista_transacoes.*

class ListaTransacaoActivity : AppCompatActivity() {

    private val transacoes: MutableList<Transacao> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)
        configuraResumo()
        configuraLista()
        configuraFab()
    }

    private fun configuraFab() {
        lista_transacoes_adiciona_receita
            .setOnClickListener {
                chamaDialogDeAdicao(RECEITA)
            }

        lista_transacoes_adiciona_despesa
            .setOnClickListener {
                chamaDialogDeAdicao(DESPESA)
            }
    }

    private fun chamaDialogDeAdicao(tipo: Tipo) {
        AdicionaTransacaoDialog(window.decorView as ViewGroup, this)
            .chama(tipo, object : TransacaoDelegate {
                override fun delegate(transacao: Transacao) {
                    atualizaTransacoes(transacao)
                    lista_transacoes_adiciona_menu.close(true)
                }
            })
    }

    private fun atualizaTransacoes(transacao: Transacao) {
        transacoes.add(transacao)
        configuraLista()
        configuraResumo()
    }

    private fun configuraResumo() {
        val view = window.decorView
        val resumoView = ResumoView(this, view, transacoes)
        resumoView.atualiza()
    }

    private fun configuraLista() {
        lista_transacoes_listview.adapter = ListaTransacoesAdapter(transacoes, this)
        lista_transacoes_listview.setOnItemClickListener { parent, view, posicao, id ->
            val transacao = transacoes[posicao]
            AlteraTransacaoDialog(window.decorView as ViewGroup, this)
                .chama(transacao,object :TransacaoDelegate{
                    override fun delegate(transacao: Transacao) {
                        atualizaTransacoes(transacao)
                    }
                })

        }
    }

}