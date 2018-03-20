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

plt.plot(Tnd, rationd)
plt.plot(Tnf, rationf)
plt.plot(T, ratio)

plt.text(7, .05, "Protons and neutrons are\nin thermal equilibrium.")
plt.text(.27, .07, "Neutrons decay\ninto protons.")
plt.text(.028, .108, "Stable\nnuclei.")

plt.annotate(xy=(.99, .29), xytext=(1.56, .56), s="Neutrons and protons\ndecouple.", arrowprops={"arrowstyle": "->"}) 
plt.annotate(xy=(.07, .21), xytext=(.12, .33), s="Neutrons combine with\nprotons to form deute-\nrium, which is stable.\nNeutron decay stops.", arrowprops={"arrowstyle": "->"}) 

plt.semilogx()
plt.semilogy()
plt.xlabel("T [MeV]")
plt.ylabel("$N_n/N_p$")
plt.xlim(10, 0.01)
plt.ylim(.01, 1)
plt.show()
