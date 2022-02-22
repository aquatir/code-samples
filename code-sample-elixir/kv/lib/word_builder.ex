defmodule WordBuilder do
  def build(alphabet, positions) do

    # one way
    letters = Enum.map(positions, fn at -> String.at(alphabet, at) end)

    # Easier to read way: first create a function capturing "alphabet". Then pass in to Enum.map. Either does the same
    # partial = fn at -> String.at(alphabet, at) end
    # letters = Enum.map(positions, partial)
    Enum.join(letters)
  end
end
