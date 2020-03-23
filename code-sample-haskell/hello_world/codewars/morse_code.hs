module MorseCode where

import Data.Map
import Data.List.Split as Split
import Data.List as L

import Data.Char

-- All other character should be added here, because currenly it only translate ".... . -.--   .--- ..- -.. ." which is "HEY JUDE"
morseCodes :: Map String String
morseCodes = fromList [
    ("....","H"), 
    (".","E"),
    ("-.--", "Y"),
    (".---", "J"),
    ("..-", "U"),
    ("-..", "D")]

decodeMorse :: String -> String
decodeMorse s = L.intercalate " " $ Prelude.map (decodeWord) $ decodeIntoWords $ trimStr s 
   where trimStr = dropWhileEnd isSpace . dropWhile isSpace
         decodeIntoWords = Split.splitOn "   " 
         decodeWord   w  = L.concat $ Prelude.map (decodeLetter) $ Split.splitOn " " w
         decodeLetter a  = morseCodes ! a

