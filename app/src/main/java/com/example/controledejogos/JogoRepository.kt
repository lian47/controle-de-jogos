package com.example.controledejogos

object JogoRepository {
    private val listaJogos = mutableListOf<Jogo>()

    private var proximoId = 1

    fun adicionarJogo(nome: String, plataforma: String, foiFinalizado: Boolean){
        val novoJogo = Jogo(proximoId, nome, plataforma, foiFinalizado)
        listaJogos.add(novoJogo)
        proximoId++
    }

    fun obterTodosOsJogos(): List<Jogo> {
        return listaJogos
    }
    fun atualizarJogo(id: Int, novoNome: String, novaPlataforma: String, novoFoiFinalizado: Boolean){
        val jogoExistente = listaJogos.find {it.id == id}

        jogoExistente?.let {
            it.nome = novoNome
            it.plataforma = novaPlataforma
            it.foiFinalizado = novoFoiFinalizado
        }
    }

    fun removerJogo(id: Int){
        listaJogos.removeAll {it.id == id}
    }
}