module Codewars.G964.Getmiddle where

getMiddle :: String -> String
getMiddle [] = []
getMiddle xs 
    | odd len = [xs !! half] 
    | otherwise = [(xs !! (half-1)), (xs !! half)]
    where len = length xs
          half = len `div` 2
