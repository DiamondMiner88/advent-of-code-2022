fun main() {
    val input = readInput("input/day08.txt")
        .split("\n")
        .filter { it.isNotBlank() }

    val map: List<List<Int>> = input.map { row ->
        row.toList().map { it.digitToInt() }
    }

    val size = map.size

    val visibleMap = map.mapIndexed { rowI, row ->
        row.mapIndexed { colI, height ->
            if (colI == 0 || colI == size - 1 || rowI == 0 || rowI == size - 1)
                true
            else {
                val leftTrees = row.slice(0 until colI)
                val rightTrees = row.slice(colI + 1 until size)
                val topTrees = (0 until rowI).map { map[it][colI] }
                val btmTrees = (rowI + 1 until size).map { map[it][colI] }


                if (leftTrees.max() < height) true
                else if (rightTrees.max() < height) true
                else if (topTrees.max() < height) true
                else btmTrees.max() < height
            }
        }
    }

    println(visibleMap.map { it.count { it } }.sum())

    val scenicScores = map.mapIndexed { rowI, row ->
        row.mapIndexed { colI, height ->
            val leftTrees = row.slice(0 until colI).reversed()
            val rightTrees = row.slice(colI + 1 until size)
            val topTrees = (0 until rowI).map { map[it][colI] }.reversed()
            val btmTrees = (rowI + 1 until size).map { map[it][colI] }

            val leftScore = run {
                var cnt = 0
                leftTrees.forEach { tree ->
                    cnt++
                    if (tree >= height)
                        return@run cnt
                }
                cnt
            }

            val rightScore = run {
                var cnt = 0
                rightTrees.forEach { tree ->
                    cnt++
                    if (tree >= height)
                        return@run cnt
                }
                cnt
            }

            val topScore = run {
                var cnt = 0
                topTrees.forEach { tree ->
                    cnt++
                    if (tree >= height)
                        return@run cnt
                }
                cnt
            }

            val btmScore = run {
                var cnt = 0
                btmTrees.forEach { tree ->
                    cnt++
                    if (tree >= height)
                        return@run cnt
                }
                cnt
            }

            leftScore * rightScore * topScore * btmScore
        }
    }
    println(scenicScores.map { it.max() }.max())
}
