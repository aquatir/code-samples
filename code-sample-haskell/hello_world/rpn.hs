module ReversedPolishNotion where

-- Normal RPN which fails on malformed strings
calculateRPN :: String -> Int
calculateRPN = readAsInt . head . calculate . words

calculate :: [String] -> [String]
calculate words = foldl (\acc x -> computeValue acc x) [] words
    where
        computeValue :: [String] -> String -> [String] 
        computeValue (x:y:ys) "-" = ys ++ [ show $ (readAsInt x) - (readAsInt y)]
        computeValue (x:y:ys) "+" = ys ++ [ show $ (readAsInt x) + (readAsInt y)]
        computeValue (x:y:ys) "*" = ys ++ [ show $ (readAsInt x) * (readAsInt y)]
        computeValue (x:y:ys) "/" = ys ++ [ show $ (readAsInt x) `div` (readAsInt y)]
        computeValue xs numberAsString = xs ++ [numberAsString] 

readAsInt :: String -> Int
readAsInt x = read x :: Int


