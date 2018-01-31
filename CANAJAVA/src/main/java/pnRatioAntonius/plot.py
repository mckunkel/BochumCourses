import matplotlib.pyplot as plt
import csv
import numpy as np


def read_data(filename):
    T = []
    ratio = []

    for line in csv.reader(open(filename)):
        if len(line) != 3:
            break
        if line[0].startswith("#"):
            continue

        T.append(line[1])
        ratio.append(line[2])

    return np.array(T), np.array(ratio)

T, ratio = read_data("../../../../../pnRatioAntonius/pn-ratio.csv")
Tnd, rationd = read_data("../../../../../pnRatioAntonius/pn-ratio-nodecouple.csv")
Tnf, rationf = read_data("../../../../../pnRatioAntonius/pn-ratio-nofreeze.csv")

plt.plot(T, ratio)
plt.plot(Tnd, rationd)
plt.plot(Tnf, rationf)
plt.semilogx()
plt.semilogy()
#plt.gca().invert_xaxis()
plt.xlabel("T [MeV]")
plt.ylabel("$N_n/N_p$")
plt.xlim(10, 0.01)
plt.ylim(.01, 1)
plt.show()
