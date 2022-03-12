//
// Created by Thread on 2022/3/9.
//

#ifndef CDATASTRUCT_LINKLIST_H
#define CDATASTRUCT_LINKLIST_H
typedef int ElemType;
typedef int Status;
#define OK 1
#define Error 0

/**
 * 链式结构
 */
typedef struct Node{
    ElemType data;
    struct Node *next;
} Node;
typedef Node *LinkList;

/**
 * 获取线性表元素
 * @param list
 * @param e
 * @return
 */
Status getElem(LinkList list, int i, ElemType *e);

/**
 * 插入元素
 * @param list
 * @param i
 * @param e
 * @return
 */
Status ListInsert(LinkList *list, int i, ElemType e);

/**
 * 删除元素
 * @param list
 * @param i
 * @param e
 * @return
 */
Status ListDelete(LinkList *list, int i, ElemType *e);

/**
 * 创建链表整表 头插法
 * @param list
 * @param n
 * @return
 */
Status CreateListHead(LinkList *list, int n);

void testList();
#endif //CDATASTRUCT_LINKLIST_H
