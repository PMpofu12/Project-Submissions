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
