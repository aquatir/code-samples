defmodule SortTest do
  use ExUnit.Case
  doctest Sort

  test "ascending should sort" do
    assert Sort.ascending([4, 3, 2, 1]) == [1, 2, 3, 4]
  end

  test "ascending should sort no elements" do
    assert Sort.ascending([]) == []
  end

  test "ascending should sort 1 element" do
    assert Sort.ascending([1]) == [1]
  end

  test "descending should sort" do
    assert Sort.descending([1, 2, 3, 4]) == [4, 3, 2, 1]
  end

  test "descending should sort no elements" do
    assert Sort.ascending([]) == []
  end

  test "descending should sort 1 element" do
    assert Sort.ascending([1]) == [1]
  end

  test "qsort should sort" do
    assert Sort.qsort([4, 3, 2, 1]) == [1, 2, 3, 4]
  end

  test "qsort should sort no elements" do
    assert Sort.qsort([]) == []
  end

  test "qsort should sort 1 element" do
    assert Sort.qsort([1]) == [1]
  end
end
