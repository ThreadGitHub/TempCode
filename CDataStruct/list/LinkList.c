#include <stdio.h>
#include <stdlib.h>
#include "LinkList.h"
#include <time.h>

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

Status CreateListHead(LinkList *list, int n){
    //创建 list的头节点
    LinkList node = (LinkList)malloc(sizeof (Node));
    (*node).data = 0;
    (*node).next = NULL;
    *list = node;
    for(int i = 0;i < n;i++){
        //重置随机数种子
        srand(time(NULL));
        int randNum = rand() % 100 + 1;
        //头结点的元素后面的第一个元素持续拼接新创建的节点 ，拆开 头结点和 之后的 节点 然后拼接上新创建的节点
        Node* item = malloc(sizeof (Node));
        (*item).data = randNum;
        (*item).next = (*list)->next;
        (*list)->next = item;
    }
}

void testList(){
    //定义链表
    LinkList list;

    //根据头插法创建链表
    CreateListHead(&list, 1);

    //获取元素
    ElemType *item = (int*)malloc(sizeof (int));
    getElem(list, 2, item);
    printf("%d", *item);

    //插入元素
    ListInsert(&list, 1, 3);

    //删除元素
    ListDelete(&list, 1, item);
}

