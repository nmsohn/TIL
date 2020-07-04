
알고리즘 문제를 풀다보면 Java로 stdin/stdout 하는 방법이 크게 두 가지로 나누어진다. 하나는 Scanner와 PrintStream를 사용하는 것이고 다른 하나는 BufferedReader와 BufferedWriter를 사용하는 것이다.  알고리즘 문제를 풀 때 둘 중 어느 방법이 더 나을까?

# Scanner vs BufferedReader

## 버퍼메모리

Scanner는 1024 chars , BufferedReader는 8192 chars  크기를 갖는 인스턴스를 생성한다. 만약 읽어오는 string 양이 많으면 BufferedReader를 쓰는 것이 낫다. 다만 알고리즘 문제를 풀 때 방대한 양을 불러올 일이 없기 때문에 버퍼 메모리 차이는 크게 상관 없다고 본다.

## 용이성

BufferedReader는 데이터만 읽어들일 수 있는 반면에 Scanner는 data를 nextInt() 와 같이 파싱하는 기능이 있다.  BufferedReader는 오직 string만 읽을 수 있기 때문에 int로 불러올려면 Integer.parseInt(br.readLine()) 와 같이 따로 파싱을 해주어야 한다. 또 BufferedReader는 new line만 경계로 인식하기 때문에 white space가 있는 경우 StringTokenizer를 써줘야 한다.

또, Scanner는 따로 예외 처리를 해줄 필요가 없지만 BufferedReader는 CheckedException이 있어 반드시 IOException를 처리해야한다. 

## 속도

 BufferedReader는 buffering이라는 기능을 써서 디스크나 stdin에서 불러오는 횟수를 줄여 준다. 매번 읽어오는 것이 아니라 한번에 데이터 chunk로 나눠서 읽어온 후 메인 메모리(버퍼)에 복사한다. 사용자가 필요할 때마다 버퍼에서 불러오기 때문에 속도가 빠르다.  아마 BufferedReader를 사용하는 가장 강력한 이유일 것이다.

## 사용법

### Scanner

```
Scanner sc = new Scanner(System.in); 
sc.nextInt(); 
while(sc.next()){ 
	//... 
}
```

### BufferedReader

```
BufferedReader br = new BufferedReader(new InputStreamReader(System.in));  //InputStreamReader를 데코레이팅해서 사용. byte 스트림을 char 스트림으로 바꿔주는 InputStreamReader
//라인 단위로 입력받기 때문에, 한 줄에 공란을 경계로 여러 값이 입력된 경우라면 파싱이 필수 -> StringTokenizer 사용하면 됨 
StringTokenizer st = new StringTokenizer(br.readLine()); 
//한줄로 있는거 
Integer.parseInt(st.nextToken()); 
//뉴라인 
Integer.parseInt(br.readLine()); 
br.close();
```

# PrintStream vs BufferedWriter

이 둘도 위에 둘을 비교한 것과 비슷하다. BufferedWriter를 쓰는 편이 성능면에서 더 효율적이다. 대신 BufferedWriter를 사용하면 항상 close()를 해주어야 한다. flush()의 경우 버퍼가 다 차면 알아서 flush해주지만 필요할 경우 직접 flush()해주면 된다.

## 사용법

### PrintStream

```
System.out.println("string");
```

### BufferedWriter

```
int i = 0;
BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
bw.write(String.valueOf(i));
bw.flush();
bw.close();
```
