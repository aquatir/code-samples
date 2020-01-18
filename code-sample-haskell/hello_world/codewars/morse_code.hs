module MorseCode where

import Data.Map
import Data.List.Split
import qualified Data.List as L

morseCodes :: Map String Char
morseCodes = fromList [
    ("....",'H'), 
    (".",'E'),
    ("-.--", 'Y'),
    (".---", 'J'),
    ("..-", 'U'),
    ("-..", 'D')]

decodeMorse :: String -> String
decodeMorse s = L.intercalate " " $ L.map (decodeWord) $ decodeIntoWords s 
    where decodeIntoWords = splitOn "   " 
          decodeWord   w  = L.map (decodeLetter) $ splitOn " " w
          decodeLetter a  = morseCodes ! a
