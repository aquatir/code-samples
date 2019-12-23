-- quicksort
quicksort :: (Ord a) => [a] -> [a]
quicksort []     = []
quicksort (x:xs) = left ++ [x] ++ right
    where 
        left  = quicksort $ filter (<=x) xs
        right = quicksort $ filter (>x) xs


-- mergesort
mergesort :: (Ord a) => [a] -> [a]
mergesort []      = []
mergesort [x]     = [x]
mergesort [x,y]  
    | x <= y      = [x] ++ [y]
    | otherwise   = [y] ++ [x]
mergesort (x:y:rest) = merge (mergesort [x,y]) (mergesort rest) 

merge :: (Ord a) => [a] -> [a] -> [a]
merge [] x = x
merge x [] = x
merge l1@(x:xs) l2@(y:ys)
    | x < y     = x : merge xs l2
    | x > y     = y : merge l1 ys
    | otherwise = x:y : merge xs ys


-- Reversed polish notation calculator
calculateRPN :: String -> Int
calculateRPN = readAsInt . head . calculate . words

calculate :: [String] -> [String]
calculate words = foldl (\acc x -> computeValue acc x) [] words

computeValue :: [String] -> String -> [String]
computeValue (x:y:ys) "-" = ys ++ [ show $ (readAsInt x) - (readAsInt y)]
computeValue (x:y:ys) "+" = ys ++ [ show $ (readAsInt x) + (readAsInt y)]
computeValue (x:y:ys) "*" = ys ++ [ show $ (readAsInt x) * (readAsInt y)]
computeValue (x:y:ys) "/" = ys ++ [ show $ (readAsInt x) `div` (readAsInt y)]
computeValue xs numberAsString = xs ++ [numberAsString] 

readAsInt :: String -> Int
readAsInt x = read x :: Int

