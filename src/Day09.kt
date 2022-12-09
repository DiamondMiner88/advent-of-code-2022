import kotlin.math.abs

fun main() {
    d9part1()
    d9part2()
}

fun move(target: Pair<Int, Int>, src: Pair<Int, Int>, exact: Boolean): Pair<Int, Int> {
    val (hX, hY) = target
    val (tX, tY) = src

    if (!exact) {
        if (
            abs(hX - tX) <= 1 &&
            abs(hY - tY) <= 1
        ) {
            return src
        }
    }

    return if (hX != tX && hY != tY) {
        var y = hY - tY
        if (y > 1) y = 1
        else if (y < 1) y = -1

        var x = hX - tX
        if (x > 1) x = 1
        else if (x < 1) x = -1

        tX + x to tY + y
    } else {
        if (hX == tX) {
            var y = hY - tY
            if (y > 1) y = 1
            else if (y < 1) y = -1

            tX to tY + y
        } else {
            var x = hX - tX
            if (x > 1) x = 1
            else if (x < 1) x = -1

            tX + x to tY
        }
    }
}

fun d9part1() {
    val input = readInput("input/day09.txt")
        .split("\n")
        .filter { it.isNotBlank() }

    var hPos = 0 to 0
    var tPos = 0 to 0
    val allTPos = mutableListOf(0 to 0)

    for (inst in input) {
        val dir = inst[0]
        val count = inst.slice((2 until inst.length)).toInt()

        val targetPos = when (dir) {
            'L' -> {
                hPos.copy(first = hPos.first - count)
            }

            'R' -> {
                hPos.copy(first = hPos.first + count)
            }

            'D' -> {
                hPos.copy(second = hPos.second - count)
            }

            'U' -> {
                hPos.copy(second = hPos.second + count)
            }

            else -> error("guh")
        }

        while (
            abs(targetPos.first - hPos.first) >= 1 ||
            abs(targetPos.second - hPos.second) >= 1
        ) {
            hPos = move(targetPos, hPos, true)
            tPos = move(hPos, tPos, false)

            allTPos += tPos
        }
    }

    println(allTPos.distinct().size)
}

fun d9part2() {
    val input = readInput("input/day09.txt")
        .split("\n")
        .filter { it.isNotBlank() }

    var hPos = 0 to 0
    var tPos1 = 0 to 0
    var tPos2 = 0 to 0
    var tPos3 = 0 to 0
    var tPos4 = 0 to 0
    var tPos5 = 0 to 0
    var tPos6 = 0 to 0
    var tPos7 = 0 to 0
    var tPos8 = 0 to 0
    var tPos9 = 0 to 0
    val allTPos9 = mutableListOf(0 to 0)

    for (inst in input) {
        val dir = inst[0]
        val count = inst.slice((2 until inst.length)).toInt()

        val targetPos = when (dir) {
            'L' -> {
                hPos.copy(first = hPos.first - count)
            }

            'R' -> {
                hPos.copy(first = hPos.first + count)
            }

            'D' -> {
                hPos.copy(second = hPos.second - count)
            }

            'U' -> {
                hPos.copy(second = hPos.second + count)
            }

            else -> error("guh")
        }

        while (
            abs(targetPos.first - hPos.first) >= 1 ||
            abs(targetPos.second - hPos.second) >= 1
        ) {
            hPos = move(targetPos, hPos, true)
            tPos1 = move(hPos, tPos1, false)
            tPos2 = move(tPos1, tPos2, false)
            tPos3 = move(tPos2, tPos3, false)
            tPos4 = move(tPos3, tPos4, false)
            tPos5 = move(tPos4, tPos5, false)
            tPos6 = move(tPos5, tPos6, false)
            tPos7 = move(tPos6, tPos7, false)
            tPos8 = move(tPos7, tPos8, false)
            tPos9 = move(tPos8, tPos9, false)

            allTPos9 += tPos9
        }
    }

    println(allTPos9.distinct().size)
}
