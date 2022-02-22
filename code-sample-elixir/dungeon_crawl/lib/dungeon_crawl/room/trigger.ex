defmodule DungeonCrawl.Room.Trigger do

  alias DungeonCrawl.Character
  alias DungeonCrawl.Room.Action

  @callback run(Character.t, Action.t) :: {Character.t, atom}
end
