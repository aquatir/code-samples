class Employee:
    class_var = "hello"

    @classmethod
    def hello_world(cls):
        return f"{cls.class_var}, world!"

    @staticmethod
    def static_hello():
        return "hello!!!"

    def __init__(self, identifier, salary, department):
        self.identifier = identifier
        self.salary = salary

        # private
        self.__department = department

    def tax(self):
        return self.salary * 0.2

    def salary_per_day(self):
        return self.salary / 30

    # private
    def __salary_per_year(self):
        return self.salary * 12

    def get_department(self):
        return self.__department


class Point:
    def __init__(self, x, y, z):
        self.x = x
        self.y = y
        self.z = z

    def sq_sum(self):
        return self.x ** 2 + self.y ** 2 + self.z ** 2


class Student:
    def __init__(self, name, phy, chem, bio):
        self.name = name
        self.phy = phy
        self.chem = chem
        self.bio = bio

    def totalObtained(self):
        return self.phy + self.chem + self.bio

    def percentage(self):
        return self.totalObtained() / 300 * 100


class Calculator:
    def __init__(self, num1, num2):
        self.num1 = num1
        self.num2 = num2

    def add(self): return self.num2 + self.num1

    def subtract(self): return self.num2 - self.num1

    def multiply(self): return self.num2 * self.num1

    def divide(self): return self.num2 / self.num1


if __name__ == '__main__':
    print("oop basics")
    emp1 = Employee(1, 100, "finance")

    print(emp1)
    emp1.title = "title"
    print(f"id: '{emp1.identifier}', salary: '{emp1.salary}', department: '{emp1.get_department()}', "
          f"title: '{emp1.title}', class_var: '{emp1.class_var}'")
    print(f"tax: '{emp1.tax()}', salary per day: '{emp1.salary_per_day()}'")
    print(f"class method: '{Employee.hello_world()}', static method: '{Employee.static_hello()}'")
    print(f"class method: '{emp1.hello_world()}', static method: '{emp1.static_hello()}'")
    print(f"access private: {emp1._Employee__department}'")  # this does work!
