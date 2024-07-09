package com.example.jogodavelhamquinasimples

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import com.example.jogodavelhamquinasimples.databinding.ActivityMainBinding

import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    val tabuleiro = arrayOf(
        arrayOf("A", "B", "C"),
        arrayOf("D", "E", "F"),
        arrayOf("G", "H", "I")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
    }

    fun buttonClick(view: View){
        val buttonSelecionado = view as Button
        buttonSelecionado.text = "X"
        buttonSelecionado.setBackgroundResource(R.drawable.coxinha)
        buttonSelecionado.isEnabled = false

        when(buttonSelecionado.id){
            binding.buttonZero.id -> tabuleiro[0][0] = "X"
            binding.buttonUm.id -> tabuleiro[0][1] = "X"
            binding.buttonDois.id -> tabuleiro[0][2] = "X"
            binding.buttonTres.id -> tabuleiro[1][0] = "X"
            binding.buttonQuatro.id -> tabuleiro[1][1] = "X"
            binding.buttonCinco.id -> tabuleiro[1][2] = "X"
            binding.buttonSeis.id -> tabuleiro[2][0] = "X"
            binding.buttonSete.id -> tabuleiro[2][1] = "X"
            binding.buttonOito.id -> tabuleiro[2][2] = "X"
        }

        val vencedor = verificaVencedor(tabuleiro)
        if (vencedor != null) {
            exibirMensagemVencedor(vencedor)
            reiniciarJogo()
            return
        }

        // Gera posições aleatórias para o próximo movimento do computador
        var rX: Int
        var rY: Int
        var i = 0
        while (i < 9) {
            rX = Random.nextInt(0, 3)
            rY = Random.nextInt(0, 3)
            if (tabuleiro[rX][rY] != "X" && tabuleiro[rX][rY] != "O") {
                tabuleiro[rX][rY] = "O"
                atualizarBotao(rX, rY, R.drawable.pizza)
                break
            }
            i++
        }

        val vencedorDepoisComputador = verificaVencedor(tabuleiro)
        if (vencedorDepoisComputador != null) {
            exibirMensagemVencedor(vencedorDepoisComputador)
            reiniciarJogo()
        }
    }

    fun verificaVencedor(tabuleiro: Array<Array<String>>): String? {
        for (i in 0 until 3) {
            if (tabuleiro[i][0] == tabuleiro[i][1] && tabuleiro[i][1] == tabuleiro[i][2]) {
                return tabuleiro[i][0]
            }
            if (tabuleiro[0][i] == tabuleiro[1][i] && tabuleiro[1][i] == tabuleiro[2][i]) {
                return tabuleiro[0][i]
            }
        }
        if (tabuleiro[0][0] == tabuleiro[1][1] && tabuleiro[1][1] == tabuleiro[2][2]) {
            return tabuleiro[0][0]
        }
        if (tabuleiro[0][2] == tabuleiro[1][1] && tabuleiro[2][2] == tabuleiro[2][0]) {
            return tabuleiro[0][2]
        }

        var empate = 0
        for (linha in tabuleiro) {
            for (valor in linha) {
                if (valor == "X" || valor == "O") {
                    empate++
                }
            }
        }
        if (empate == 9) {
            return "Empate"
        }
        return null
    }

    private fun exibirMensagemVencedor(vencedor: String) {
        val mensagem = if (vencedor == "Empate") {
            "O jogo terminou em empate!"
        } else {
            "O vencedor é: $vencedor"
        }
        Toast.makeText(this, mensagem, Toast.LENGTH_LONG).show()
    }

    private fun reiniciarJogo() {
        for (i in 0 until 3) {
            for (j in 0 until 3) {
                tabuleiro[i][j] = when {
                    i == 0 && j == 0 -> "A"
                    i == 0 && j == 1 -> "B"
                    i == 0 && j == 2 -> "C"
                    i == 1 && j == 0 -> "D"
                    i == 1 && j == 1 -> "E"
                    i == 1 && j == 2 -> "F"
                    i == 2 && j == 0 -> "G"
                    i == 2 && j == 1 -> "H"
                    i == 2 && j == 2 -> "I"
                    else -> ""
                }
            }
        }
        binding.buttonZero.setBackgroundResource(R.drawable.pizza)
        binding.buttonZero.isEnabled = true
        binding.buttonUm.setBackgroundResource(R.drawable.pizza)
        binding.buttonUm.isEnabled = true
        binding.buttonDois.setBackgroundResource(R.drawable.pizza)
        binding.buttonDois.isEnabled = true
        binding.buttonTres.setBackgroundResource(R.drawable.pizza)
        binding.buttonTres.isEnabled = true
        binding.buttonQuatro.setBackgroundResource(R.drawable.pizza)
        binding.buttonQuatro.isEnabled = true
        binding.buttonCinco.setBackgroundResource(R.drawable.pizza)
        binding.buttonCinco.isEnabled = true
        binding.buttonSeis.setBackgroundResource(R.drawable.pizza)
        binding.buttonSeis.isEnabled = true
        binding.buttonSete.setBackgroundResource(R.drawable.pizza)
        binding.buttonSete.isEnabled = true
        binding.buttonOito.setBackgroundResource(R.drawable.pizza)
        binding.buttonOito.isEnabled = true
    }

    private fun atualizarBotao(rX: Int, rY: Int, resourceId: Int) {
        val posicao = rX * 3 + rY
        when(posicao){
            0 -> {
                binding.buttonZero.setBackgroundResource(resourceId)
                binding.buttonZero.isEnabled = false
            }
            1 -> {
                binding.buttonUm.setBackgroundResource(resourceId)
                binding.buttonUm.isEnabled = false
            }
            2 -> {
                binding.buttonDois.setBackgroundResource(resourceId)
                binding.buttonDois.isEnabled = false
            }
            3 -> {
                binding.buttonTres.setBackgroundResource(resourceId)
                binding.buttonTres.isEnabled = false
            }
            4 -> {
                binding.buttonQuatro.setBackgroundResource(resourceId)
                binding.buttonQuatro.isEnabled = false
            }
            5 -> {
                binding.buttonCinco.setBackgroundResource(resourceId)
                binding.buttonCinco.isEnabled = false
            }
            6 -> {
                binding.buttonSeis.setBackgroundResource(resourceId)
                binding.buttonSeis.isEnabled = false
            }
            7 -> {
                binding.buttonSete.setBackgroundResource(resourceId)
                binding.buttonSete.isEnabled = false
            }
            8 -> {
                binding.buttonOito.setBackgroundResource(resourceId)
                binding.buttonOito.isEnabled = false
            }
        }
    }
}
