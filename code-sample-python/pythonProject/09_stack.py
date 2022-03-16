class Stack():
    def __init__(self):
        self.items = []

    def push(self, item):
        self.items.append(item)

    def pop(self):
        return self.items.pop()

    def is_empty(self):
        return self.items == []

    def peek(self):
        if not self.is_empty():
            return self.items[-1]

    def get_stack(self):
        return self.items


def is_match(p1, p2):
    if p1 == "(" and p2 == ")":
        return True
    elif p1 == "{" and p2 == "}":
        return True
    elif p1 == "[" and p2 == "]":
        return True
    else:
        return False


def is_paren_balanced(paren_string):
    s = Stack()
    is_balanced = True
    index = 0

    while index < len(paren_string) and is_balanced:
        paren = paren_string[index]
        if paren in "([{":
            s.push(paren)
        else:
            if s.is_empty():
                is_balanced = False
                break
            else:
                top = s.pop()
                if not is_match(top, paren):
                    is_balanced = False
                    break
        index += 1

    if s.is_empty() and is_balanced:
        return True
    else:
        return False


def convert_int_to_bin(dec_num):
    stack = Stack()

    while dec_num != 0:
        rem = dec_num % 2
        stack.push(rem)
        dec_num = dec_num // 2

    res = ""
    while not stack.is_empty():
        res += str(stack.pop())

    return res


if __name__ == '__main__':
    stack = Stack()

    stack.push(1)
    stack.push(2)
    print(stack.get_stack())
    print(stack.pop(), stack.pop())

    # True False True False
    print(is_paren_balanced("(())"), is_paren_balanced("(()))"), is_paren_balanced("[({})]"),
          is_paren_balanced("[({)]}"))

    print(convert_int_to_bin(3), convert_int_to_bin(7), convert_int_to_bin(4))
