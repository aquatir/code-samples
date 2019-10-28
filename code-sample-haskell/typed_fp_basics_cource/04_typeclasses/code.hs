module Main where 

-- import Prelude hiding (map, filter, zip, taske, head, zipWith, zipWith3, takeWhile, dropWhile, break, foldr, foldl)

class CanFoo a where
   toFoo :: a -> String
   
instance CanFoo Int where
   toFoo 42 = "42!"
   toFoo _ = "Oops!"


main = do
   print $ toFoo (42::Int) 
