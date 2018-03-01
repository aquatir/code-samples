from app import app
from flask import render_template
import urllib.request as requester

@app.route("/")
def hello_world():
    return "Hello, World!"


@app.route("/world/")
def world_url():
    return "I'm da king!"


@app.route("/hello/<name>/")
def hello_you(name):
    #app.logger.debug("A value for debugging")
    return render_template('hello.html', name=name)


@app.route("/<int:user_number>/")
def add_ap_stuff(user_number):
    return "your number: " + str(user_number) + " multiplied by 10: " + str(user_number*10)

@app.route("/abc/")
def query():
    return requester.urlopen("http://localhost:8000/").read()


