defmodule NumberCompare do
  def greater(number, other_number) do
    check(number >= other_number, number, other_number)
  end

  # these are called "function clauses"
  # elixir will execute the first one that matches the pattern
  # defp == "define private". Those are not visible outside the module
  defp check(true, number, _), do: number
  defp check(false, _, other_number), do: other_number

  # would be analyzed from top to bottom
  #    could either use do: notation as the short form, or do/end as the long form
  @spec greater2(any, any) :: any
  def greater2(number, other_number) when number >= other_number do
    number
  end

  def greater2(_, other_number), do: other_number
end
