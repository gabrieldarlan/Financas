package br.com.gdarlan.financas.ui.activity

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.gdarlan.financas.R
import br.com.gdarlan.financas.extensions.formataParaBrasileiro
import br.com.gdarlan.financas.model.Tipo.DESPESA
import br.com.gdarlan.financas.model.Tipo.RECEITA
import br.com.gdarlan.financas.model.Transacao
import br.com.gdarlan.financas.ui.ResumoView
import br.com.gdarlan.financas.ui.adapter.ListaTransacoesAdapter
import kotlinx.android.synthetic.main.activity_lista_transacoes.*
import kotlinx.android.synthetic.main.form_transacao.view.*
import java.math.BigDecimal
import java.util.*

class ListaTransacaoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)
        val transacoes: List<Transacao> = transacoesDeExemplo()
        configuraResumo(transacoes)
        configuraLista(transacoes)

        lista_transacoes_adiciona_receita.setOnClickListener {
            val view = window.decorView
            val viewCriada = LayoutInflater.from(this).inflate(
                R.layout.form_transacao,
                view as ViewGroup,
                false
            )

            val ano = 2021
            val mes = 1
            val dia = 20

            val hoje = Calendar.getInstance()
            viewCriada.form_transacao_data.setText(hoje.formataParaBrasileiro())

            viewCriada.form_transacao_data.setOnClickListener {
                DatePickerDialog(this, { view, ano, mes, dia ->
                    val dataSelecionada = Calendar.getInstance()
                    dataSelecionada.set(ano, mes, dia)
                    viewCriada.form_transacao_data
                        .setText(
                            dataSelecionada
                                .formataParaBrasileiro()
                        )
                }, ano, mes, dia).show()
            }

            val adapter = ArrayAdapter.createFromResource(
                this,
                R.array.categorias_de_receita,
                android.R.layout.simple_spinner_dropdown_item

            )
            viewCriada.form_transacao_categoria.adapter = adapter

            AlertDialog
                .Builder(this)
                .setTitle(R.string.adiciona_despesa)
                .setView(viewCriada)
                .setPositiveButton("Adicinar", null)
                .setNegativeButton("Cancelar", null)
                .show()

        }
    }

    private fun configuraResumo(transacoes: List<Transacao>) {
        val view = window.decorView
        val resumoView = ResumoView(this, view, transacoes)
        resumoView.atualiza()
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