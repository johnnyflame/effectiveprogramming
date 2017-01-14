import sys
import itertools

# ''.join(itertools.imap(str, intList)) 

    #    if len(x) == 1 return x
 
 #   return (x)

x = [1,2,3]
target = 9

"""
Recursion design:

Base case:

When n == 3

"""

def evaluate_partial(input_list):
   


    while len(input_list) > 3:
       # print"full list",input_list
        evaluate_list = input_list[:3]
        input_list = input_list[3:]
        input_list.insert(0, eval(''.join(map(str, evaluate_list))))    
    if len(input_list) <= 3:
        return eval(''.join(map(str, input_list)))
    


def eval_left_to_right(input_list,target):
    
    for i in itertools.product(["+","*"], repeat=len(input_list) - 1):
        combined_list = [None] * (len(input_list) + len(i))      
        combined_list[::2] = input_list
        combined_list[1::2] = i

    #    print "TARGET:", target

        if evaluate_partial(combined_list) == target:
            print ' '.join(map(str,combined_list))
            return
        
    print "impossible"
    return

   
        
      
   
    
       

        
#eval_left_to_right(x,target)
#combined_list.insert(0,evaluate_partial(first_three_items))



def eval_normal(input_list,target):
    
    for i in itertools.product(["+","*"], repeat=len(input_list) - 1):
        output_line = [None] * (len(input_list) + len(i))
        output_line[::2] = input_list
        output_line[1::2] = i
        out = ' '.join(map(str, output_line))
        result = eval(out)

        if result == target:
            print out
            return
    print "impossible"
    return


 
def searchSolution(input_list, target, left_to_right):
    if left_to_right is True:   
        eval_left_to_right(input_list,target)
    else:
        eval_normal(input_list,target)




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

        print NL + "",
        searchSolution(input_list,target,left_to_right)



   





 



""" 
        



*assuming the input is not corrupted (Error handling later)

1. add all tokens from line 1 into a list of ints
2. add first token of line 2 to the variable target
3. add second token of line 2 to a boolean

*Algorithm for generating permutation

Normal is done

Now, left to right:


From a list of all operators and numbers:

1. pop first 3 elements
2. evaluate them(maybe in a seperate function)
3. add the result back to the list
4. repeat until len(list) == 1



"""
