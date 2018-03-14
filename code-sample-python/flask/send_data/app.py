from flask import Flask
from urllib3 import request
app = Flask(__name__)

@app.route('/')
def hello_world():
    return 'Hello, World!'

@app.route("/model/excel", methods = ['GET', 'POST'])
def load_excel():
    data = request.get_data()
    print (data)
    return "kek"