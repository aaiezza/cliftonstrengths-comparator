package io.github.aaiezza.cstrengths

import org.apache.commons.cli.CommandLineParser
import org.apache.commons.cli.DefaultParser
import org.apache.commons.cli.Option
import org.apache.commons.cli.Options
import java.text.ParseException
import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun main(args: Array<String>) {
    try {
        val configuration = parseCommandLine(args)

        val reader = StrengthsReportFileReader()
        val strengthsComparator = StrengthsComparator()
        val timeline = strengthsComparator.compareStrengths(
            reader.readStrengths(configuration.filename1),
            reader.readStrengths((configuration.filename2))
        )

        val outputFilename = "%s%s.txt".format(
            configuration.outputFilenamePrefix,
            LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"))
        )
        StrengthsTimelineFileWriter().writeTimeline(outputFilename, timeline)
    } catch (exp: ParseException) {
        System.err.println("Parsing failed.  Reason: " + exp.message)
    }
}

data class Configuration(
    val filename1: String,
    val filename2: String,
    val outputFilenamePrefix: String
)

fun parseCommandLine(args: Array<String>): Configuration {
    val options = Options().addOption(
        Option.builder("i")
            .numberOfArgs(2)
            .required()
            .hasArgs()
            .valueSeparator(' ')
            .desc("The two Clifton Strengths assessment files in order of the date they were taken").build()
    )
        .addOption("o", "output-file-prefix", true, "Prefix of output file: `{prefix}YYYYMMDD.txt`")
    val parser: CommandLineParser = DefaultParser()
    val line = parser.parse(options, args)

    val inputFiles = line.getOptionValues('i')
    require(inputFiles.size == 2) { "Two input files are required" }

    val outputFilenamePrefix = line.getOptionValue('o') { "result_comparison_" }

    return Configuration(inputFiles[0], inputFiles[1], outputFilenamePrefix)
}
