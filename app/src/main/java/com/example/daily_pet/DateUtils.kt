package com.example.daily_pet.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

object DateUtils {

    @RequiresApi(Build.VERSION_CODES.O)
    fun calcularDiasDesdeCriacao(dataCriacao: String): Long {
        return try {
            val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
            val criacao = LocalDate.parse(dataCriacao, formatter)
            val hoje = LocalDate.now()
            val progresso = ChronoUnit.DAYS.between(criacao, hoje)
            progresso
        } catch (e: Exception) {
            0
        }
    }
}
