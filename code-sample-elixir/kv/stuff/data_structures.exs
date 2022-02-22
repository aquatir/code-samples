# a normal map
map = %{first: 123, second: 343}
IO.inspect(map)

# a normal list.
# can also pattern maths with [haed | tail] or even [head, after_head | tail]
list = [1, 2, 3]
IO.inspect(list)

# an associative list. Each element is a tuple. Used a lot in the language itself, e.g. in imports
list_mapped = [a: 5, a: 10]
IO.inspect(list_mapped)

# data is a Struct which is like a map, but slightly more confined
date = ~D[2018-01-01]
%{day: day, month: month, year: year, calendar: calendar} = date
IO.puts("day: #{day} month: #{month} year: #{year} calendar: #{calendar}")

# you can pattern match with struct name. Assigning a map with the same fields would fail in such a call
%Date{day: day} = date
IO.puts("day: #{day}")

# This thing is called "Sigil". It allows you to magically simplify programs. Should read up about them at some point.
w_signil = ~w(word AND word AND otherWord)
IO.inspect(w_signil)
