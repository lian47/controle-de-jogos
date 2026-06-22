package com.example.controledejogos

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: JogoAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerViewJogos)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = JogoAdapter(
            lista = JogoRepository.obterTodosOsJogos(),

            aoClicarNoJogo = { jogoClicado ->
                val intent = Intent(this, FormularioActivity::class.java)
                // Enviamos o ID do jogo para a tela de formulário saber quem editar
                intent.putExtra("JOGO_ID", jogoClicado.id)
                startActivity(intent)
            },

            aoSegurarNoJogo = { jogoClicado ->
                AlertDialog.Builder(this)
                    .setTitle("Excluir Jogo")
                    .setMessage("Tem certeza que deseja excluir '${jogoClicado.nome}'?")
                    .setPositiveButton("Sim") { _, _ ->
                        JogoRepository.removerJogo(jogoClicado.id)
                        adapter.notifyDataSetChanged()
                        Toast.makeText(this, "Jogo excluído!", Toast.LENGTH_SHORT).show()
                    }
                    .setNegativeButton("Não", null)
                    .show()
            }
        )
        recyclerView.adapter = adapter

        val fabAdicionar = findViewById<FloatingActionButton>(R.id.fabAdicionarJogo)
        fabAdicionar.setOnClickListener {
            val intent = Intent(this, FormularioActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()
    }
}