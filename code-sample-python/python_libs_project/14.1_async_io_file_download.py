import aiohttp
import asyncio
import async_timeout
import os


# This does not start 5 download as the same time
# but still uses asyncio

async def download_coroutine(session, url):
    async with async_timeout.timeout(10):
        async with session.get(url) as response:
            print(f" working with url {url}")
            filename = os.path.basename(url)

            with open(filename, 'wb') as f_handle:
                while True:
                    chunk = await response.content.read(1024)
                    if not chunk:
                        break
                    f_handle.write(chunk)
            res = await response.release()
            print(f" done with {url}")
            return res


async def main(loop):
    urls = ["http://www.irs.gov/pub/irs-pdf/f1040.pdf",
            "http://www.irs.gov/pub/irs-pdf/f1040a.pdf",
            "http://www.irs.gov/pub/irs-pdf/f1040ez.pdf",
            "http://www.irs.gov/pub/irs-pdf/f1040es.pdf",
            "http://www.irs.gov/pub/irs-pdf/f1040sb.pdf"]

    async with aiohttp.ClientSession(loop=loop) as session:
        for url in urls:
            print(f"Starting wait on {url}")
            await download_coroutine(session, url)


if __name__ == '__main__':
    print("async io starting")

    loop = asyncio.get_event_loop()
    loop.run_until_complete(main(loop))
