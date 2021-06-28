{-# LANGUAGE DeriveAnyClass #-}

class (Eq a, Enum a, Bounded a) => CyclicEnum a where
  cpred :: a -> a
  cpred d
    | d == minBound = maxBound
    | otherwise = pred d
  csucc :: a -> a
  csucc d
    | d == maxBound = minBound
    | otherwise = succ d

data Direction = North | East | South | West
    deriving (Eq, Enum, CyclicEnum, Bounded, Show)
-- instance CyclicEnum Direction


data Turn = TNone | TLeft | TRight | TAround
    deriving (Eq, Enum, Bounded, Show)

rotate :: Turn -> Direction -> Direction
rotate TNone = id
rotate TLeft = cpred
rotate TRight = csucc
rotate TAround = cpred . cpred

every :: (Enum a, Bounded a) => [a]
every = enumFrom minBound

orient :: Direction -> Direction -> Turn
orient d1 d2 = head $ filter (\t -> rotate t d1 == d2) every

rotateMany :: Direction -> [Turn] -> Direction
rotateMany = foldl (flip rotate)

rotateManySteps :: Direction -> [Turn] -> [Direction]
rotateManySteps = scanl (flip rotate)

orientMany :: [Direction] -> [Turn]
orientMany ds@(_:_:_) = zipWith orient ds (tail ds)
orientMany _ = []

-- rotateFromFile :: Direction -> FilePath -> IO ()
-- orientFromFile :: FilePath -> IO ()
main :: IO ()

main = print $ orientMany [North, South, South, North]