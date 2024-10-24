package io.github.aaiezza.cstrengths

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.fail
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.concurrent.atomic.AtomicReference

class StrengthsComparatorTest {
    private lateinit var subject: StrengthsComparator

    @BeforeEach
    fun setUp() {
        subject = StrengthsComparator()
    }

    @Test
    fun `should give expected timeline results`() {
        val strengths1 = readCliftonStrengthsResultsFile("20180205_results.txt")
        val strengths2 = readCliftonStrengthsResultsFile("20241023_results.txt")

        val result = subject.compareStrengths(strengths1, strengths2)
            .sortedByUpwardMovement()

        assertThat(result[0].strength).isEqualTo(Clifton.Strength("Belief"))
    }

    private fun readCliftonStrengthsResultsFile(fileName: String): Clifton.DatedStrengths {
        val inputStream = StrengthsComparatorTest::class.java.classLoader.getResourceAsStream(fileName)
            ?: fail { "File not found: $fileName" }

        val dateOfResults = AtomicReference<LocalDate>()

        return inputStream.reader()
            .readLines()
            .mapIndexedNotNull { i, it ->
                if (i == 0) {
                    dateOfResults.set(LocalDate.parse(it, DateTimeFormatter.ofPattern("yyyyMMdd")))
                    null
                } else  Clifton.Strength(it)
            }
            .let{
                Clifton.DatedStrengths(dateOfResults.get(), it)
            }
    }
}
