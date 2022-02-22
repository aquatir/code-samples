defmodule EvenOrOddTest do
  use ExUnit.Case
  doctest EvenOrOdd

  test "check return even for even" do
    assert (EvenOrOdd.check(2) == "even")
  end

  test "check return odd for odd" do
    assert (EvenOrOdd.check(1) == "odd")
  end
end
