defmodule RandomFunctions do

  @spec f_with_default(number, number) :: number
  def f_with_default(value, value_with_default \\ 10) do
    value * value_with_default
  end

  @spec f_with_two_default(number, number) :: number
  def f_with_two_default(value, another \\ 10, yet_another \\ 15) do
    value * another * yet_another
  end

  # sum from zero to n
  def up_to(0), do: 0
  def up_to(n), do: n + up_to(n - 1)

  # sum from zero to n — tail rec
  def up_to_tail_rec(n), do: up_to_tail_rec_helper(n, 0)
  defp up_to_tail_rec_helper(0, accumulator), do: accumulator
  defp up_to_tail_rec_helper(n, accumulator), do: up_to_tail_rec_helper(n - 1, accumulator + n)

  def sum_of_list(n), do: sum_of_list_helper(n, 0)
  defp sum_of_list_helper([], acc), do: acc
  defp sum_of_list_helper([head | tail], acc), do: sum_of_list_helper(tail, acc + head)

  def factorial(n) when n >= 0, do: factorial_help(n, 1)
  defp factorial_help(0, acc), do: acc
  defp factorial_help(n, acc), do: factorial_help(n - 1, acc * n)

end
