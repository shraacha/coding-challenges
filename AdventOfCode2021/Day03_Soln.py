try:
    input = [list(x.rstrip("\n")) for x in open("03i.txt", 'r')]
except FileNotFoundError:
    print("Please check the path.")

width = len(input[0])
accum = [[0 for i in range(width)] for j in range(2)]
final = [[], []]

for i in input:
    for j, val in enumerate(i):
        accum[1 if val=='1' else 0][j] += 1

for i in range(width):
    if accum[0][i] > accum[1][i]:
        final[0].append(0); final[1].append(1)
    else:
        final[0].append(1); final[1].append(0)

def binToDec (bin):
    dec = 0
    for i in range(len(bin)):
        dec += (2 ** (width - 1 - i)) * bin[i]
    return dec

gamma = binToDec(final[0])
epsilon = binToDec(final[1])

#print(gamma * epsilon)

o2 = input
co2 = input
for i in range(width):
    if len(o2) > 1:
        o2_0 = [x for x in o2 if x[i] == '0']
        o2_1 = [x for x in o2 if x[i] == '1']
        if len(o2_1) >= len(o2_0):
            o2 = o2_1
        else:
            o2 = o2_0
    if len(co2) > 1:
        co2_0 = [x for x in co2 if x[i] == '0']
        co2_1 = [x for x in co2 if x[i] == '1']
        if len(co2_0) <= len(co2_1):
            co2 = co2_0
        else:
            co2 = co2_1

o2_final = ''
co2_final = ''

for x in o2[0]:
    o2_final = o2_final + x

for x in co2[0]:
    co2_final = co2_final + x 

print(int(o2_final, 2) * int(co2_final, 2))