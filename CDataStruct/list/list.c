#include <stdio.h>
#include "list.h"

Status getElem(struct List list, int i, ElemType *e){
    if(list.length == 0 || i < 1 || i > list.length){
        return Error;
    }
    *e = list.data[i - 1];
    return OK;
}

void testList() {
    int num = 0;
    ElemType *el = &num;
    struct List list;
    list.length = 10;
    getElem(list, 1,el);
    printf("%d", *el);
}