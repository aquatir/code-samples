import re

if __name__ == '__main__':
    print("regex starting")

    text = 'abcdfghijk'
    parser = re.search('a[b-f]*f', text)
    print(parser.group())

    print("****")

    text = "The ants go marching one by one"

    strings = ['the', 'one']

    for string in strings:
        regex = re.compile(string)
        match = re.search(regex, text)
        if match:
            print('Found "{}" in "{}"'.format(string, text))
            text_pos = match.span()
            print(text[match.start():match.end()], text[text_pos[0]:text_pos[1]])
        else:
            print('Did not find "{}"'.format(string))
    print("****")

    silly_string = "the cat in the hat"
    pattern = "the"
    print(re.findall(pattern, silly_string))
    print("****")

    for match in re.finditer(pattern, silly_string):
        s = "Found '{group}' at {begin}:{end}".format(
            group=match.group(), begin=match.start(),
            end=match.end())
        print(s, end="; ")
    print()
    print("****")

    string = r"\point \property"
    pattern = "\\\p"
    result = re.findall(pattern, string)
    print(result, result == [r"\p", r"\p"])
    print("****")
