import sys
import itertools


for i in itertools.product([0,1], repeat=5):
    
    print i














count = 0
input_figures = ""
target = 0
left_to_right = False


"""
Code for parsing, not robust against bad input
How do I protect against it?
"""

for line in sys.stdin:
    count = count + 1
    #    print count
    if count % 2 != 0:
        input_list = line.split()
    else:
        target =  line.split()[0]
        NL = line.split()[1]
        left_to_right = True if NL is 'L' else False
       
        print input_list
        print target, NL, left_to_right
        

   

    
    


 
def searchSolution(input_list, target, left_to_right):
    if left_to_right is True:
        eval_left_to_right(input_list,target)
    else:
        eval_normal(input_list,target)



def eval_left_to_right(input_list,target):
    print "hello"
    

def eval_normal(input_list,target):
    print "Hello"
        
        
        








"""

*assuming the input is not corrupted (Error handling later)

1. add all tokens from line 1 into a list of ints
2. add first token of line 2 to the variable target
3. add second token of line 2 to a boolean
"""

