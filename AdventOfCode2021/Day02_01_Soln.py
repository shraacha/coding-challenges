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

posn = [0,0]
for i in newinput:
    dir = i[0]
    delta = int(i[1])

    if dir == "forward":
        posn[0] += delta
    elif dir == "down":
        posn[1] += delta
    else:
        posn[1] -= delta

print(posn[0] * posn[1])
