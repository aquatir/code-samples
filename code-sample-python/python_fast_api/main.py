from typing import Optional

from fastapi import FastAPI, BackgroundTasks
from pydantic import BaseModel

app = FastAPI()


@app.get("/")
async def root():
    return {"message": "Hello World"}


@app.get("/hello/{name}")
async def say_hello(name: str):
    return {"message": f"Hello {name}"}


class SayHelloBody(BaseModel):
    name: str


@app.post("/hello")
async def say_hello_post(body: SayHelloBody, start: int, end: int, maybe: int = 10, maybe2: Optional[int] = None):
    """
    :param body: body of post request
    :param start: query param
    :param end: query param
    :param maybe: query param optional
    :param maybe2: query param optional
    :return:
    """
    return {"message": f"Hello from '{start}' to '{end}', maybe1: '{maybe}', maybe2: '{maybe2}' by {body.name}"}


@app.get("/notif")
async def notification(background_tasks: BackgroundTasks):
    background_tasks.add_task(lambda : print("backgroup task executed"))