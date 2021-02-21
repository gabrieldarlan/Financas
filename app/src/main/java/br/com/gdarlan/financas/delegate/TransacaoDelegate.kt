package br.com.gdarlan.financas.delegate

import br.com.gdarlan.financas.model.Transacao

interface TransacaoDelegate {
    fun delegate(transacao: Transacao)
}