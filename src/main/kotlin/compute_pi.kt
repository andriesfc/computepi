import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.context
import com.github.ajalt.clikt.output.CliktHelpFormatter
import com.github.ajalt.clikt.parameters.options.default
import com.github.ajalt.clikt.parameters.options.flag
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.types.int
import java.text.DecimalFormat
import kotlin.random.Random

private const val DEFAULT_SAMPLE_SIZE = 100000
private const val DEFAULT_ITERATIONS = 100

fun main(args: Array<String>) {
    object : CliktCommand(
        name = "computepi",
        help = """Compute PI using Monte Carlo approximation
        
        For a detail explanation see the following articles:
        https://www.geeksforgeeks.org/estimating-value-pi-using-monte-carlo/
        https://betterprogramming.pub/calculating-pi-%CF%80-with-monte-carlo-using-parallel-computing-with-openmp-and-c-2b3a357f0f78
      
        """.trimIndent()) {

        val emitAll by option(
            "--all",
            help = "Print out intermediate calculated values of PI."
        ).flag(default = false)

        val sampleSize by option(
            "--sampleSize",
            help = "Number of points to evaluate per computation of a single value."
        ).int().default(DEFAULT_SAMPLE_SIZE)

        val iterations by option(
            "--iterations", "-i",
            help = "How many iterations should the computation be repeated until the final value is reported."
        ).int().default(DEFAULT_ITERATIONS)

        override fun run() {
            val pi = computePi(sampleSize)
                .take(iterations)
                .onEach { if (emitAll) println(it) }
                .last()

            if (!emitAll) {
                println(pi)
            }
        }

        init {
            context {
                helpFormatter = CliktHelpFormatter(showDefaultValues = true)
            }
            main(args)
        }
    }
}

data class Point(val x: Double, val y: Double)

fun Number.format(f: DecimalFormat): String = f.format(this)
fun Number.format(f: String): String = format(DecimalFormat(f))

fun Point.isInsideUnitCircle() = x * x + y * y <= 1

fun Random.nextPoint(): Point = Point(x = nextDouble(0.0, 1.0), y = nextDouble())
fun Random.points(): Sequence<Point> = sequence {
    while (true) {
        yield(nextPoint())
    }
}

fun computePi(sampleSize: Int): Sequence<Double> = sequence {
    var total = 0
    var count = 0
    while (true) {
        val points = Random.points().take(sampleSize)
        val inside = points.filter(Point::isInsideUnitCircle)
        total += sampleSize
        count += inside.count()
        val ratio = count / total.toDouble()
        yield(ratio * 4)
    }
}
