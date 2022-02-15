# 전문(전문 통신) 핸들러
전문통신할때 사용하는 전문에 대하여 
1. 데이터 추출
2. 데이터를 전문으로 변환

의 기능을 하는 모듈.

## 사용법
```java
TelegramHandler telegramHandler = new TelegramHandler("12345  testname     ");
telegramHandler.field(1, 5); // "12345"

telegramHandler.changeField(1, 5, "22");
telegramHandler.field(1, 5); // "22"
    
telegramHandler.telegram(); // "22     testname     " <- byte[]
```
자세한 사용법은
TelegramHandlerTest 클래스 참조.
