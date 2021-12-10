import kotlin.math.abs

fun main() {
    fun part1(input: List<String>): Int {
        println("Day07 - part1")
        val horizontalPositionsofCrabs = input[0].split(",").map { it.toInt() }

        // brute force to the correct solution
        val fuelCostsList = mutableListOf<Int>()
        val maxPosition = horizontalPositionsofCrabs.maxOrNull() ?: 0
        (1..maxPosition).forEach { position ->
            var fuelCosts = 0
            horizontalPositionsofCrabs.forEach {
                fuelCosts += (abs(it - position))
            }
            fuelCostsList += fuelCosts
        }

        return fuelCostsList.minOrNull() ?: 0
    }

    fun part2(input: List<String>): Int {
        println("Day07 - part2")
        val horizontalPositionsofCrabs = input[0].split(",").map { it.toInt() }

        // brute force to the correct solution
        val fuelCostsList = mutableListOf<Int>()
        val maxPosition = horizontalPositionsofCrabs.maxOrNull() ?: 0
        (1..maxPosition).forEach { position ->
            var fuelCosts = 0
            horizontalPositionsofCrabs.forEach {
                val delta = abs(it - position)
                fuelCosts += (delta * (delta + 1)) / 2
            }
            fuelCostsList += fuelCosts
        }

        return fuelCostsList.minOrNull() ?: 0
    }

    val testInput = readInput("Day07_test")
    val input = readInput("Day07")

    check(part1(testInput).also { println("Answer test input part1: $it") } == 37)
    println("Answer part1: " + part1(input))

    check(part2(testInput).also { println("Answer test input part2: $it") } == 168)
    println("Answer part2: " + part2(input))
}
