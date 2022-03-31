import os
dirname = os.path.dirname(__file__)
inputPath = os.path.join(dirname, '../inputs/04i.txt')

with open(inputPath) as file:
    lines = file.readlines()

draw = (int(d) for d in lines[0].strip().split(","))

del lines[0]
del lines[0]

# this class defines a bingo board and related operations
class bingoBoard:
    def __init__(self, winStatus, board, unmarkedSum):
        self.winStatus = False
        self.board = [[[0, False] for x in range(0,5)] for y in range(0, 5)]
        self.umarkedSum = 0

    def insertValue(self, val, row, col):
        self.board[row][col][0] = val

    def checkWin(self, row, col):
        for square in self.board[row]:
            if (square[1] == False):
                break
        else:
            self.winStatus = True
            return
        
        for aRow in self.board:
            if (aRow[col][1] == False):
                break
        else:
            self.winStatus = True

    def mark(self, val):
        row = 0
        col = 0

        for yInd, y in enumerate(self.board):
            for xInd, x in enumerate(y):
                if (val == x[0]):
                    row = yInd
                    col = xInd
                    x[1] = True
                    break
            else:
                continue
            break

        self.checkWin(row, col)
    
    def sum(self):
        self.unmarkedSum = 0
        for y in self.board:
            for x in y:
                if x[1] == False:
                    self.unmarkedSum += x[0]

#setting up the boards
boards = []
tempBoard = bingoBoard(0, 0, 0)
firstLine = 0
for lNum, l in enumerate(lines):
    if (l == '\n'):
        firstLine = lNum + 1
    else:
        row = l.strip().split()
        for e in range(0, len(row)):
            tempBoard.insertValue(int(row[e]), lNum - firstLine, e)
        if (lNum - firstLine) == 4:
            boards.append(tempBoard)
            tempBoard = bingoBoard(0, 0, 0)


# part 1 
# going through the draws
# winner = bingoBoard(0,0,0)
# lastDraw = 0
# def play(boards, draw):
#     for d in draw:
#         for b in boards:
#             b.mark(d)
#             if (b.winStatus == True):
#                 winner = b
#                 lastDraw = d
#                 break
#         else:
#             continue
#         break
#     winner.sum()
#     print((winner.unmarkedSum) * lastDraw)

# play(boards, draw)


# part 2
def play2(boards, draw):
    winner = bingoBoard(0,0,0)
    lastDraw = 0

    for d in draw:
        boardNum = len(boards)
        deleted = 0
        for i in range(0, boardNum):
            boards[i - deleted].mark(d)
            if (len(boards) > 1) and (boards[i - deleted].winStatus == True):
                del boards[i - deleted]
                deleted += 1
            elif (len(boards) == 1) and (boards[i - deleted].winStatus == True):
                winner = boards[0]
                lastDraw = d
                break
        else:
            continue
        break

    winner.sum()
    print((winner.unmarkedSum) * lastDraw)

play2(boards, draw)








