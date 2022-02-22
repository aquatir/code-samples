defmodule WordBuilderTest do
  use ExUnit.Case
  doctest WordBuilder

  test "build is correct" do
    assert (WordBuilder.build("abcde", [1,0,3])) == "bad"
  end

end
