#include <stdio.h>
#include <stdlib.h>
#include "LinkList.h"

Status getElem(LinkList list, int i, ElemType *e){
    //指向第一个节点
    LinkList node = list->next;
    int j = 1;

    //node 这个 如果为 NULL 的话是相当于 false
    while(node && j < i){
        node = list->next;
        j++;
    }
    //如果 node == null 的话 取反 为 true
    if(!node || j > i){
        return Error;
    }
    *e = node->data;
    return OK;
}

Status ListInsert(LinkList *list, int i, ElemType e){
    //查找到要插入的元素的位置
    LinkList p = *list;
    int j = 1;
    while(p && j < i){
        p = (*list)->next;
        j++;
    }

    //如果 p = null 取反为 true
    if(!p || j > i){
        return Error;
    }

    LinkList node = (LinkList)malloc(sizeof (Node));
    node->data = e;
    node->next = p->next;
    p->next = node;
    return OK;
}

Status ListDelete(LinkList *list, int i, ElemType *e){
    //查找到要插入的元素的位置
    LinkList p = *list;
    int j = 1;
    while(p && j < i){
        p = (*list)->next;
        j++;
    }

    //如果 p = null 取反为 true
    if(!p->next || j > i){
        return Error;
    }

    LinkList node = p->next;
    *e = node->data;
    p->next = node->next;
    free(node);
    return OK;
}

void testList(){
    LinkList list;
    Node node;
    node.data = 2;
    Node starNode;
    starNode.data = 1;
    starNode.next = &node;
    list = &starNode;

    ElemType *item = (int*)malloc(sizeof (int));
    getElem(list, 2, item);
    printf("%d", *item);

    //插入元素
    ListInsert(&list, 1, 3);

    //删除元素
    ListDelete(&list, 1, item);
}