module Main where

secsToWeeks secs = let perMinute = 60
                       perHour   = 60 * perMinute
                       perDay    = 24 * perHour
                       perWeek   =  7 * perDay
                   in  secs / perWeek


classify age = case age of 0 -> "newborn"
                           1 -> "infant"
                           2 -> "toddler"
                           _ -> "senior citizen"

main = do putStrLn "age?"
          x <- readLn
          putStrLn (classify x)          
          putStrLn "secs?"
          y <- readLn
          print (secsToWeeks y)



