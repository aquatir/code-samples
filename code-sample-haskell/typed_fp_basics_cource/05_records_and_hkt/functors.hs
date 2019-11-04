{-# LANGUAGE InstanceSigs #-}

{-# OPTIONS
  -Wall
  -Wno-missing-signatures
  -Wno-type-defaults
#-}




module Main where

import Data.Bifunctor


-- Функтор - это штука, которая подменяет значение внутри контейнера 
-- Эндофунктор - это штука, которая подменяет значение внутри контейнера, но не меняет его тип!
-- class Functor f where
--     fmap :: (a -> b) -> f a -> f b
-- 
-- instance Functor Maybe where
--    fmap :: (a -> b) -> Maybe a -> Maybe b    
--

data Pair a = Pair a a  deriving Show

instance Functor Pair where
    fmap :: (a -> b) -> Pair a -> Pair b
    fmap f (Pair a1 a2) = Pair (f a1) (f a2)


data TwoPair a b = TwoPair a b deriving Show

instance Functor (TwoPair a) where
    fmap :: (from -> to) -> TwoPair a from -> TwoPair a to
    fmap f (TwoPair a a2) = TwoPair a (f a2)

instance Bifunctor TwoPair where
    first  :: (from -> to) -> TwoPair from c -> TwoPair to c 
    second :: (from -> to) -> TwoPair a from -> TwoPair a to  
    
    first  f (TwoPair a b) = TwoPair (f a) b
    second f (TwoPair a b) = TwoPair a (f b)
    
-- Задание 2 (хардкорное)
-- Реализовать Foldable через fold. Для этого придется изобрести (или подглядеть в Data.Monoid) обёртки-моноиды, так как в сигнатуре fold есть моноид. 
data List a = Cons a (List a) | Nil


-- Задание 1 
-- Реализовать для типа ниже Monoid, Functor, Bifunctor и 2 функции
-- fromList :: [Either a b] -> KeepFlip a b
-- toList   :: KeepFlip a b -> [Either a b]
data KeepFlip a b
  = Keep a (KeepFlip a b)
  | Flip b (KeepFlip b a)
  | End

instance Monoid (KeepFlip a b) where  
    mempty = End
    
    mappend a End = a 
    mappend End b = b
    mappend (Keep _ _) (Keep _ _) = undefined  -- Каждый это еще пачка из 4...?
    mappend (Flip _ _) (Keep _ _) = undefined
    mappend (Keep _ _) (Flip _ _) = undefined
    mappend (Flip _ _) (Flip _ _) = undefined


main = do
    print pair 
    print pairAfterApply
    where 
        pair = Pair "hello" "world"
        pairAfterApply = fmap (++ "!") $ pair

   
