import aiohttp
import asyncio

from aiohttp import ClientSession


async def fetch(n: int, session: ClientSession, url: str):
    async with session.get(url) as response:
        resp = await response.text()
        print(f"{n} — {resp}")


async def main(n):
    url = "https://httpstat.us/200"
    async with aiohttp.ClientSession() as session:
        tasks = [asyncio.create_task(fetch(n, session, url)) for n in range(n)]
        await asyncio.gather(*tasks)


if __name__ == '__main__':
    asyncio.run(main(500))
