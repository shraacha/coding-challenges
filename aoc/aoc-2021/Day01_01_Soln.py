# Advent Of Code 2021
# Program Title:
#

try:
    file = open('01i.txt', 'r')
    input = file.readlines()
except FileNotFoundError:
    print("Please check the path.")
finally:
    file.close()

last_depth = 0
current_depth = 0
increase = 0

for i, line in enumerate(input):
    if i == 0:
        last_depth = int(line)
    else:
        current_depth = int(line)
        if current_depth > last_depth:
            increase += 1
        last_depth = current_depth

print(increase)

