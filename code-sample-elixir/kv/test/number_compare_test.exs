defmodule NumberCompareTest do
  use ExUnit.Case
  doctest NumberCompare

  test "greater return second as bigger" do
    assert NumberCompare.greater(1, 2) == 2
  end

  test "greater return first as bigger" do
    assert NumberCompare.greater(3, 1) == 3
  end

  test "greater return any as bigger" do
    assert NumberCompare.greater(1, 1) == 1
  end

  test "greater2 return second as bigger" do
    assert NumberCompare.greater2(1, 2) == 2
  end

  test "greater2 return first as bigger" do
    assert NumberCompare.greater2(3, 1) == 3
  end

  test "greater2 return any as bigger" do
    assert NumberCompare.greater2(1, 1) == 1
  end
end
