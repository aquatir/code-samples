defmodule TaskList do

  ## Import two functions from File so we could write just "write(..)" and not "File.write()"
  import File, only: [write: 3, read: 1]

  ## Module attribute.
  @file_name "task_list.md"

  def add(task_name) do
    task = "[ ] " <> task_name <> "\n"
    write(@file_name, task, [:append])
  end

  def show_list do
    read(@file_name)
  end

  def reset do
    write(@file_name, "", [:write])
  end
end
