#include <stdio.h>
#include "list.h"

Status getElem(List list, int i, ElemType *e){
    if(list.length == 0 || i < 1 || i > list.length){
        return Error;
    }
    *e = list.data[i - 1];
    return OK;
}

Status ListInsert(List *list, int i, ElemType e){
    if(list->length == maxLength){
        return Error;
    }
    if(i < 1 || i > maxLength+1){
        return Error;
    }
    if(i <= list->length){
        for(int k = list->length-1;k >= i-1;k--){
            list->data[k+1] = list->data[k];
        }
    }
    list->data[i-1] = e;
    list->length++;
    return OK;
}

void testList() {
    int num = 0;
    ElemType *el = &num;
    List list;
    List *listPoint = &list;
    ListInsert(listPoint, 1, 1);
    ListInsert(listPoint, 2, 2);
    ListInsert(listPoint, 3, 3);
    ListInsert(listPoint, 4, 4);
    ListInsert(listPoint, 2, 996);
    ListInsert(listPoint, 10, 777);
    Status  result = ListInsert(listPoint, 11, 777);
    printf("%d", result);
}

