fun main() {
    fun populateNextDay(lanternfish: MutableList<Int>): MutableList<Int> {
        val newDayOfLanternFish = lanternfish.toMutableList()
        (0 until lanternfish.size).forEach {
            when (lanternfish[it]) {
                0 -> {
                    newDayOfLanternFish.add(8)
                    newDayOfLanternFish[it] = 6
                }
                else -> newDayOfLanternFish[it] -= 1
            }
        }
        return newDayOfLanternFish
    }

    fun part1(input: List<String>): Int {
        println("Day06 - part1")
        var lanternfish = input[0].split(",").map { it.toInt() }.toMutableList()

        (1..80).forEach {
            lanternfish = populateNextDay(lanternfish)
            // println("After $it days: $lanternfish")
        }
        return lanternfish.count()
    }

    fun populateNextDay(fishPerTimerValue: LongArray): LongArray =
        LongArray(9).apply {
            val numberOfNewFish = fishPerTimerValue[0]
            (1..8).forEach {
                this[it - 1] = fishPerTimerValue[it]
            }
            this[8] = numberOfNewFish // new fish spwaned
            this[6] += numberOfNewFish // fish with timer 0 added to new timer of 6
        }

    fun convertListOfTimersToArrayOfFishPerTimerValue(lanternfish: MutableList<Int>): LongArray =
        LongArray(9).apply {
            lanternfish.forEach {
                this[it] += 1L
            }
        }

    fun part2(input: List<String>): Long {
        println("Day06 - part2")
        val lanternfish = input[0].split(",").map { it.toInt() }.toMutableList()

        // the number of expected fish doesn't fit into the the list, so convert the list of fish
        // to an array of internal timer values containing the number of fish with this value
        var fishPerTimerValue: LongArray = convertListOfTimersToArrayOfFishPerTimerValue(lanternfish)

        (1..256).forEach {
            fishPerTimerValue = populateNextDay(fishPerTimerValue)
        }

        // number of fish is sum of all values in array
        return fishPerTimerValue.sum()
    }

    val testInput = readInput("Day06_test")
    val input = readInput("Day06")

    check(part1(testInput).also { println("Answer test input part1: $it") } == 5934)
    println("Answer part1: " + part1(input))

    check(part2(testInput).also { println("Answer test input part2: $it") } == 26984457539)
    println("Answer part2: " + part2(input))
}
