module Main where 


data Thing = This | That
instance Show Thing where
    show This = "This"
    show That = "That"

instance Eq Thing where
    This == This = True
    That == That = True
    _    == _    = False

instance Ord Thing where
    This <= That = True
    
-- instance Ord Thisng where

data Pair a = Pair a a
instance Show a => Show (Pair a) where
    show (Pair a1 a2) = "Pair[" ++ show a1 ++ ", " ++ show a2 ++ "]"

-- first, second :: Pair a -> a
-- first  (Pair a _) = a
-- second (Pair _ b) = b

instance Eq a => Eq (Pair a) where
    (Pair a1 a2) == (Pair b1 b2) = a1 == b1 && a2 == b2

instance Ord a => Ord (Pair a) where
    (Pair a1 a2)  >  (Pair b1 b2) = a1 > b1 || (a1 == b1 && a2 > b2)
    pair1 <= pair2 = not (pair1 > pair2)   
    
main = do
    print This
    print That
    print $ Pair This That
    print $ Pair "Test" "Me" 
    print $ Pair True True   == Pair True True
    print $ Pair True False  == Pair True True
    print $ Pair False True  == Pair True True
    print $ Pair False False == Pair True True
--
    print $ Pair 4 6 >= Pair 4 5
    print $ Pair 4 5 >= Pair 4 5
    print $ Pair 4 6 < Pair 4 5
    print $ Pair 4 6 <= Pair 4 5
