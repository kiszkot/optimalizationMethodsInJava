# Combiate city names

file = open("../assets/temat11/id_names_map.txt", "r")
comb = open("../assets/temat11/comb.txt", "w")
lines = file.readlines()
lines.pop(0)
cities = []

for line in lines:
	i = line.index(" ")
	str = line[i+1:-1]
	cities.append(str)

for i,f in enumerate(cities):
	for j,t in enumerate(cities):
		if i == j:
			continue
		str = f + " " + t + "\n"
		comb.write(str)

file.close()
comb.close()