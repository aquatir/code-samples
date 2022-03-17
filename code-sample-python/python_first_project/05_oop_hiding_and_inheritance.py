class Rectangle:
    def __init__(self, length, width):
        self.__length = length
        self.__width = width

    def area(self):
        return self.__length * self.__width

    def perimeter(self):
        return 2 * (self.__length + self.__width)


class Student:
    __name = None
    __roll_number = None

    def set_name(self, name):
        self.__name = name

    def get_name(self):
        return self.__name

    def set_roll_number(self, roll_number):
        self.__roll_number = roll_number

    def get_roll_number(self):
        return self.__roll_number


class Vehicle:
    fuel_cap = 90

    def __init__(self, make, color, model):
        self.make = make
        self.color = color
        self.model = model

    def print_details(self):
        print("Manufacturer:", self.make)
        print("Color:", self.color)
        print("Model:", self.model)


class Car(Vehicle):
    fuel_cap = 50

    def __init__(self, make, color, model, doors):
        # calling the constructor from parent class
        super().__init__(make, color, model)
        self.doors = doors

    def print_details(self):
        super().print_details()
        print("Doors:", self.doors)

    def display(self):
        # accessing fuelCap from the Vehicle class using super()
        print("Fuel cap from the Vehicle Class:", super().fuel_cap)

        # accessing fuelCap from the Car class using self
        print("Fuel cap from the Car Class:", self.fuel_cap)


class CombustionEngine:
    def __init__(self, tank_capacity):
        self.tank_capacity = tank_capacity


class ElectricEngine:
    def __init__(self, charge_capacity):
        self.charge_capacity = charge_capacity


# Child class inherited from CombustionEngine and ElectricEngine
class HybridEngine(CombustionEngine, ElectricEngine):

    def __init__(self, tank_capacity, charge_capacity):
        CombustionEngine.__init__(self, tank_capacity)
        ElectricEngine.__init__(self, charge_capacity)

    def print_details(self):
        print("Tank Capacity:", self.tank_capacity)
        print("Charge Capacity:", self.charge_capacity)


class Account:
    def __init__(self, title=None, balance=0):
        self.title = title
        self.balance = balance

    def withdrawal(self, amount):
        self.balance -= amount

    def deposit(self, amount):
        self.balance += amount

    def get_balance(self):
        return self.balance


class SavingsAccount(Account):
    def __init__(self, title=None, balance=0, interest_rate=0):
        super().__init__(title, balance)
        self.interest_rate = interest_rate

    def interest_amount(self):
        return self.interest_rate * self.balance / 100


if __name__ == '__main__':
    print("oop hiding")

    obj1 = Car("Suzuki", "Grey", "2015", 4)
    obj1.print_details()

    print("****")
    obj1.display()
    print("****")
    obj2 = Vehicle("Other", "White", "20200")
    obj2.print_details()

    print("****")
    car = HybridEngine("250 W", "20 Litres")
    car.print_details()
