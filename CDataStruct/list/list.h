//
// Created by Thread on 2022/2/12.
//

#ifndef CDATASTRUCT_LIST_H
#define CDATASTRUCT_LIST_H

#define maxLength 10
#define OK 1
#define Error 0
#define True 1
#define False 0
typedef int ElemType;
typedef int Status;

typedef struct List {
    ElemType data[maxLength];
    int length;
} List;

void testList();

/**
 * 获取线性表中指定元素的值
 * @param list 线性表
 * @param i 获取元素的索引
 * @param e 元素的返回值
 * @return
 */
Status getElem(List list, int i, ElemType *e);

/**
 * 线性表 插入操作
 * @param list 线性表
 * @param i 插入元素的位置
 * @param e 接收值
 * @return
 */
Status ListInsert(List *list, int i, ElemType e);

/**
 * 线性表 删除操作
 * @param list 线性表
 * @param i 删除元素位置
 * @param e 接收值
 * @return
 */
Status ListDelete(List *list, int i, ElemType *e);
#endif //CDATASTRUCT_LIST_H