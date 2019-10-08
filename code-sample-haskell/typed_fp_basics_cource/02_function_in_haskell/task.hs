-- Ссылка на задание: https://gist.github.com/astynax/1eb88e195c4bab2b8d31d04921b18dd0
-- Это тоже самое задание, что в части 1, только сделанное прямо на занятии по-другому

module Main where

data Row a = Row a a a a

data Color = Red | Blue

data Field = 
    Field (Row (Row (Maybe Color)))


empty :: Field
empty = Field (Row r r r r)
    where 
        r = Row 
            (Nothing)
            (Nothing)
            (Nothing)
            (Nothing)

main = putStrLn $ drawField empty


drawField :: Field -> String
drawField (Field r) = 
    unlines 
        $ map fromRow 
            $ toList r

toList :: Row a -> [a]
toList (Row a b c d) = [a, b, c, d] 

fromRow :: Row (Maybe Color) -> String
fromRow r = 
    unwords 
        $ map fromCell 
            $ toList r

fromCell :: Maybe Color -> String
fromCell (Just Red)   = "R"
fromCell (Just Blue)  = "B"
fromCell _            = "."   
