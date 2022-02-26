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
    if(i < 1 || i > maxLength){
        return Error;
    }
    if(i <= list->length){
        for(int k = list->length-1;k >= i-1;k--){
            list->data[k+1] = list->data[k];
        }
    }
    list->data[i-1] = e;
    list->length+=1;
    return OK;
}

void testList() {
    int num = 0;
    ElemType *el = &num;
    List list;
    list.length = 0;
    int length = sizeof(list.data) / sizeof(list.data[0]);
    for(int i = 0;i < length;i++){
        list.data[i] = 0;
    }

    List *listPoint = &list;
    ListInsert(listPoint, 1, 1);
    ListInsert(listPoint, 2, 2);
    ListInsert(listPoint, 3, 3);
    ListInsert(listPoint, 4, 4);
    ListInsert(listPoint, 2, 996);
    ListInsert(listPoint, 10, 777);
    Status  result = ListInsert(listPoint, 11, 777);

    printf("顺序表 : ");
    for (int i = 0; i < length; ++i){
        printf("%d\t", list.data[i]);
    }

    ElemType elemType = 0;
    ElemType *elemType_p = &elemType;
    getElem(list, 4, elemType_p);
    printf("\n第4个元素 : %d", *elemType_p);
    getElem(list, 2, elemType_p);
    printf("\n第2个元素 : %d", *elemType_p);
    getElem(list, 10, elemType_p);
    printf("\n第10个元素 : %d", *elemType_p);
}