defmodule SomethingInsideTest do
  use ExUnit.Case
  doctest Something.Inside

  test "result is correct" do
    assert (Something.Inside.hello("Ivan") == "Hello, Ivan!")
  end
end
