package io.github.aaiezza.cstrengths

class StrengthsComparator {
    fun compareStrengths(strengths1: Clifton.DatedStrengths, strengths2: Clifton.DatedStrengths): Clifton.Strengths.Timeline {
        return strengths2.mapIndexed { i, s ->
            Clifton.Strength.Movement(s, strengths1.indexOf(s) - i)
        }.let{ Clifton.Strengths.Timeline(listOf(strengths1, strengths2), it) }
    }
}
