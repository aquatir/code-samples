defmodule Mix.Tasks.Start do
  use Mix.Task

  def run(_), do: DungeonCrawl.CLI.Main.start_game
end
