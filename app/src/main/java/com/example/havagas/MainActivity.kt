package com.example.havagas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import com.example.havagas.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var amb: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        amb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(amb.root)

        amb.formacaoSp.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when (position) {
                    0 -> {
                        amb.anoFormacaoEt.visibility = View.GONE
                        amb.instituicaoEt.visibility = View.GONE
                        amb.tituloMonografiaEt.visibility = View.GONE
                        amb.orientadorEt.visibility = View.GONE
                    }
                    in 1..2 -> {
                        amb.anoFormacaoEt.visibility = View.VISIBLE
                        amb.instituicaoEt.visibility = View.GONE
                        amb.tituloMonografiaEt.visibility = View.GONE
                        amb.orientadorEt.visibility = View.GONE
                    }
                    in 3..4 -> {
                        amb.anoFormacaoEt.visibility = View.VISIBLE
                        amb.instituicaoEt.visibility = View.VISIBLE
                        amb.tituloMonografiaEt.visibility = View.GONE
                        amb.orientadorEt.visibility = View.GONE
                    }
                    else -> {
                        amb.anoFormacaoEt.visibility = View.VISIBLE
                        amb.instituicaoEt.visibility = View.VISIBLE
                        amb.tituloMonografiaEt.visibility = View.VISIBLE
                        amb.orientadorEt.visibility = View.VISIBLE
                    }
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                // Não se aplica
            }
        }

        amb.telefoneCelularBt.setOnClickListener {
            if (amb.telefoneCelularEt.visibility == View.GONE) {
                amb.telefoneCelularEt.visibility = View.VISIBLE
            } else {
                amb.telefoneCelularEt.visibility = View.GONE
            }
        }




        amb.salvarBt.setOnClickListener {
            val nomeCompleto = amb.nomeCompletoEt.text.toString()
            val email = amb.emailEt.text.toString()
            val desejaReceberEmail = if (amb.simRb.isChecked) "Sim" else "Não"
            val telefone = amb.telefoneEt.text.toString()
            val telefoneTipo = if (amb.comercialRb.isChecked) "Comercial" else "Residencial"
            val telefoneCelular = amb.telefoneCelularEt.text.toString()
            val sexo = if (amb.masculinoRb.isChecked) "Masculino" else "Feminino"
            val data = "${amb.dataNascimentoDp.dayOfMonth}/${amb.dataNascimentoDp.month}/${amb.dataNascimentoDp.year}"
            val formacao = amb.formacaoSp.selectedItem.toString()
            val anoFormacao = amb.anoFormacaoEt.text.toString()
            val instituicao = amb.instituicaoEt.text.toString()
            val tituloMonografia = amb.tituloMonografiaEt.text.toString()
            val orientador = amb.orientadorEt.text.toString()
            val vagasInteresse = amb.vagasInteresseEt.text.toString()

            if (nomeCompleto.isNotEmpty() && email.isNotEmpty()
                && telefone.isNotEmpty() && telefoneTipo.isNotEmpty()
                && sexo.isNotEmpty() && data.isNotEmpty() && formacao.isNotEmpty()
                && anoFormacao.isNotEmpty() && vagasInteresse.isNotEmpty()) {

                val mensagem = when (formacao) {
                    "Graduação", "Especialização" -> {
                        if (instituicao.isNotEmpty()) {
                            "Nome completo: $nomeCompleto, E-mail: $email, Deseja receber e-mail? $desejaReceberEmail, " +
                                    "Tipo telefone: $telefoneTipo, Telefone: $telefone, Telefone celular: $telefoneCelular, " +
                                    "Sexo: $sexo, Data nascimento: $data, Formação: $formacao, Ano de formação: $anoFormacao, " +
                                    "Instituição: $instituicao, Vagas de interesse: $vagasInteresse"
                        } else {
                            ""
                        }
                    }
                    "Mestrado", "Doutorado" -> {
                        if (tituloMonografia.isNotEmpty() && orientador.isNotEmpty()) {
                            "Nome completo: $nomeCompleto, E-mail: $email, Deseja receber e-mail? $desejaReceberEmail, " +
                                    "Tipo telefone: $telefoneTipo, Telefone: $telefone, Telefone celular: $telefoneCelular, " +
                                    "Sexo: $sexo, Data nascimento: $data, Formação: $formacao, Ano de formação: $anoFormacao, " +
                                    "Instituição: $instituicao, Título monografia: $tituloMonografia, " +
                                    "Orientador: $orientador, Vagas de interesse: $vagasInteresse"
                        } else {
                            ""
                        }
                    }
                    else -> {
                        "Nome completo: $nomeCompleto, E-mail: $email, Deseja receber e-mail? $desejaReceberEmail, " +
                                "Tipo telefone: $telefoneTipo, Telefone: $telefone, Telefone celular: $telefoneCelular, " +
                                "Sexo: $sexo, Data nascimento: $data, Formação: $formacao, Ano de formação: $anoFormacao, " +
                                "Vagas de interesse: $vagasInteresse"
                    }
                }

                if (mensagem.isNotEmpty()) {
                    Toast.makeText(this@MainActivity, mensagem, Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this@MainActivity, "Preencha todos os campos obrigatórios!", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(this@MainActivity, "Preencha todos os campos obrigatórios!", Toast.LENGTH_LONG).show()
            }

        }

        amb.limparBt.setOnClickListener {
            amb.nomeCompletoEt.setText("")
            amb.emailEt.setText("")
            amb.telefoneEt.setText("")
            amb.telefoneCelularEt.setText("")
            amb.telefoneCelularEt.visibility = View.GONE
            amb.vagasInteresseEt.setText("")
            amb.receberEmailsRg.check(amb.simRb.id)
            amb.telefoneTipoRg.check(amb.comercialRb.id)
            amb.sexoRg.check(amb.masculinoRb.id)
            amb.dataNascimentoDp.updateDate(2000, 0, 1)
            amb.formacaoSp.setSelection(0)
            amb.anoFormacaoEt.setText("")
            amb.instituicaoEt.setText("")
            amb.tituloMonografiaEt.setText("")
            amb.orientadorEt.setText("")
            amb.anoFormacaoEt.visibility = View.GONE
            amb.instituicaoEt.visibility = View.GONE
            amb.tituloMonografiaEt.visibility = View.GONE
            amb.orientadorEt.visibility = View.GONE
        }
    }
}