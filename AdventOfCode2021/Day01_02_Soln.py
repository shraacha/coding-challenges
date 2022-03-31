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
windows = [0, 0, 0]
window_list = []

for i, line in enumerate(input):
    if (i % 3) == 0:
        if i != 0:
            windows[1] += int(line)
            windows[2] += int(line)
            window_list.append(windows[1])
        windows[0] = int(line)
    elif (i % 3) == 1:
        if i != 1:
            windows[2] += int(line)
            window_list.append(windows[2])
        windows[1] = int(line)
        windows[0] += int(line)
    elif (i % 3) == 2:
        windows[2] = int(line)
        windows[0] += int(line)
        windows[1] += int(line)
        window_list.append(windows[0])

for i, window in enumerate(window_list):
    if i == 0:
        last_depth = int(window)
    else:
        current_depth = int(window)
        if current_depth > last_depth:
            increase += 1
        last_depth = current_depth

print(increase)