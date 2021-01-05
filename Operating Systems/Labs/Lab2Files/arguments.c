#include <stdio.h>
#include <string.h>

int main(int argc, char *argv[]) {
    char str[128];

    if(argc <= 1) {
      printf("%s\n", argv[0]);
      printf("Program name: %s\n", argv[0]);
      printf("Arguments: NONE");
    }else {
            printf("Program name: %s\n", argv[0]);
            strcat(str, argv[1]);
            strcat(str, ", ");
            for(int i = 2; i < argc; ++i) {
              strcat(str, argv[i]);
              if (i != argc-1) {
                strcat(str, ", ");
              }
            }
        }
        printf("%s\n", str);
    return 0;
}
