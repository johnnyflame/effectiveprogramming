from collections import deque
import fileinput
import copy
import random
import sys

q = deque()
result = []
target_size = 0
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
            print ''.join(element)
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
    #    print "matched, try reversing"
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



    
def better_max_match(input_queue):
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
    


"""
Greedy strategy for implementing max matches, operates in O(n**n) time
"""

def greedy_max(input_queue):
    #Question: If there is only one strip, are those squares matching or not matching?
   
    result = []
    total_matches = 0
    total_cost = 0
    availability_list = [True] * len(input_queue)

  
    
    start = random.randint(0,len(input_queue)-1)
    result.append(input_queue[start])
    availability_list[start] = False

 
    
    while len (result) < target_size and any (availability_list):
        i = 0
        while i < len (availability_list) -1 and not availability_list[i]:
            i = i + 1
    
        candidate = input_queue[i]
        cost = find_non_matches(result[-1],candidate)
        gains = find_matches(result[-1],candidate)
        candidate_index = i
        
        for j in range(0,len(input_queue)):
            if availability_list[j] and find_matches(result[-1], input_queue[j]) > gains:
                cost = find_non_matches(result[-1], input_queue[j])
                gains = find_matches(result[-1],input_queue[j])
                candidate = input_queue[j]
                candidate_index = j
            else:
                input_queue[j].reverse()
                if availability_list[j] and find_matches(result[-1], input_queue[j]) > gains:
                    cost = find_non_matches(result[-1], input_queue[j])
                    gains = find_matches(result[-1],input_queue[j])
                    candidate = input_queue[j]
                    candidate_index = j
                else:
                    input_queue[j].reverse()
    
        availability_list[candidate_index] = False
      
        result.append(candidate)
       
        
        total_matches = total_matches + gains
        total_cost = total_cost + cost
       
    return total_matches, result, total_cost
    



def greedy_balance(input_queue):
    #Question: If there is only one strip, are those squares matching or not matching?
   
    result = []
    total_matches = 0
    total_cost = 0
    availability_list = [True] * len(input_queue)    
    start = random.randint(0,len(input_queue)-1)
    result.append(input_queue[start])
    availability_list[start] = False
    search_max = True
   
    

    while len (result) < target_size and any (availability_list):
        i = 0
        
        while i < len (availability_list) -1 and not availability_list[i]:
            i = i + 1
    
        candidate = input_queue[i]
        cost = find_non_matches(result[-1],candidate)
        gains = find_matches(result[-1],candidate)
        candidate_index = i

        
      
        for j in range(0,len(input_queue)):
            if search_max:
                if availability_list[j] and find_matches(result[-1], input_queue[j]) > gains:
                    gains = find_matches(result[-1],input_queue[j])
                    candidate = input_queue[j]
                    candidate_index = j
                else:
                    input_queue[j].reverse()
                    if availability_list[j] and find_matches(result[-1], input_queue[j]) > gains:
                        gains = find_matches(result[-1],input_queue[j])
                        candidate = input_queue[j]
                        candidate_index = j
                    else:
                        input_queue[j].reverse()
            else:

                if availability_list[j] and find_non_matches(result[-1], input_queue[j]) > cost:
                    cost = find_non_matches(result[-1], input_queue[j])
                    candidate = input_queue[j]
                    candidate_index = j
                else:
                    input_queue[j].reverse()
                    if availability_list[j] and find_non_matches(result[-1], input_queue[j]) > cost:
                        cost = find_non_matches(result[-1], input_queue[j])
                        candidate = input_queue[j]
                        candidate_index = j
                    else:
                        input_queue[j].reverse()
                        
        result.append(candidate)
        availability_list[candidate_index] = False
        
        total_matches = total_matches + gains
        total_cost = total_cost + cost
        search_max = not search_max
  
    return total_matches, result, total_cost



def call_optimization(input_queue, iteration, maximize):
    best_score = 0
    best_result = []
    best_balance = len(input_queue) * len(input_queue[0])
    max_cost = 0
    
    if maximize:
        for i in range (0, iteration):
            current_score, current_result, cost = greedy_max(input_queue)
            if current_score > best_score:
                best_result = current_result
                best_score = current_score         

      
        for item in best_result:
            print ''.join(item)
        print "matches: ", best_score    
    else:
      
        for i in range (0, iteration):
            current_score, current_result, cost = greedy_balance(input_queue)
            difference = abs(current_score - cost)

            if difference < best_balance:           
                best_balance = difference
                best_score = current_score
                max_cost = cost
                best_result = current_result

        print "\nOutput carpet: \n"
        for item in best_result:
            print ''.join(item)

        
        print "Matches: ", best_score
        print "Non-matches: ", max_cost

        print "Difference between matches and non-matches: ", abs(best_score - max_cost)        



        
def main():
    global target_size
    if len(sys.argv) != 3:
        print "Please enter correct input"
        return
    
    option = sys.argv[1]
    target_size = int(sys.argv[2])

    for line in sys.stdin:
        carpet_strip = []
        line = line.replace("\n","") # Might not be neccesary if the input has no \n character
        for char in line:
            carpet_strip.append(char)
        q.append(carpet_strip)
   
    if target_size < 1:
        print "You're a real comedian mate."
        return
        
    if target_size > len(q):
        print "\nThe requested carpet size is larger than the quantity in stock"
        print "Stock size: ", len(q)
        print "Requested carpet size: ", target_size
        return
   
    iterations = 250
    maximize = True
    if option == '-n':
        no_match(q,result,tries,len(q))
    elif option == '-m':
        call_optimization(q,iterations,maximize)
    elif option == '-b':
        maximize = False
        call_optimization(q,iterations,maximize)
    else:
        print "Please give correct option"
        return

    "Next step will be determined by the flag from input"


    """
    TODO:

    IO parsing
    Testing
    Convert TABS to space
    """

main()
