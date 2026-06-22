package com.example.controledejogos

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class FormularioActivity : AppCompatActivity() {
    private var jogoIdParaEdicao: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario)

        val editNome = findViewById<EditText>(R.id.editNomeJogo)
        val editPlataforma = findViewById<EditText>(R.id.editPlataforma)
        val checkFinalizado = findViewById<CheckBox>(R.id.checkFinalizado)
        val btnSalvar = findViewById<Button>(R.id.btnSalvar)
        jogoIdParaEdicao = intent.getIntExtra("JOGO_ID", -1)
        if (jogoIdParaEdicao != -1) {
            val jogoSelecionado = JogoRepository.obterTodosOsJogos().find { it.id == jogoIdParaEdicao }

            jogoSelecionado?.let {
                editNome.setText(it.nome)
                editPlataforma.setText(it.plataforma)
                checkFinalizado.isChecked = it.foiFinalizado

                btnSalvar.text = "Atualizar Jogo"
            }
        }
        btnSalvar.setOnClickListener {
            val nome = editNome.text.toString()
            val plataforma = editPlataforma.text.toString()
            val finalizado = checkFinalizado.isChecked
            if (nome.trim().isEmpty() || plataforma.trim().isEmpty()) {
                Toast.makeText(this, "Por favor, preencha todos os campos!", Toast.LENGTH_SHORT).show()
            } else {

                if (jogoIdParaEdicao != -1) {
                    JogoRepository.atualizarJogo(jogoIdParaEdicao, nome, plataforma, finalizado)
                    Toast.makeText(this, "Jogo atualizado com sucesso!", Toast.LENGTH_SHORT).show()
                } else {
                    JogoRepository.adicionarJogo(nome, plataforma, finalizado)
                    Toast.makeText(this, "Jogo salvo com sucesso!", Toast.LENGTH_SHORT).show()
                }

                finish()
            }
        }
    }
}