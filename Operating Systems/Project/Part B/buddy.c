#include "buddy.h"
#include <sys/mman.h>
#include <stddef.h>
#include <stdio.h>
#include <math.h>
#include <assert.h>

#define MIN 5
#define LEVELS 8
#define PAGE 4096

enum flag {Free, Allocated};

struct head {
	short int level;
	enum flag status;
	// If it is Free, then it'll store links to the next and previous blocks in the list.
	struct head *next;
	struct head *prev;
};

struct head *new() {
	struct head *new = (struct head*) mmap(NULL, PAGE, PROT_READ | PROT_WRITE, MAP_PRIVATE | MAP_ANONYMOUS, -1, 0);
	if (new == MAP_FAILED){
		return NULL;
	} else{
	assert(((long int)new & 0xfff) == 0);
	new -> status = Free;
	new -> level = LEVELS - 1;
	return new;
	}
}

struct head *buddy(struct head* block){
	int position = block -> level;
  long int mask = 0x1 << (position + MIN);
	return (struct head*)((long int)block ^ mask);
}

struct head *split(struct head *block){
	int position = block -> level - 1;
	int mask = 0x1 << (position + MIN);
	return (struct head*)((long int)block | mask);
}

struct head *primary(struct head* block){
	int position = block -> level;
	long int mask = 0xffffffffffffffff << (1 + position + MIN);
	return (struct head*)((long int) block & mask);
}

void *hide(struct head* block){
	return (void*)(block + 1);
}


struct head *magic(void *memory){
	return ((struct head*)memory - 1);
}


int level(int req){
	int totalSize = req + sizeof(struct head);
	int currentSize = 1 << MIN;
	int i = 0;
	while(totalSize > currentSize){
		currentSize <<= 1;
		i++;
	}
	return i;
}

struct head * flists[LEVELS] = {NULL};

struct head *find(int position) {
  if(flists[position] != NULL) {
    struct head *allocated = flists[position];
    flists[position] = allocated -> next;
    if(allocated -> next != NULL) {
      allocated -> next -> prev = NULL;
    }
    allocated -> status = Allocated;
    return allocated;
  } else {
    if(position < (LEVELS - 1)) {
      struct head *big = find(position + 1);
      if(big == NULL) {
					return NULL;
			}
      else {
				struct head *leftOver = split(big);
					leftOver -> status = Free;
					leftOver -> level = position;
					leftOver -> next = NULL;
					leftOver -> prev = NULL;
					flists[position] = leftOver;
					big -> level = position;
					big -> status = Allocated;
					return big;
     }
    } else {
      struct head *block = new();
      if(block == NULL) {
				return NULL;
      } else {
					block -> status = Allocated;
					block -> level = position;
					return block;
      }
    }
  }
}

void insert(struct head *block) {
  int position = block -> level;
  while (position < (LEVELS - 1)) {
    struct head *sibling = buddy(block); // find the sibling block
    if(sibling -> status == Free && sibling -> level == position) {
      if(sibling -> next != NULL)
				sibling -> next -> prev = sibling -> prev;
      if(sibling -> prev != NULL)
				sibling -> prev -> next = sibling -> next;
      else
				flists[position] = sibling -> next;

      block = primary(block);
      block -> level = position + 1;
      position++;
    } else {
      break;
    }
  }
  block -> status = Free;
  block -> prev = NULL;
  if(position == (LEVELS - 1)) {
    if(flists[position] != NULL)  {
      return;
    }
  }
  struct head *next = flists[position];
  block -> next = next;
  if(next != NULL) {
    next -> prev = block;
  }
  flists[position] = block;
}


void *balloc(size_t size){
	if (size == 0){
		return NULL;
	}
	int position = level(size);
	struct head *allocated = find(position);
	return hide(allocated);
}

void bfree (void *memory){
	if (memory != NULL){
		struct head *block = magic(memory);
		insert(block);
	}
	return;
}

void dispblocklevel(struct head* block){
	printf("block level = %d\n",block->level);
}
void dispblockstatus(struct head* block){
	printf("block status = %d\n",block->status);
}

void blockinfo(struct head* block){
	printf("===================================================================\n");
	dispblockstatus(block);
	dispblocklevel(block);
	printf("start of block in memory: %p\n", block);
	printf("size of block in memory: %ld in bytes\n",sizeof(struct head));
	printf("===================================================================\n");
}
