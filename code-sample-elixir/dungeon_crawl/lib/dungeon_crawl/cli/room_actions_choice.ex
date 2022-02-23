defmodule DungeonCrawl.CLI.RoomActionsChoice do
  alias Mix.Shell.IO, as: Shell
  import DungeonCrawl.CLI.BaseCommands

  def start(room) do
      Shell.info(room.description)
      chosen_action = ask_for_option(room.actions)
      {room, chosen_action}
  end
end
