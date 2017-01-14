import sys
import itertools

# ''.join(itertools.imap(str, intList)) 

   
x = "1+2+3+4+5"
print x.split()[:3]


    #    if len(x) == 1 return x
 
 #   return (x)


def eval_normal(input_list,target):
    
    for i in itertools.product(["+","*"], repeat=len(input_list) - 1):
        output_line = [None] * (len(input_list) + len(i))
        output_line[::2] = input_list
        output_line[1::2] = i
        out = ''.join(map(str, output_line))
        result = eval(out)

        if result == target:
            print out, "=", target
            return
    print "Impossible"
    return


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
        target =  int(line.split()[0])
        NL = line.split()[1]
        left_to_right = True if NL is 'L' else False

        eval_left_to_right(input_list,target)

   
 
def searchSolution(input_list, target, left_to_right):
    if left_to_right is True:
        eval_left_to_right(input_list,target)
    else:
        eval_normal(input_list,target)





        
def eval_left_to_right(input_list,target):
    
    for i in itertools.product(["+","*"], repeat=len(input_list) - 1):
        output_line = [None] * (len(input_list) + len(i))
        output_line[::2] = input_list
        output_line[1::2] = i
        out = ''.join(map(str, output_line))
        result = eval(out)

        if result == target:
            print out, "=", target
            return
    print "Impossible"
    return

 



""" 
        



*assuming the input is not corrupted (Error handling later)

1. add all tokens from line 1 into a list of ints
2. add first token of line 2 to the variable target
3. add second token of line 2 to a boolean

*Algorithm for generating permutation

Normal is done

Now, left to right:




"""
