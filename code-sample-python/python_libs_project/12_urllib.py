import urllib.request, urllib.robotparser
from urllib.parse import urlparse

if __name__ == '__main__':
    print("urllib starting")

    result = urlparse('https://duckduckgo.com/?q=python+stubbing&t=canonical&ia=qa')
    print(result)
    print(result.netloc)
    print(result.geturl())
    print(result.port)

    print("****")

    url = urllib.request.urlopen('https://www.google.com/')
    print(url.info())
    header = url.info()
    print(header.as_string())
    print(url.getcode())

    print("****")

    robot = urllib.robotparser.RobotFileParser()
    print(robot.set_url('http://arstechnica.com/robots.txt'))
    print(robot.read())
    print(robot.can_fetch('*', 'http://arstechnica.com/'))
    print(robot.can_fetch('*', 'http://arstechnica.com/cgi-bin/'))

    print("****")