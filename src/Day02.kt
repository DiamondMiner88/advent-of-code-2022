fun main() {
    d2Part1()
    d2Part2()
}

fun d2Part1() {
    val input = readInput("input/day02.txt")
    val rounds = input.split("\n")
        .filter { it.isNotBlank() }
        .map { it[0] to it[2] }

    var total = 0
    for (round in rounds) {
        val (other, me) = round
        when (me) {
            'X' -> total += 1 // rock
            'Y' -> total += 2 // paper
            'Z' -> total += 3 // sizzors
        }

        when (me) {
            'X' -> when (other) {
                'A' -> total += 3
                'B' -> total += 0
                'C' -> total += 6
            }

            'Y' -> when (other) {
                'A' -> total += 6
                'B' -> total += 3
                'C' -> total += 0
            }

            'Z' -> when (other) {
                'A' -> total += 0
                'B' -> total += 6
                'C' -> total += 3
            }
        }
    }

    println(total)
}

fun d2Part2() {
    val input = readInput("input/day02.txt")
    val rounds = input.split("\n")
        .filter { it.isNotBlank() }
        .map { it[0] to it[2] }

    var total = 0

    for (round in rounds) {
        val (other, result) = round

        when (result) {
            'X' -> total += 0 // lose
            'Y' -> total += 3 // tie
            'Z' -> total += 6 // win
        }

        when (result) {
            'X' -> when (other) {
                'A' -> total += 3
                'B' -> total += 1
                'C' -> total += 2
            }

            'Y' -> when (other) {
                'A' -> total += 1
                'B' -> total += 2
                'C' -> total += 3
            }

            'Z' -> when (other) {
                'A' -> total += 2
                'B' -> total += 3
                'C' -> total += 1
            }
        }
    }

    println(total)
}
