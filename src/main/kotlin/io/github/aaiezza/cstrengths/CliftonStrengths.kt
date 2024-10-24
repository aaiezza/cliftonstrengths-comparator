package io.github.aaiezza.cstrengths

import io.github.aaiezza.cstrengths.Clifton.Strengths.Companion.NUMBER_OF_STRENGTHS
import java.time.LocalDate

class Clifton {
    data class Strength(val value: String) {
        init {
            require(value.isNotBlank())
        }

        data class Movement(val strength: Strength, val movement: Int) {
            init {
                require(movement in -NUMBER_OF_STRENGTHS..NUMBER_OF_STRENGTHS)
            }
        }
    }

    data class Strengths(val value: List<Strength>) : List<Strength> by value {
        companion object {
            const val NUMBER_OF_STRENGTHS: Int = 34
        }

        init {
            require(value.size == NUMBER_OF_STRENGTHS)
        }

        data class Timeline(val strengths: List<DatedStrengths>, val value: List<Strength.Movement>) : List<Strength.Movement> by value
    }

    data class DatedStrengths(val date: LocalDate, val strengths: List<Strength>) : List<Strength> by strengths
}

fun Clifton.Strengths.Timeline.sortedByUpwardMovement() = this.sortedBy { -it.movement }.let{ Clifton.Strengths.Timeline(this.strengths, it) }
