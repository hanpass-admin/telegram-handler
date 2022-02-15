# 전문(전문 통신) 핸들러
전문통신할때 사용하는 전문에 대하여 
1. 데이터 추출
2. 전문 수정

의 기능을 하는 모듈.

전체 전문에 대한 스팩정의를 요구하지 않으며, 필요한 부분만 읽고, 필요한 부분만 수정하여 사용할 수 있습니다.(cherry picking) 
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
