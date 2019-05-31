package br.com.filipe.domain.exception

/**
 * Created by Murilo Moro on 31/01/19.
 */
class DefaultException(
    val code: String = "",
    override val message: String = "Erro Inesperado"
) : Exception()