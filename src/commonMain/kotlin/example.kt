import arrow.analysis.pre

fun problem(numbers: List<Int>): Int =
  0 + numbers[0] + numbers[1] // <- problems!

fun average(numbers: List<Int>): Int {
  pre(!numbers.isEmpty()) { "list is not empty" }
  return numbers.sum() / numbers.size
}
