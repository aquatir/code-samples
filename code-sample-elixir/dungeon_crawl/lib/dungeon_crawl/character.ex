defmodule DungeonCrawl.Character do
  defstruct name: nil,
            description: nil,
            hit_points: 0,
            max_hit_points: 0,
            attack_description: nil,
            damage_range: nil

  # a protocol which simplifies printing
  defimpl String.Chars do
    def to_string(character), do: character.name
  end

end
