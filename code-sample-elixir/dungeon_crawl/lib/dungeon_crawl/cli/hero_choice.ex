defmodule DungeonCrawl.CLI.HeroChoice do
  alias Mix.Shell.IO, as: Shell
  import DungeonCrawl.CLI.BaseCommands

  def start do
      Shell.cmd("clear")
      Shell.info("Start by choosing your hero:")

      DungeonCrawl.Heroes.all()
      |> ask_for_option
      |> confirm_hero
  end

  defp confirm_hero(chosen_hero) do
      Shell.cmd("clear")
      Shell.info(chosen_hero.description)
      if Shell.yes?("Confirm?"), do: chosen_hero, else: start()
  end
end
