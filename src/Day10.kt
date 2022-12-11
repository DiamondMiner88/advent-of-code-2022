fun main() {
    d10part1()
    d10part2()
}

// does not work

fun d10part1() {
    val input = ("addx 15\n" +
            "addx -11\n" +
            "addx 6\n" +
            "addx -3\n" +
            "addx 5\n" +
            "addx -1\n" +
            "addx -8\n" +
            "addx 13\n" +
            "addx 4\n" +
            "noop\n" +
            "addx -1\n" +
            "addx 5\n" +
            "addx -1\n" +
            "addx 5\n" +
            "addx -1\n" +
            "addx 5\n" +
            "addx -1\n" +
            "addx 5\n" +
            "addx -1\n" +
            "addx -35\n" +
            "addx 1\n" +
            "addx 24\n" +
            "addx -19\n" +
            "addx 1\n" +
            "addx 16\n" +
            "addx -11\n" +
            "noop\n" +
            "noop\n" +
            "addx 21\n" +
            "addx -15\n" +
            "noop\n" +
            "noop\n" +
            "addx -3\n" +
            "addx 9\n" +
            "addx 1\n" +
            "addx -3\n" +
            "addx 8\n" +
            "addx 1\n" +
            "addx 5\n" +
            "noop\n" +
            "noop\n" +
            "noop\n" +
            "noop\n" +
            "noop\n" +
            "addx -36\n" +
            "noop\n" +
            "addx 1\n" +
            "addx 7\n" +
            "noop\n" +
            "noop\n" +
            "noop\n" +
            "addx 2\n" +
            "addx 6\n" +
            "noop\n" +
            "noop\n" +
            "noop\n" +
            "noop\n" +
            "noop\n" +
            "addx 1\n" +
            "noop\n" +
            "noop\n" +
            "addx 7\n" +
            "addx 1\n" +
            "noop\n" +
            "addx -13\n" +
            "addx 13\n" +
            "addx 7\n" +
            "noop\n" +
            "addx 1\n" +
            "addx -33\n" +
            "noop\n" +
            "noop\n" +
            "noop\n" +
            "addx 2\n" +
            "noop\n" +
            "noop\n" +
            "noop\n" +
            "addx 8\n" +
            "noop\n" +
            "addx -1\n" +
            "addx 2\n" +
            "addx 1\n" +
            "noop\n" +
            "addx 17\n" +
            "addx -9\n" +
            "addx 1\n" +
            "addx 1\n" +
            "addx -3\n" +
            "addx 11\n" +
            "noop\n" +
            "noop\n" +
            "addx 1\n" +
            "noop\n" +
            "addx 1\n" +
            "noop\n" +
            "noop\n" +
            "addx -13\n" +
            "addx -19\n" +
            "addx 1\n" +
            "addx 3\n" +
            "addx 26\n" +
            "addx -30\n" +
            "addx 12\n" +
            "addx -1\n" +
            "addx 3\n" +
            "addx 1\n" +
            "noop\n" +
            "noop\n" +
            "noop\n" +
            "addx -9\n" +
            "addx 18\n" +
            "addx 1\n" +
            "addx 2\n" +
            "noop\n" +
            "noop\n" +
            "addx 9\n" +
            "noop\n" +
            "noop\n" +
            "noop\n" +
            "addx -1\n" +
            "addx 2\n" +
            "addx -37\n" +
            "addx 1\n" +
            "addx 3\n" +
            "noop\n" +
            "addx 15\n" +
            "addx -21\n" +
            "addx 22\n" +
            "addx -6\n" +
            "addx 1\n" +
            "noop\n" +
            "addx 2\n" +
            "addx 1\n" +
            "noop\n" +
            "addx -10\n" +
            "noop\n" +
            "noop\n" +
            "addx 20\n" +
            "addx 1\n" +
            "addx 2\n" +
            "addx 2\n" +
            "addx -6\n" +
            "addx -11\n" +
            "noop\n" +
            "noop\n" +
            "noop")
        .split("\n")
        .filter { it.isNotBlank() }
//    val input = readInput("input/day10.txt")
//        .split("\n")
//        .filter { it.isNotBlank() }
//    val input = ("noop\n" +
//            "addx 3\n" +
//            "addx -5")
//        .split("\n")
//        .filter { it.isNotBlank() }

    var xReg = 1
    val xRegHistory = mutableListOf<Int>()
    val queued = mutableListOf<Int>()

    var cnt = 0
    val iter = input.iterator()
    while (iter.hasNext() || queued.isNotEmpty()) {
        val inst = if (iter.hasNext()) {
            iter.next()
        } else {
            ""
        }

        println("${++cnt}")
        println("before: $inst, $xReg $queued")
        xRegHistory += xReg

        if (queued.isNotEmpty()) {
            xReg += queued.removeAt(0)
        }
        if (inst.startsWith("addx")) {
            queued += inst.slice(5 until inst.length).toInt()
        }

        println("after: $inst $xReg $queued")
    }

    println(xRegHistory)

    val cycleNumbers = arrayOf(20, 60, 100, 140, 180, 220)
    val strengths = cycleNumbers.map { xRegHistory.getOrElse(it) { 0 } * it }
    println(strengths)
    println(strengths.sum())
}

fun d10part2() {

}
