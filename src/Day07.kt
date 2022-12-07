import kotlin.math.abs
import kotlin.math.max

fun main() {
    val input = readInput("input/day07.txt")
        .split("\n")
        .filter { it.isNotBlank() }

    var cd = ""
    val files = mutableMapOf<String, Int>()
    var curCmd: String? = null
    for (line in input) {
        if (line[0] == '$') {
            curCmd = line.substring(2)

            when {
                curCmd.startsWith("cd") -> {
                    val dir = line.substring(5)
                    if (dir == "..") {
                        cd = cd.substring(0, cd.lastIndexOf("/"))
                        if (cd == "")
                            cd = "/"
                    } else if (dir == "/") {
                        cd = "/"
                    } else {
                        if (cd == "/") {
                            cd = "/$dir"
                        } else {
                            cd = "$cd/$dir"
                        }
                    }
                    curCmd = null
                }

                curCmd == "ls" -> {
                    curCmd = "ls"
                }
            }
        } else if (curCmd == "ls") {
            val splits = line.split(" ")
            if (splits[0].all { it.isDigit() }) {
                if (cd == "/") {
                    files["/${splits[1]}"] = splits[0].toInt()
                } else {
                    files["$cd/${splits[1]}"] = splits[0].toInt()
                }
            }
        }
    }

    val allDirs = files.flatMap {
        val names = mutableListOf<String>()
        for (i in 0 until it.key.length) {
            if (it.key[i] == '/') {
                names += it.key.substring(0, i)
            }
        }
        names
    }.distinct().filter { it.isNotBlank() } + "/"

    val dirSizes = allDirs.associateWith {
        var totalSize = 0
        for ((file, size) in files) {
            if (file.substring(0, max(file.lastIndexOf("/"), 1)).startsWith("$it"))
                totalSize += size
        }
        totalSize
    }

    var part1 = 0
    for (size in dirSizes.values)
        if (size <= 100000) part1 += size
    println(part1)

    val neededSpace = abs(70000000 - dirSizes["/"]!! - 30000000)
    var part2 = 70000000
    for (size in dirSizes.values)
        if (neededSpace <= size && part2 > size)
            part2 = size
    println(part2)
}
