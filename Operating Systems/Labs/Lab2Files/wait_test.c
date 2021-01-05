#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>

int main( ){
int pid = fork();
	if(pid == 0){
		//printf( " I’m the child %d\n" , getpid());
		//sleep(1);
		return 42;
	}else{
		//printf("My child is called %d\n",pid);
		//wait(NULL);
		//printf( "My child has terminated\n");
		int res = -5;
		wait(&res);
		printf("the result was %d\n",WEXITSTATUS(res));
	}
//printf("That’s it %d\n" , getpid());
return 0 ;
}

// #include <stdio.h>
// #include <unistd.h>
// #include <sys/types.h>
// #include <sys/wait.h>
//
// int main( ){
// int pid = fork();
// if(pid == 0){
// 	printf( " I’m the child %d\n" , getpid());
// 	sleep(1);
// }else{
// 	printf("My child is called %d\n",pid);
// 	wait(NULL);
// 	printf( "My child has terminated\n");
// }
// printf("That’s it %d\n" , getpid());
// return 0 ;
// }
