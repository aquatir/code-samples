from flask import Flask
app = Flask(__name__)


@app.route("/")
def hello_world():
    return "Hello, World!"


@app.route("/world")
def world_url():
    return "I'm da king!"


@app.route("/<name>")
def hello_you(name):
    return "Hello, " + name


@app.route("/<int:numberr>")
def add_ap_stuff(numberr):
    return "your number: " + str(numberr) + " multiplied by 10: " + str(numberr*10)

