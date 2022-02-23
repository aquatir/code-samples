defmodule Shop do
  def checkout() do

    # Using with clase: only if all results are captured the do part is executed
    with {quantity, _} <- ask_number("Quantity?"),
         {price, _} <- ask_number("Price?") do quantity * price
    else
      :error ->
        IO.puts("It's not a number")
    end
  end

  def ask_number(message) do
    message <> "\n"
      |> IO.gets
      |> Integer.parse
  end
end
