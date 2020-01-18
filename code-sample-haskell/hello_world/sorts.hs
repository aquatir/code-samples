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


