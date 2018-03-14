from flask import Flask
from flask import Response
#from urllib3 import request THERE IS request in both urllib and flask. Be cautious!
from flask import request
app = Flask(__name__)


@app.route("/json/", methods=['GET', 'POST'])
def post_json():
    content = request.json
    app.logger.debug("content: " + str(content))
    resp = Response('{"expert_id": 3}')
    resp.headers['Content-Type'] = 'application/json'
    return resp
