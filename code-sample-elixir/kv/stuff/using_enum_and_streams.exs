require Integer

Enum.each(["dogs", "cats", "flowers"], &(IO.puts String.upcase(&1)))  # print all in upcase
IO.inspect Enum.map([1, 2, 3], &(&1 * &1))                            # square of all
IO.inspect Enum.reduce([1,2,3], 0, &(&1 + &2))                        # sum all into on e
IO.inspect Enum.filter([1,2,3], &(Integer.is_even(&1)))               # filter by leaving only even elements (2 in this case)
IO.inspect Enum.count([1, 2, 3])                                      # count number of elements
IO.inspect Enum.uniq(["a", "a", "b", "b", "b", "c"])                  # filter by leaving only unique elements. ["a", "b", "c"] in this case
IO.inspect Enum.sum([10, 5, 5, 10])                                   # sum elements.
IO.inspect Enum.sort(["c", "b", "d", "a"], &<=/2)                     # sort using a comparison function
IO.inspect Enum.sort(["c", "b", "d", "a"], &>=/2)
IO.inspect Enum.member?([10, 20, 12], 10)                             # check if the element exists in a list
IO.inspect Enum.join(["apples", "hot dogs", "flowers"], ", ")         # join with value

IO.inspect Enum.at([1,2,3], 2)

list = [1,2,3,4]
IO.inspect hd(list)
IO.inspect tl(list)

tuple = {:ok, "hello"}
IO.inspect tuple
new_tuple = put_elem(tuple, 1, "world")
IO.inspect new_tuple


IO.puts("")

medals = [
  %{medal: :gold, player: "Anna"},
  %{medal: :silver, player: "Joe"},
  %{medal: :gold, player: "Zoe"},
  %{medal: :bronze, player: "Anna"},
  %{medal: :silver, player: "Anderson"},
  %{medal: :silver, player: "Peter"}
]

IO.inspect Enum.group_by(medals, &(&1.medal), &(&1.player))
IO.inspect Enum.group_by(medals, &(&1.player), &(&1.medal))

IO.puts("")

# for — comprehension

IO.inspect for a <- ["dogs", "cats", "flowers"], do: String.upcase(a)
IO.inspect for a <- ["Willy", "Anna"], b <- ["Math", "English"], do: {a, b}

  ### can also filter

IO.inspect for n <- [1, 2, 3, 4, 5, 6, 7], n > 3, do: n
IO.inspect for num <- ["10", "kekw", "20"], Integer.parse(num) != :error,  do: num

IO.puts("")

# Pipelining

IO.inspect "kek" |> String.upcase |> String.first  # prints "K"

capitalize_words = fn words ->
  String.split(words)
  |> Enum.map(&String.capitalize/1)
  |> Enum.join(" ")
end

  ## same thing
capitalize_words2 = fn words ->
  words
  |> String.split
  |> Enum.map(&String.capitalize/1)
  |> Enum.join(" ")
end

IO.inspect capitalize_words.("so many words")
IO.inspect capitalize_words2.("so many words")



# Lazy evaluation

range = 1..10
IO.inspect range # does expand instantly

IO.inspect Enum.filter(range, &(&1 < 5))

  # the same range can be "reused"
IO.inspect Enum.filter(range, &(&1 >= 3))

integers_from_one = Stream.iterate(1, fn previous -> previous + 1 end)
IO.inspect Enum.take(integers_from_one, 5)

  # The stream is not "consumed" -> same result
IO.inspect Enum.take(integers_from_one, 5)

cycle = Stream.cycle(["red", "green", "blue"])
IO.inspect Enum.zip(1..10, cycle)

# flat map and chunk
IO.inspect Enum.flat_map([[1, 2], [3, 4], [5, 6]], &(&1))
IO.inspect Enum.chunk_every([1,2,3,4,5,6], 2)
