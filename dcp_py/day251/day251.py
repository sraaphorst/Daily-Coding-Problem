#!/usr/bin/env python3
# By Sebastian Raaphorst, 2019


from typing import Callable, Iterable
import tempfile


def fileLength(name: str) -> int:
    with open(name) as f:
        for (i, l) in enumerate(f):
            pass
    return i + 1


def sortHugeDataset(infilename: str, outfilename: str, chunks: int, sorter: Callable[[Iterable[int]], Iterable[int]]):
    numLines = fileLength(infilename)
    linesPerChunk = numLines / chunks + 1

    # Read the files
    lineNumber = 0
    chunkFiles = []

    currChunkLines = []
    for line in open(infilename):
        # If we have read a chunk, sort it, and output it.
        if len(currChunkLines) >= linesPerChunk:
            sortedData = sorter([int(x) for x in currChunkLines])
            chunkFile = tempfile.NamedTemporaryFile('rw', delete=False)
            for sd in sortedData:
                chunkFile.write(f'{sd}\n')
        else:
            chunkFiles.append(chunkFile.name)

    # Now we have to combine all of the chunk files.

