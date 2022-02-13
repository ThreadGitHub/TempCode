cmake_minimum_required(VERSION 3.21)
project(CDataStruct C)

set(CMAKE_C_STANDARD 99)

add_executable(CDataStruct main.c list/list.c list/list.h)
