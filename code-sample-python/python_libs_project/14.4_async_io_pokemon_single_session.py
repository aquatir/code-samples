import asyncio
import time

import aiohttp
from aiohttp import ClientSession

#
# It DOES finish roughly after 150 seconds each time... why?
#


# async def on_request_start(
#         session, trace_config_ctx, params):
#     print(f"--Starting request on session {session} with params {params}")
#
#
# async def on_request_end(session, trace_config_ctx, params):
#     print(f"--Ending request on session {session} with params {params}")


async def get_pokemon(url: str, session: ClientSession) -> str:
    print(f"starting get on url: {url}")
    async with session.get(url) as resp:
        pokemon = await resp.json()
        print(f"got response on url: {url}")
        return pokemon['name']


async def main(loop):
    tasks = []

    # trace_config = aiohttp.TraceConfig()
    # trace_config.on_request_start.append(on_request_start)
    # trace_config.on_request_end.append(on_request_end)
    conn = aiohttp.TCPConnector(limit=100)
    async with ClientSession(connector=conn, loop=loop, trace_configs=[]) as session:
        print("opening client connection")
        for i in range(1, 150):
            url = f'https://pokeapi.co/api/v2/pokemon/{i}'
            task = get_pokemon(url.format(i), session)
            tasks.append(task)

        print("waiting on all responses")
        responses = await asyncio.gather(*tasks)
        print("wait finished")
        print(responses)
    print("closing client connection")


if __name__ == '__main__':
    start_time = time.time()
    loop = asyncio.get_event_loop()
    loop.run_until_complete(main(loop))

    print("--- %s seconds ---" % (time.time() - start_time))
