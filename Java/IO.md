
When you are doing ps in Java, there are mainly two ways to do stdin/stdout. One is using Scanner and PrintStream and another is using BufferedReader and BufferedWriter. Which one is showing better performance during ps?

# Scanner vs BufferedReader

## Buffer 🗃

Scanner and BufferedReader each genereates the instance of 1024 chars and of 8192 chars. If you are reading large amount of string, it is better using BufferedReader. But you rarely encounters algorithms problems that require you to load large amount of string. This may not be the critical difference. 

## Convenience 🏝

While BufferedReader can only read data, Scanner provides more parsing functionality like nextInt(). BufferedReader can only read string so if you want to get integer, you will need to parse it using `Integer.parseInt(br.readLine())`. Also, BufferedReader can consider new line as an end. If there is white space, you need to use StringTokenizer.

Another difference is that Scanner does not require tyr-catch but BufferedReader has CheckedException. You need to catch IOException as mandatory.

## Speed 🌀🌀

 BufferedReader use a technique called buffering and reduces the frequency of calling data from disk or stdin. Instead of reading string every time, it reads data in chunks at once and copy them into main memory (buffer). A user reads data from buffer when needed. This is a lot faster. This is the main reason using BufferedReader over Scanner.

## Examples

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
BufferedReader br = new BufferedReader(new InputStreamReader(System.in));  //Decorating with InputStreamReader. Convert byte 스트림 into char using InputStreamReader
//Input is done per line. If there are whitespaces in one line, parsing is required. USe StringTokenizer.
StringTokenizer st = new StringTokenizer(br.readLine()); 
//One line
Integer.parseInt(st.nextToken()); 
//New line 
Integer.parseInt(br.readLine()); 
br.close();
```

# PrintStream vs BufferedWriter

The comparison of these two are similar to the explanation above. Using BufferedWriter is faster but you need to do `close()`. `flush()` is not required as BufferedWriter flushes when the buffer is full. If needed, you can do it manually.

## Examples

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
