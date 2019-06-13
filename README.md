﻿# 대전 이젠 아카데미

## Fulfillment Service 구현 프로젝트

Amazon Fulfilment Service와 비슷하게 창고 시스템을 개발하는 프로젝트 입니다. (3인 1조)

[My GitHub Blog, Fulfillment Service Link](https://baekjungho.github.io/project-ezenfulfillment/)

## 기간 : 2019-04-30 ~ 2019-05-17

### 사용 언어 

	Java, JSP, Servlet, EL, JSTL, MySQL

### Tool

	Eclipse, HeidiSQL, Tomcat, BootStrap

### 형상관리

	GitHub

### 협업방식

	Feature Branch Workflow 

### 구현 내용

- __Content__

    - 귀하는 귀하 소유의 창고를 이용하여 풀필먼트 서비스(Fulfillment Service)를 하고자 한다.

    - 귀하는 3개 이상의 쇼핑몰, 4개의 운송회사(경기, 중부, 영남, 서부물류) 및 5개 이상의 구매처와 거래하고 있다.

    - 쇼핑몰로부터는 송장을 CSV 형태로 받아서 처리한다.

    - 송장에는 받는 사람의 이름, 전화번호, 주소와 배달할 품목의 제품코드, 제품명, 수량이 들어가 있다.

    - 전일 오후 6시부터 금일 오전 9시까지의 주문은 오전 9시에 처리하고, 금일 오전 9시부터 오후 6시까지의 주문은 오후 6시에 처리한다. 처리해야 할 것은 운송회사의 트럭에 운송지 별로 화물을 적재하고 송장을 운송회사에 전달하는 것이다. (출고)

    - 창고에 보관되어 있는 물품은 최소한 30가지 이상이어야 하고, 물품을 관리하기 위해서 물품의 ID, 물품명, 사진, 개별가격, 재고수량을 보관하여야 한다.
    재고수량이 10개 미만으로 떨어지는 순간 구매처에 발주를 하여야 하고, 구매처는 발주한 다음날 오전 10시에 납품을 한다. (입고) 재고수량이 모자라면 운송을 할 수 없다.

    - 관리자는 언제든지 창고의 재고를 파악할 수 있어야 하고(재고조사), 영업을 위해서 창고에서 보관하고 있는 물품을 사진을 포함하여 잠재 고객에게 보여줄 수 있어야 한다.

    - 매월 단위로 쇼핑몰에 대금을 청구하는데, 청구 금액은 물품 가격과 물품 가격의 10%에 해당하는 서비스료 및 1개의 송장당 10,000원이다. 구매처와 운송회사에는 매월 단위로 물품 가격과 운송비를 지급한다.

    - 관리자는 월 단위로 판매 내역, 발주 내역, 운송 내역 및 매출 총이익을 알 수 있어야 한다. 구매처와 운송회사는 시스템에 로그인해서 일별/월별 주문내역을 확인할 수 있어야 한다.
    
### 진행 과정

- __2019-04-30(화)__

    - 역할 분담
    - 화면 동작 과정 설계
    - 화면 UI 설계
    - DB Table 설계(상품목록, 관리자, 잠재고객, 송장 CSV 파일)

- __2019-05-01(수)__

    - DB Table 설계(송장, 운송회사, 구매처)
    - MVC 설계(Model, View, Controller)
    - View 구현(index.jsp, _top.jspf, _nav.jspf, _bottom.jspf, loginForm.jsp, registerForm.jsp)

- __2019-05-02(목)__

    - DTO, DAO 설계, SQL 쿼리문 작성
    - Controller session, attribute, parameter 설계
    - View 구현	

- __2019-05-03(금)__

    - 프로젝트 계획발표 1차 : 업무분장, DB설계, 화면초안
    - Controller, Model 구현
    - View 구현

- __2019-05-07(화)__

    - 창고관리자 및 상품목록 기능 구현
    - 형상관리를 위해 Github의 Feature Branch Workflow를 이용
    - Oragnization, Project 생성
    - View 구현
    
- __2019-05-08(수)__

    - 송장, 청구, 지급 Model 구현
    - 개발과 동시에 프로그램 설계 및 기능을 Wiki에 작성
    - View 구현

- __2019-05-09(목)__

    - 발주, 상품목록, 재고조사 및 대금지급 컨트롤러 구현
    - 모델 각종 메소드 기능단위로 추가
    - View 구현

- __2019-05-10(금)__

    - 출고, 청구 등 컨트롤러 구현 및 수정
    - 모델 각종 메소드 기능단위로 추가
    - View 구현

- __2019-05-11(토)__

    - 중간점검

- __2019-05-12(일)__

    - CSV 파일 컨트롤러 구현

- __2019-05-13(월)__

    - 리팩토링
    - 기능단위 구현으로 방향 전환

- __2019-05-14(화)__

    - 리팩토링
    - 기능단위 구현(로그인, 회원가입, 재고조사, 상품목록출력, 발주)

- __2019-05-15(수)__

    - 리팩토링
    - 기능단위 구현(지급, 송장처리 파일컨트롤러)
    
- __2019-05-16(목)__

    - 리팩토링
    - 기능단위 구현(송장처리 완료, 출고, 지급, 발주)