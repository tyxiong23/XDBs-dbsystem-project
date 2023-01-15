from argparse import ArgumentParser
from pathlib import Path

def getArgs():
    parser = ArgumentParser("XDB 2023")
    parser.add_argument('-b', '--base-dir', type=Path, default='data', help="root path of stored data for XDB")
    parser.add_argument('-f', '--file', type=Path, help="input file with [csv/sql] format")
    parser.add_argument('-p', '--printer', type=str, choices=["table", "csv"], default='table', help="choose the printer type")
    parser.add_argument('-t', '--table', type=str, help="the table selected for loading data (only works when loading CSV file)")
    parser.add_argument('database', nargs='?', type=str, help="choose a database")
    return parser.parse_args()

if __name__ == "__main__":
    args = getArgs()
    print(args)