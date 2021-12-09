import kotlin.math.abs

fun main() {
    fun convertToPoint(point: String): Point {
        val intArray = point.split(",").map { it.toInt() }.toIntArray()
        return Point(intArray[0], intArray[1])
    }

    fun convertToPairOfPoints(points: List<String>): Pair<Point, Point> {
        val pointsArray = points.map { s -> convertToPoint(s) }.toTypedArray()
        return Pair(pointsArray[0], pointsArray[1])
    }

    fun parseInput(input: List<String>): List<Pair<Point, Point>> =
        input.map { line -> convertToPairOfPoints(line.split(" -> ")) }

    fun extrapolate(pair: Pair<Point, Point>): List<Point> =
        when {
            pair.first.isVertical(pair.second) -> pair.first.extrapolateVertical(pair.second)
            pair.first.isHorizontal(pair.second) -> pair.first.extrapolateHorizontal(pair.second)
            else -> pair.first.extrapolateDiagonal(pair.second)
        }

    fun part1(input: List<String>): Int {
        println("Day05 - part1")
        val lines: List<Pair<Point, Point>> = parseInput(input)
        println("lines: $lines")

        return lines
            .filter { pair -> pair.first.isHorizontal(pair.second) || pair.first.isVertical(pair.second) }
            .flatMap { pair -> extrapolate(pair) }
            .groupBy { it }
            .filter { (_, points) -> points.size > 1 }
            .count()
    }

    fun part2(input: List<String>): Int {
        println("Day05 - part2")
        val lines: List<Pair<Point, Point>> = parseInput(input)
        println("lines: $lines")

        return lines
            .filter { pair ->
                pair.first.isHorizontal(pair.second)
                        || pair.first.isVertical(pair.second)
                        || pair.first.isDiagonal(pair.second)
            }.also { println("Lines: $it") }
            .flatMap { pair -> extrapolate(pair) }.also { println("Diagonals: $it") }
            .groupBy { it }
            .filter { (_, points) -> points.size > 1 }
            .count()
    }

    val testInput = readInput("Day05_test")
    val input = readInput("Day05")

    check(part1(testInput).also { println("Answer test input part1: $it") } == 5)
    println("Answer part1: " + part1(input))

    check(part2(testInput).also { println("Answer test input part2: $it") } == 12)
    println("Answer part2: " + part2(input))
}

data class Point(val x: Int, val y: Int) {
    fun isVertical(other: Point): Boolean = x == other.x

    fun isHorizontal(other: Point): Boolean = y == other.y

    fun isDiagonal(other: Point): Boolean = abs(x - other.x) == abs(y - other.y)

    fun extrapolateVertical(other: Point): List<Point> =
        when {
            y <= other.y -> y.rangeTo(other.y).map { Point(x, it) }
            else -> y.downTo(other.y).map { Point(x, it) }
        }

    fun extrapolateHorizontal(other: Point): List<Point> =
        when {
            x <= other.x -> x.rangeTo(other.x).map { Point(it, y) }
            else -> x.downTo(other.x).map { Point(it, y) }
        }

    fun extrapolateDiagonal(other: Point): List<Point> {
        val points = mutableListOf<Point>()
        var y1 = y
        when {
            x < other.x -> x.rangeTo(other.x).forEach {
                points += Point(it, y1)
                when {
                    y < other.y -> y1++
                    else -> y1--
                }
            }
            else -> x.downTo(other.x).forEach {
                points += Point(it, y1)
                when {
                    y < other.y -> y1++
                    else -> y1--
                }
            }
        }
        return points
    }
}
