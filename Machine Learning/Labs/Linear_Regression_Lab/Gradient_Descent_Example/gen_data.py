import numpy as np
import matplotlib.pyplot as plt

x = np.random.normal(0,10, (150,1))
x = np.hstack([np.ones(x.shape[0])[np.newaxis].T, x, x**2])
true_param = np.random.uniform(0,1,x.shape[1]).T
y = np.dot(x, true_param)
y = y + np.random.normal(0,8,y.shape)
plt.axhline(0,color='black')
plt.axvline(0,color='black')
plt.scatter(x[:,1],y, color='blue')
plt.xlabel('x-values')
plt.ylabel('y-values')
plt.savefig('data.png')
data_file = open('data.txt', 'w')
for i in np.arange(x.shape[0]):
	data_file.write(str(x[i,1])+','+str(y[i])+',')
