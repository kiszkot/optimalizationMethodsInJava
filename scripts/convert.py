# Convert names to ids

file = open("../assets/temat11/id_names_map.txt", "r")
comb = open("../assets/temat11/combinations.txt", "r")
map = open("../assets/temat11/cmapEWD.txt", "w")
lines = file.readlines()
lines.pop(0)
v = len(lines)
cities = []
ids = []

for line in lines:
	i = line.index(" ")
	id = line[0:i-2]
	str = line[i+1:-1]
	cities.append(str)
	ids.append(id)
	
lines = comb.readlines()
e = len(lines)
map.write(f"{v}\n{e}\n")
for line in lines:
    i = line.index(" ")
    f = line[0:i]
    j = line.index(" ", i+1)
    t = line[i+1:j]
    str = f"{cities.index(f)} {cities.index(t)} {line[j+1:-1]}\n"
    map.write(str)

file.close()
comb.close()
map.close()