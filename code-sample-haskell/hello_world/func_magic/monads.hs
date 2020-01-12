module DiveIntoMonads where

st = print "Hello!"


monadReturn = return "WHAT" :: Maybe String  
monadOnJust = Just 9 >>= \x -> return (x*10)  
monadOnNothing = Nothing >>= \x -> return $ x ++ " that all!"


