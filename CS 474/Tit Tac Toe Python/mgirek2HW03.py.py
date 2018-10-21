import sys

board = [
         [' ', ' ', ' '],
         [' ', ' ', ' '],
         [' ', ' ', ' ']
         ]

player1 = 'X'
player2 = 'O'
players = [player1,player2]

def printBoard():
    print '''
        +--+---+---+
        + {0}| {1} | {2} +
        +--|---|---+
        + {3}| {4} | {5} +
        +--|---|---+
        + {6}| {7} | {8} +
        +--+---+---+
        '''.format(board[0][0], board[0][1], board[0][2], board[1][0], board[1][1], board[1][2], board[2][0], board[2][1], board[2][2]);
printBoard()
def place_token(player, x, y):
    board[int(x)][int(y)] = player

def newBoard():
    for i in range(len(board)):
        for j in range(len(board)):
            board[i][j] = ' '
    printBoard()

def diagonal():
    if len(set([board[i][i] for i in range(len(board))])) == 1:
        return board[0][0]
    if len(set([board[i][len(board)-i-1] for i in range(len(board))])) == 1:
        return board[0][len(board)-1]
    return 0

def column():
    for i in range(len(board)):
        if len(set([row[i] for row in board])) == 1:
            return row[i]
    return 0

def row():
    for i in range(len(board)):
        if len(set(board[i])) == 1:
            return board[i][0]
    return 0
def did_win(player):
    if(column() == player):
        return True
    if(row() == player):
        return True
    if(diagonal() == player):
        return True
    return False

def move(player):
    x = raw_input("Enter Row ")
    y = raw_input("Enter Column ")

    if (int(x) not in range(0,3) or int(y) not in range(0,3)):
        print "Invalid move, outside board, try again"
        move(player)

    else:
        if board[int(x)][int(y)] != ' ':
            print "Invalid move, position already taken, try again:"
            move(player)
        else:
            board[int(x)][int(y)] = player
            if(did_win(player)):
                printBoard()
                print (str(player) + " WINS !!!")
                answer = raw_input("Play again? (y/n)")
                if answer == 'y':
                    newBoard()
                    start()
                else:
                    print("Goodbye!")
                    sys.exit()
            printBoard()


def start():
    for i in range(len(board)**2):
        print("Turn " + str(i+1) + ": Player " + str(i%2 + 1) + " ("+players[i%2]+")")
        move(players[i%2])
        if(did_win(players[i%2])):
            printBoard()
            print (str(players[i%2]) + " WINS!!!")
            sys.exit()
        if(did_win(players[0]) != True  and did_win(players[1]) != True  and i == 8 ):
            print ("It is a tie !")
            answer = raw_input("Play again? (y/n)")
            if answer == 'y':
                newBoard()
                start()
            else:
                print("Goodbye!")
                sys.exit()


start()

