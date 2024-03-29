# 로또 프로그램 개발

## 개요

본 프로그램은 사용자가 입력한 금액에 따라 로또를 구매하고, 당첨 결과를 확인할 수 있는 기능을 제공합니다. 로또의 구매 및 당첨 확인 과정을 통해 로또의 무의미함을 직접 체험해볼 수 있습니다.

## 기능 요구사항

1. **로또 구매**
    - 로또 한 장의 가격은 1KW입니다.
    - 사용자는 원하는 만큼의 로또를 구매할 수 있으며, 입력한 금액만큼 최대한 많은 로또를 구매합니다.
    - 예: 사용자가 40KW을 입력하면, 40장의 로또를 구매합니다.

2. **로또 번호**
    - 한 장의 로또는 1부터 45까지의 숫자 중 6개로 구성됩니다.
    - 한 장의 로또 내에서 중복된 숫자는 없어야 합니다.

3. **수동 및 자동 로또 구매**
    - 사용자는 일부 로또를 수동으로 구매할 수 있으며, 나머지는 자동으로 구매됩니다.
    - 예: 40장 중 1장을 수동으로 구매하고자 할 경우, 사용자로부터 6개의 숫자를 입력받고 나머지 39장은 랜덤으로 생성됩니다.

4. **당첨 번호**
    - 당첨 번호는 사용자가 직접 입력하거나 프로그램이 랜덤으로 생성할 수 있습니다.

5. **로또 출력**
    - 구매한 로또는 화면에 한 줄씩 출력되며, 각 로또 내의 숫자는 오름차순으로 정렬되어야 합니다.

6. **당첨 결과 출력**
    - 당첨 번호와 함께 각 등수별 당첨된 횟수를 출력할 수 있습니다.
    - 당첨금의 단위는 카카오 원(KW)으로 통일합니다.

7. **당첨금**
    - 1등(6개 번호 일치): 100,000 KW
    - 2등(5개 번호 일치): 5,000 KW
    - 3등(4개 번호 일치): 100 KW
    - 4등(3개 번호 일치): 5 KW
    - 그 이하: 낙첨

## 선택 사항

프로젝트에 최소 한 개의 선택 사항을 적용해야 하며, 더 고도화된 로또 프로그램을 원하는 경우 2개 이상의 선택 사항을 시도할 수 있습니다.

1. **모든 Validation 세세하게 지정하기**
    - 코틀린의 `require`, `assert`, `check`을 활용하여 모든 입력 및 상태에 대한 검증을 구현합니다.

2. **보너스 번호 추가**
    - 당첨 번호에 보너스 번호를 추가하여, 숫자 5개와 보너스 번호가 일치하는 경우를 2등으로 합니다. 기존의 2등과 3등은 각각 3등과 4등으로 변경됩니다.

3. **코드와 도메인 분리**
    - 각 코드의 역할과 도메인에 따라 파일 또는 패키지를 분리하여 구조화합니다.

4. **전략 패턴 적용**
    - 수동 로또와 자동 로또를 생성하는 로직을 전략 패턴으로 구현합니다.

5. **수익금 재투자**
    - 수익금으로 구매 가능한 최대한의 로또를 다시 구매하고, 돈이 0KW가 될 때까지 무한 루프를 돌리도록 구현합니다.

6. **테스트 코드 작성**
    - jUnit5, assertJ, mockito, kotest 등을 활용한 테스트 코드를 작성합니다.

7. **일급 컬렉션 사용**
    - 여러 컬렉션을 래핑하고 객체 간의 협력 관계를 구현합니다.

## 객체 지향 생활 체조 원칙

개발 과정에서는 [객체 지향 생활 체조 원칙](https://jamie95.tistory.com/99)을 지키면서 프로그래밍을 해보는 것을 추천합니다.
