#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>

#define ROUNDS 10
#define LOOP 100000

struct chunk{
int size;
struct chunk *next;
};
struct chunk* flist = NULL;

void *malloc (size_t size){
  if(size == 0){
    return NULL;
  }
  struct chunk *next = flist;
  struct chunk *prev = NULL;
  while(next != NULL){
    if(next->size >= size){
    if(prev != NULL){
      prev->next = next->next;
    }else{
      flist = next->next;
    }
    return (void *)(next + 1);
    }else{
      prev = next;
      next = next->next;
    }
  }
  // use sbrk to allocate new memory
  void *memory = sbrk(size + sizeof(struct chunk));
  if(memory == (void *)-1){
    return NULL;
  }else{
    struct chunk *cnk = (struct chunk*)memory;
    cnk->size = size;
    return (void *)(cnk + 1);
  }
}

void free(void *memory){
  if(memory != NULL){
  // we’re jumping back one chunk position
  struct chunk *cnk = (struct chunk *)((struct chunk *)memory - 1);
  cnk->next = flist;
  flist = cnk;
  }
  return;
}

// int main(){
//   void *init = sbrk(0);
//   void *current;
//   printf("The initial top of the heap is %p.\n", init);
//   for(int j = 0; j < ROUNDS; j++){
//     for(int i = 0; i < LOOP; i++){
//     size_t size = (rand() % 4000) + sizeof(int);
//     int *memory;
//     memory = malloc(size);
//     if(memory == NULL){
//     fprintf(stderr, "malloc failed\n");
//     return(1);
//     }
//     // writing to the memory so we know it exists
//     *memory = 123;
//     free(memory);
//     }
//   current = sbrk(0);
//   int allocated = (int)((current - init) / 1024);
//   printf("%d\n" ,j);
//   printf("The current top of the heap is %p. \n", current);
//   printf("increased by %d Kbyte\n", allocated);
//   }
// return 0;
// }
