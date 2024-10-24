package io.github.aaiezza.cstrengths

import java.io.File
import java.io.PrintWriter
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.concurrent.atomic.AtomicReference

class StrengthsReportFileReader {
    fun readStrengths(fileName: String): Clifton.DatedStrengths {
        val inputStream = File(fileName).inputStream()

        val dateOfResults = AtomicReference<LocalDate>()

        return inputStream.reader()
            .readLines()
            .mapIndexedNotNull { i, it ->
                if (i == 0) {
                    dateOfResults.set(LocalDate.parse(it, DateTimeFormatter.ofPattern("yyyyMMdd")))
                    null
                } else Clifton.Strength(it)
            }
            .let {
                Clifton.DatedStrengths(dateOfResults.get(), it)
            }
    }
}

class StrengthsTimelineFileWriter {
    fun writeTimeline(fileName: String, timeline: Clifton.Strengths.Timeline) {
        File(fileName).printWriter().use { writer ->
            writer.println("-- %s --".format(timeline.strengths[0].date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))))
            writer.println()
            writer.printTimeline(timeline.strengths[0].map {
                timeline.first { ts -> ts.strength == it }
            }.let { timeline.copy(value = it) })
            writer.println()
            writer.println("-- %s --".format(timeline.strengths[1].date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))))
            writer.println()
            writer.printTimeline(timeline)
            writer.println()
            writer.println("-- Sorted by movement --")
            writer.println()
            writer.printTimeline(timeline.sortedByUpwardMovement())
        }
    }

    private fun PrintWriter.printTimeline(timeline: Clifton.Strengths.Timeline) {
        timeline.forEachIndexed { i, it ->
            println("%2d. %-18s %+2d".format(i + 1, it.strength.value, it.movement))
        }
    }
}
