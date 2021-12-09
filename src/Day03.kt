fun main() {

    fun convertLineToListOfInt(line: String): List<Int> {
        return line.map {
            when (it) {
                '1' -> 1
                else -> 0
            }
        }.toList()
    }

    fun updateMap(i: Int, map: MutableMap<Int, Int>) {
        map[i] = when (map[i]) {
            null -> 1
            else -> map[i]!!.plus(1)
        }
    }

    fun determineNextLists(i: Int, inputList: List<List<Int>>, common: Common): List<List<Int>> {
        val zerosList = mutableListOf<List<Int>>()
        val onesList = mutableListOf<List<Int>>()
        val mapOfNumberOfZeros = mutableMapOf<Int, Int>()
        val mapOfNumberOfOnes = mutableMapOf<Int, Int>()

        inputList.forEach {
            when (it[i]) {
                1 -> {
                    updateMap(i, mapOfNumberOfOnes)
                    onesList.add(it)
                }
                else -> {
                    updateMap(i, mapOfNumberOfZeros)
                    zerosList.add(it)
                }
            }
        }
        return when (common) {
            Common.MOST -> when {
                mapOfNumberOfOnes[i]!! >= mapOfNumberOfZeros[i]!! -> onesList
                else -> zerosList
            }
            else -> when {
                mapOfNumberOfOnes[i]!! < mapOfNumberOfZeros[i]!! -> onesList
                else -> zerosList
            }
        }
    }

    fun part1(input: List<String>): Int {
        println("Day03 - part1")
        val size = input[0].length // size of the bit array
        val mapOfNumberOfZeros = mutableMapOf<Int, Int>()
        val mapOfNumberOfOnes = mutableMapOf<Int, Int>()
        input.map { convertLineToListOfInt(it) }
            .forEach {
                var i = 0; it.forEach { bit ->
                when (bit) {
                    1 -> updateMap(i, mapOfNumberOfOnes)
                    else -> updateMap(i, mapOfNumberOfZeros)
                }
                i++
            }
            }
        var gammaBits = ""
        var epsilonBits = ""
        (0 until size).forEach { i ->
            when {
                mapOfNumberOfOnes[i]!! > mapOfNumberOfZeros[i]!! -> {
                    gammaBits += '1'
                    epsilonBits += '0'
                }
                else -> {
                    gammaBits += '0'
                    epsilonBits += '1'
                }
            }
        }
        val gammaRate = gammaBits.toInt(2).also { println(it) }
        val epsilonRate = epsilonBits.toInt(2).also { println(it) }
        return gammaRate * epsilonRate
    }

    fun part2(input: List<String>): Int {
        println("Day03 - part2")
        val size = input[0].length // size of the bit array
        val inputList = input.map { convertLineToListOfInt(it) }.toList()

        var tempList = inputList
        (0 until size).forEach {
            if (tempList.size != 1) {
                tempList = determineNextLists(it, tempList, Common.MOST)
            }
        }

        val oxygenRate = tempList[0].joinToString("").toInt(2)
        println(oxygenRate)

        tempList = inputList
        (0 until size).forEach {
            if (tempList.size != 1) {
                tempList = determineNextLists(it, tempList, Common.LEAST)
            }
        }
        val co2Rate = tempList[0].joinToString("").toInt(2).also { println(it) }
        return oxygenRate * co2Rate
    }

    val testInput = readInput("Day03_test")
    val input = readInput("Day03")


    check(part1(testInput).also { println("Answer test input part1: $it") } == 198)
    println("Answer part1: " + part1(input))

    check(part2(testInput).also { println("Answer test input part2: $it") } == 230)
    println("Answer part2: " + part2(input))
}

private enum class Common {
    MOST, LEAST
}