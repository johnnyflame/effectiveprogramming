from Tkinter import *

window_width = 1000
window_height = 1000

master = Tk()
w = Canvas(master, width=window_width, height=window_height)
w.pack()

w.create_line(20,20, 50, 20, width=3)


mainloop()
"""
Recursively draw lines


idea:

base case:

when n == 1, return/draw the first line near the centre of the screen

draw lines such that line(n-1)'s ends are covered, in perpendicular 
orientation

"""


