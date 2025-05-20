with open("data.txt",'a') as F:
    for i in range(1000):
        F.write(f"{i}\n")