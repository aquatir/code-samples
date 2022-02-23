defmodule DungeonCrawl.Room.Triggers.EnemyHidden do
  alias DungeonCrawl.Room.Action
  alias Mix.Shell.IO, as: Shell

  @behaviour DungeonCrawl.Room.Trigger

  def run(character, %Action{id: :forward}) do
    Shell.info("You're walking cautiously and can see the next room.")
    {character, :forward}
  end

  def run(character, %Action{id: :search}) do
    Shell.info("You start searching the room.")
    enemy_encounter(character)
  end

  def run(character, %Action{id: :rest}) do
    Shell.info("You search the room for a comfortable place to rest.")
    enemy_encounter(character)
  end

  defp enemy_encounter(character) do
    enemy = Enum.random(DungeonCrawl.Enemies.all)

    Shell.info("Suddenly...")
    Shell.info(enemy.description)
    Shell.info("The enemy #{enemy.name} surprises you and attacks first.")

    {_enemy, updated_char} = DungeonCrawl.Battle.fight(enemy, character)

    {
      updated_char,
      :forward
    }
  end
end
