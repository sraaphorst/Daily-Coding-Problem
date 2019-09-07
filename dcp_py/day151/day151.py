#!/usr/bin/evn python3
# day151.py
# By Sebastian Raaphorst, 2019.

from typing import List, Tuple

Colour = str
Image = List[List[Colour]]
Point = Tuple[int, int]


def flood_fill_queue(image: Image, orig_point: Point, new_colour: Colour) -> None:
    """
    We flood-fill an image given a point with a particular colour, using a simple breadth-first search.
    This can be more elaborately done by the right-hand rule, but this is sufficient.

    :param image: the image to flood-fiil
    :param orig_point: the original point from which to start the flood-fill
    :param new_colour: the new colour to use in the flood-fill
    :return: the modified image (this is not immutable: make it so by passing in a copy of the image)

    >>> B, W, G = 'B', 'W', 'G'
    >>> image = [[B, B, W], [W, W, W], [W, W, W], [B, B, B]]
    >>> flood_fill_queue(image, (2, 2), G)
    >>> image == [[B, B, G], [G, G, G], [G, G, G], [B, B, B]]
    True
    """
    dimx, dimy = len(image), len(image[0])
    ox, oy = orig_point
    orig_colour = image[ox][oy]

    def aux(points: List[Point]) -> None:
        if not points:
            return

        px, py = points.pop()
        if image[px][py] != orig_colour:
            aux(points)
        else:
            image[px][py] = new_colour
            aux(points + [(px + dx, py + dy) for dx in (-1, 0, 1) for dy in (-1, 0, 1)
                          if dx != dy and 0 <= px + dx < dimx and 0 <= py + dy < dimy])
    aux([orig_point])
