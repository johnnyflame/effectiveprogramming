from Tkinter import *
import sys

window_size = 1000


master = Tk()

master.title("Toothpicks")
w = Canvas(master, width=window_size, height=window_size,bg = "white")
w.pack()

def set_length(generation,ratio):
    length = (window_size - 200) / ratio
    if generation > 0:
        length = length / generation

    return length



def toothpick_placement(n,x1,y1,x2,y2,length):
    
    w.create_line(x1,y1,x2,y2)
    if(n==0):
        return 
    length *= ratio
    if y1 == y2:
        toothpick_placement(n - 1,x1,y1 - length/2,x1,y2 + length/2,length)
        toothpick_placement(n - 1,x2,y1 - length/2,x2, y2 + length/2,length)        
    else:
        toothpick_placement(n - 1,x1 - length/2,y1,x2 + length/2,y1,length)
        toothpick_placement(n - 1,x1 - length/2,y2,x2 + length/2,y2,length)



generation = 0
ratio = 1

generation = int(sys.argv[1])
if len(sys.argv) == 3:
    ratio = float(sys.argv[2])

length = set_length(generation,ratio)

x1 = window_size/2 - length/2
y1 = window_size/2
x2 = window_size/2 + length/2
y2 = window_size/2




toothpick_placement(generation,x1,y1,x2,y2,length)

    

        

        



"""
TODO:

1. Add scale abilities for both window and ratio

NOTE:

starting length should be a function of generation and (optionally) the ratio

function set_length(int generation, int ratio)

if ratio < 1:
  Make length big(ish) and window smallish
if ratio > 1:
  Make window big and length small(ish)


2. Parse input from command-line

"""






mainloop()
