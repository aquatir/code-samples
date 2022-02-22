{age, _} = Integer.parse IO.gets("Person's age:\n")

# cond can be used like a "switch" statement, but it never falls down on a match
# also if the match is not found (not exclusive), an error would be generated
#  > (CondClauseError) no cond clause evaluated to a truthy value
result = cond do
  age < 13 -> "kid"
  age < 18 -> "teen"
  age >= 18 -> "adult"
end

IO.puts "Result: #{result}"
