try:
    file = open("02i.txt", 'r')
    input = file.read().split('\n')
except FileNotFoundError:
    print("Please check the path.")
finally:
    file.close()

newinput = []

for i in input:
    newinput.append(i.split(' '))

posn = [0, 0, 0]
# horizontal, depth, aim

for i in newinput:
    dir = i[0]
    delta = int(i[1])

    if dir == "forward":
        posn[0] += delta
        posn[1] += posn[2] * delta
    elif dir == "down":
        posn[2] += delta
    else:
        posn[2] -= delta

print(posn[0] * posn[1])
