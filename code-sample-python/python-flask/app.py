import markupsafe
from flask import Flask, request

app = Flask(__name__)


@app.route('/')
def hello_world():
    return 'Hello World!'


@app.route("/hello/<string:name>")
def hello(name):
    return f"Hello, {markupsafe.escape(name)}!"


@app.route("/path/<path:url_path>")
def path(url_path):
    return f"Path is: {url_path}"


@app.route("/login", methods=['GET', 'POST'])
def login():
    if request.method == 'POST':
        return f'POST method server'
    else:
        return f'GET method server'


@app.route("/params")
def params():
    key = request.args.get('key', '')
    value = request.args.get('value', '')
    return f'your params. key: {key}, value: {value}'


if __name__ == '__main__':
    app.run()
