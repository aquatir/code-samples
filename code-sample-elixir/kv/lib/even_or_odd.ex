defmodule EvenOrOdd do

  # Interge.is_even is a macro, so we use require instead of import
  require Integer

  def check(number) when Integer.is_even(number), do: "even"
  def check(number) when Integer.is_odd(number), do: "odd"
end
