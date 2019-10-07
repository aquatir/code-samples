-- Ссылка на задание: https://gist.github.com/astynax/1eb88e195c4bab2b8d31d04921b18dd0
-- Нужно сделать только часть 1

--module ADT_TASK where


-- Откуда-нибудь заимпортить...?
data List a = Nil | Cons a (List a)              deriving (Show)
data ListNonEmpty a = ListNonEmpty a (List a)    deriving (Show)


-- Фишки и колонки 
---  
--- 1. Как задать Pillar - как конкретную имплементацию ListNonEmpty с параметром-цветом?
--- 2. Figure (Color) Pillar | Chip -  это получилась пара из цвета и либо столбца либо фишки, так? 
--- 3. А как создать с Either <Pillar, Chip> ? В чем отличие A|B и Either A|B ?
--- v     

data Color = Red | Blue                            deriving (Show) -- Доступные цвета фишки
data Chip                                           -- Бесцветная фишка
data Pillar = Pillar (ListNonEmpty Chip)            -- Столбик фишек одного цвета
data Figure = Figure Color Pillar                   -- Фигура - это непустой список фишек с цветом. 

-- Поле
---
--- 1. Как корректно задать 4х4 массив? Это ведь не список позиций...
--- v


data Position = Position Int Int             -- Координаты - это x и y  
data Field = Field Position (Maybe Figure)   -- Поле - это координаты + наличие или отсутствие фигуры на них
 
type From = Field
type To = Field

data Board = Board (ListNonEmpty (Field) )  -- Чет тут не чисто... Как сделать квадратное поле?

--
-- Ходы игроков
--
-- 1. Опять RedTurn | BlueTurn - это не Either?
-- v

data Move = Move From To       -- Движение 1 игрока - это откуда подвинуть и куда подвинуть (куда, возможно, являются фишками противника)

-- data TurnType = Movement | Attack    -- Движение - это либо перемещение своих фишек, либо атака фишек противника.
data Turn = Turn Move (Maybe Move)   -- Ход - это движение игрока одного цвета и, возможно, движение игрока другого цвета. Возможность движения и цвет определяются текущим статусом игры (GameState ниже)
data History = History (List (Turn)) --  История игры - это последовательность ходов. В принципе она не нужно, но она позволит откатываться назад. Сюда можно добавить тип хода

data InProgress = RedTurn | BlueTurn
data Done = RedWon | BlueWon 
data GameState = InProgress | Done   -- Показывает текущее состояние игры
data Game = Game Board GameState History -- Сама игра - это текущее состояние фишек на доске + история всей игры


---
--- Функции
---

-- move :: From -> To -> Game -> Maybe Game
-- 1. Проверить по From и Game.State, правильный ли игрок пытается сделать ход
-- 2. Проверить по From и To, соседние ли они
-- 3. Проверить по цвету From и To движение это или атака. Если движение -> подвинуть. Если атака -> убрать 1 фишку у врага. Если фишек осталось больше 0 -> убрать одну у атакующего.
-- 4. Обновить в Board поля From и To.
-- 5. Добавить запись в History. Возможно с TurnType
   



-- gameIsOver:: Game -> Maybe Done
-- 1. Проверить GameState. Если InProgress -> Nothing, иначе -> Done


main = print "kek cheburek"

