# 개념

## 클래스

- data(variables) 와 operation(method)의 집합
- Ruby에서는 Object root class가 존재
    - 여기서 class가 파생
- entity를 나타내는데 사용되는 속성 및 메소드의 캡슐화
- A class provides a definition for instances of the class, also known as objects.

## 객체

- 클래스의 instance
- 실체에 초점
- 클래스의 행동을 명시
- 할당된 메모리 블록

## 인스턴스

- 구현된 실체
- 메모리에 할당됨
    - new
- 관계에 초점

## 모듈

- Modules serve as a mechanism for namespaces
    - 보통 Ruby나 다른 언어에서는 이런 개념으로 쓰이는 듯
- C#에서는
    - System.Reflection.Module
    - A module is a portable executable file, such as type.dll or application.exe, consisting of one or more classes and interfaces
    - There may be multiple namespaces contained in a single module, and a namespace may span multiple modules
    - A module is a compiled dll or exe, it contains the compiled classes
    - Compiled DLLs or EXEs are called assemblies

## 구조체

- 구조체는 값타입 vs 클래스는 참조타입
    - 값타입은 **스택** 메모리에 생성
    - 참조타입은 **힙** 메모리에 생성
- 사용하는 이유?
    - 클래스를 참조를 해야하는데 시간적, 비용적 낭비를 줄이고자 사용
        - 클래스는 메모리 절약
    - **값 타입**이기 때문에 값을 복사해서 사용
    - 속도가 빠름
- 상속이 불가능함
    - **sealed (C#)**
- **new로 메모리 생성**
- GC로 관리할 수 없음
    - 메모리 관리 필요 없음
- 변수들의 집합
    - 많은 양의 변수가 있다면 구조체 사용을 지양 (하지 말라고)
        - 스택은 크기가 제한적이기 때문에 스택 오버플로우할 수 있음
- 변수의 크기가 작거나, 수명이 짧고, 자주 할당되는 객체는 구조체로