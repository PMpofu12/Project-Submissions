import numpy as np
import matplotlib.pyplot as plt

x1 = np.random.normal(0,1, (30,1))
x2 = np.random.normal(10,1, (30,1))

y1 = np.zeros(x1.shape)
y2 = np.ones(x2.shape)

x = np.append(x1, x2)
y = np.append(y1, y2) 

plt.axhline(0,color='black')
plt.axvline(0,color='black')
plt.scatter(x1,y1, color='blue')
plt.scatter(x2,y2, color='red')
plt.xlabel('x-values')
plt.ylabel('y-values')
plt.savefig('data.png')
data_file = open('data.txt', 'w')
for i in np.arange(x.shape[0]):
	data_file.write(str(x[i])+','+str(y[i])+',')
