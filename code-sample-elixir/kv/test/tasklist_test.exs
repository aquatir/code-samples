defmodule TaskListTest do
  use ExUnit.Case
  doctest TaskList

  test "save and get" do
    TaskList.reset()
    TaskList.add("kekw")
    assert TaskList.show_list() == {:ok, "[ ] kekw\n"}
  end
end
