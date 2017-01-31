from collections import deque
import fileinput
'''

'''

q = deque()
result = []
target_size = 5



def no_match(input_queue,result_stack,target_size):
  return






def main():
    for line in fileinput.input():
        carpet_strip = []
        line = line.replace("\n","") # Might not be neccesary if the input has no \n character
        for char in line:
             carpet_strip.append(char)
             
        print carpet_strip
        q.append(carpet_strip)

    print(q)

    q.append(q.popleft())

    print(q)
    

    "Next step will be determined by the flag from input"


main()
