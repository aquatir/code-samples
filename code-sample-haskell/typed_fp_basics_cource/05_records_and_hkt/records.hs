module Main where

-- Функтор - это штука, которая подменяет значение внутри контейнера 
-- Эндофунктор - это штука, которая подменяет значение внутри контейнера, но не меняет его тип!
-- class Functor f where
--     fmap :: (a -> b) -> f a -> f b
-- 
-- instance Functor Maybe where
--    fmap :: (a -> b) -> Maybe a -> Maybe b    
--
-- 
-- 
-- 
-- 

data Foo a = Foo 
    { fiz :: Int
    , baz :: a
    } deriving Show

main = do
    let a = Foo 42 10
    print $ a
    let b = a { fiz = 5 }
    print $ b

   
