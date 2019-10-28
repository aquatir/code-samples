{-# OPTIONS
  -Wall
  -Wno-missing-signatures
  -Wno-type-defaults
#-}

module Main where

import Data.Semigroup

data These a b 
    = This a
    | That b 
    | These a b deriving Show 

instance (Semigroup a, Semigroup b) => Semigroup (These a b) where
    This a1 <> This a2         = This (a1 <> a2)
    This a <> That b           = These a b    
    This a <> These a1 b1      = These (a <> a1) b1
    
    That b1 <> That b2         = That (b1 <> b2)
    That b <> This a           = This a <> That b
    That b <> These a1 b1      = These a1 (b <> b1)
    
    These a1 b1 <> These a2 b2 = These (a1 <> a2) (b1 <> b2)
    These a1 b2 <> This a      = This a <> These a1 b2
    These a1 b2 <> That b      = That b <> These a1 b2

instance (Monoid a, Monoid b) => Monoid (These a b) where
    mempty = These mempty mempty 


main = do
    print $ This "foo" <> That (Sum 40) <> These "!" (Sum 2)
    print $ mempty <> This "foo" <> That (Sum 40) <> mempty <> These "Last" (Sum 43) <> mempty

