main = interact wordCount
    where wordCount input = show (sum (map length (words input))) ++ "\n"

