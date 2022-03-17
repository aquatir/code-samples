import argparse


def get_args():
    """"""
    parser = argparse.ArgumentParser(
        description="A simple argument parser",
        epilog="This is where you might put example usage"
    )

    # required argument
    parser.add_argument('-x', '-execute', action="store", required=True,
                        help='Help text for option X')
    # optional arguments
    parser.add_argument('-y', help='Help text for option Y', default=False)
    parser.add_argument('-z', help='Help text for option Z', type=int)

    log_group = parser.add_mutually_exclusive_group()
    log_group.add_argument("-v", "-verbose", help="Enable verbose log mode", action='store_true', default=False)
    log_group.add_argument("-i", "-info", help="Enable info log mode", action='store_true', default=False)

    return parser.parse_args()


if __name__ == '__main__':
    args = get_args()
    print(args)
