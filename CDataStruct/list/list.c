#include <stdio.h>
#include "list.h"

Status initList(List *list){
    list->length = 0;
    int length = sizeof(list->data) / sizeof(list->data[0]);
    for(int i = 0;i < length;i++){
        list->data[i] = 0;
    }
}

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
    //这里是可以允许的范围是 1 - 目前所在数量 +1  这个+1就是允许在队尾追加
    if(i < 1 || i > list->length + 1){
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

Status ListDelete(List *list, int i, ElemType *e){
    if(i == 0){
        return Error;
    }
    if(i < 0 || i > list->length){
        return Error;
    }
    i -= 1;
    *e = list->data[i];
    if(i != list->length){
        for(int num = i+1;num < sizeof (list->data) / sizeof (list->data[0]);num++){
            list->data[num-1] = list->data[num];
        }
    }
    list->length--;
    return OK;
}

void testList() {
    int num = 0;
    ElemType *el = &num;

    List list;
    List *listPoint = &list;

    //初始化
    initList(listPoint);

    //插入数据
    ListInsert(listPoint, 1, 1);
    ListInsert(listPoint, 2, 2);
    ListInsert(listPoint, 3, 3);
    ListInsert(listPoint, 4, 4);
    ListInsert(listPoint, 2, 996);
    ListInsert(listPoint, 10, 777);
    Status  result = ListInsert(listPoint, 11, 777);

    ElemType elemType = 0;
    ElemType *elemType_p = &elemType;

    *elemType_p = 0;
    getElem(list, 4, elemType_p);
    printf("第4个元素 : %d", *elemType_p);

    *elemType_p = 0;
    getElem(list, 2, elemType_p);
    printf("\n第2个元素 : %d", *elemType_p);

    *elemType_p = 0;
    getElem(list, 10, elemType_p);
    printf("\n第10个元素 : %d", *elemType_p);

    //删除第一个元素
    ListDelete(listPoint, 1,elemType_p);
    //删除第三个元素
    ListDelete(listPoint, 3,elemType_p);

    printf("\n顺序表 : ");
    for (int i = 0; i < list.length; ++i){
        printf("%d\t", list.data[i]);
    }
}