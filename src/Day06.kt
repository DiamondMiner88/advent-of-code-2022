fun main() {
    d6part1()
    d6part2()
}

fun d6part1() {
    val input = readInput("input/day06.txt")

    for (i in 0 until input.length - 3) {
        val section = input.substring(i, i + 4)
        if (section.toCharArray().distinct().size != 4) continue
        println(i + 4)
        return
    }
}

fun d6part2() {
    val input = readInput("input/day06.txt")

    for (i in 0 until input.length - 13) {
        val section = input.substring(i, i + 14)
        if (section.toCharArray().distinct().size != 14) continue
        println(i + 14)
        return
    }
}
