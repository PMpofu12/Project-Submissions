#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
int main(){
  for (size_t i = 0; i < 3; i++) {
      int pid = fork();
  }
  return 0;
}
