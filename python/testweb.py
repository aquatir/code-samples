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


@app.route("/<int:user_number>")
def add_ap_stuff(user_number):
    return "your number: " + str(user_number) + " multiplied by 10: " + str(user_number*10)

