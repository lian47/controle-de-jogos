package com.example.controledejogos
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class JogoAdapter(
    private val lista: List<Jogo>,
    private val aoClicarNoJogo: (Jogo) -> Unit,        // Para Editar
    private val aoSegurarNoJogo: (Jogo) -> Unit        // Para Excluir
) : RecyclerView.Adapter<JogoAdapter.JogoViewHolder>() {

    class JogoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtNome: TextView = itemView.findViewById(R.id.txtNomeJogo)
        val txtPlataforma: TextView = itemView.findViewById(R.id.txtPlataformaJogo)
        val txtStatus: TextView = itemView.findViewById(R.id.txtStatusJogo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JogoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_jogo, parent, false)
        return JogoViewHolder(view)
    }

    override fun onBindViewHolder(holder: JogoViewHolder, position: Int) {
        val jogoAtual = lista[position]

        holder.txtNome.text = jogoAtual.nome
        holder.txtPlataforma.text = jogoAtual.plataforma

        if (jogoAtual.foiFinalizado) {
            holder.txtStatus.text = "Finalizado"
        } else {
            holder.txtStatus.text = "Pendente"
        }

        holder.itemView.setOnClickListener {
            aoClicarNoJogo(jogoAtual)
        }

        holder.itemView.setOnLongClickListener {
            aoSegurarNoJogo(jogoAtual)
            true
        }
    }

    override fun getItemCount(): Int {
        return lista.size
    }
}