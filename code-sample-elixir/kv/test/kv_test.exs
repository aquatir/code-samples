defmodule KvTest do
  use ExUnit.Case
  doctest Kv

  test "greets the world" do
    assert Kv.hello() == :world
  end
end
