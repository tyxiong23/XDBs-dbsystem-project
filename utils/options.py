from argparse import ArgumentParser
from pathlib import Path

def getArgs():
    parser = ArgumentParser("XDB 2023")
    choices = ["table", "csv"]
    parser.add_argument('-p', '--printer', type=str, choices=choices, default='table')
    parser.add_argument('-b', '--base-dir', type=Path, default='data')
    parser.add_argument('-f', '--file', type=Path)
    parser.add_argument('-t', '--table', type=str)
    parser.add_argument('database', nargs='?', type=str)
    return parser.parse_args()

if __name__ == "__main__":
    args = getArgs()
    print(args)