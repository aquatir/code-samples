defmodule ScrewsFactory do

  # Replacing Enum.map with Stream.map allows lazy evaluation
  # Using Stream.chunk_every(..) we get best of both words -> half chunked half lazy evaluation
  def run(pieces) do
    pieces
    |> Stream.chunk_every(50)
    |> Stream.flat_map(&add_thread/1)
    |> Stream.chunk_every(100)
    |> Stream.flat_map(&add_head/1)
    |> Enum.each(&output/1)
  end

  defp add_thread(pieces) do
    Process.sleep(50)
    Enum.map(pieces, &(&1 <> "--"))
  end

  defp add_head(pieces) do
    Process.sleep(100)
    Enum.map(pieces, &("o" <> &1))
  end

  defp output(screw) do
    IO.inspect(screw)
  end
end
