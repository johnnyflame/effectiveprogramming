from collections import deque
import fileinput
import copy
'''

'''

q = deque()
result = []
target_size = 4
tries = 0
max_matches = 0
current_matches = 0
cost = 0
"""
no_match

Use backtrack search? DFS? 
"""

# returns False if any matching squares are found
def found_match(list_1,list_2):
    return any (i == j for i, j in zip(list_1,list_2))

def no_match(input_queue,result,tries,input_length):

    #base case 1: if result is the same size as target, return
    if len(result) == target_size:
        for element in result:
            print element
        return
    #how to design stop case?
    if tries > input_length:
        print "not possible"
        return

    if not result:
        result.append(input_queue.popleft())
        
        return no_match(input_queue,result,tries,input_length)
    
    current_item = input_queue.popleft()

    if found_match(result[-1],current_item):
        print "matched, try reversing"
        current_item.reverse()       
        if found_match(result[-1],current_item):         
            tries = tries + 1
            input_queue.append(current_item)
            return no_match(input_queue,result,tries,input_length)
    
   
    result.append(current_item)
    return no_match(input_queue,result,tries,input_length)

        

# Local cost function returning the number of matches between 2 strips

def find_matches(list_1,list_2):
    count = 0

    for i, j in zip(list_1,list_2):
        if i == j:
            count = count + 1    
    return count

def find_non_matches(list_1,list_2):
    count = 0

    for i, j in zip(list_1,list_2):
        if i != j:
            count = count + 1    
    return count


"""
1. initialize a candidate solution (put it in a list/queue)
2. Evaluate the cost of candidate solution
3. Recursively search other combinations for better candidates
4. Pruning off bad ends.


"""



    
def max_match(input_queue):
    global cost
    global result

 
  
    for i in range(0,target_size):
        result.append(input_queue[i])
        if i > 0:
            cost = cost + find_non_matches(input_queue[i-1],input_queue[i])
            print "\niteration: ", i, "\nResult list:"
        for element in result:
            print element
        print "number of mismatches: ", cost

    print "\ntotal mismatches: ", cost

    for i in range (0,len(input_queue)):
        carpet_in_progress = []
      
"""
TODO: 

Return here tomorrow




"""


def recursive_max(carpet_in_progress, next_piece, cost_so_far,available_stock):
    global cost
    global result

    if len(carpet_in_progress) != 0:
        cost_so_far = cost_so_far + find_non_matches(carpet_in_progress[-1],next_piece)
        if cost_so_far >= cost:    
            return
    
    carpet_in_progress.append(next_piece)
    if len (carpet_in_progress) == target_size:
        cost = cost_so_far
        result = carpet_in_progress
        return
    
    return
    

def main():

    # Reads the input line by line and insert into a queue 
    for line in fileinput.input():
        carpet_strip = []
        line = line.replace("\n","") # Might not be neccesary if the input has no \n character
        for char in line:
            carpet_strip.append(char)
        q.append(carpet_strip)


#    no_match(q,result,tries,len(q))

    max_match(q)

    "Next step will be determined by the flag from input"


main()
