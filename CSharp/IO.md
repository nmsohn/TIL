# Reader

## StreamReader

- Implements a TextReader that reads characters from a byte stream in a particular encoding.

```csharp
using(StreamReader reader = new StreamReader(Socket.GetStream(), Encoding.UTF8)) {
    while(reader.Peek() >= 0) {
        Console.WriteLine(reader.ReadLine()); // or something...
    }
}
```

## BufferedReader

- 자바에서는 아래와 같이 사용

    [Scanner vs BufferedReader](https://www.notion.so/Scanner-vs-BufferedReader-713c7ca56ac24366af2d7c79a2ce9b13) 

    ```csharp
    BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream(), "UTF8"));

    BufferedReader in = new BufferedReader(new InputStreamReader(System.in, "UTF8"));
    ```

## Console.ReadLine()

```csharp
using System;
using System.IO;
using System.Linq;

namespace FastStdIO {
    class Program {
        static void Main(string[] args) {
            var n = int.Parse(Console.ReadLine());
            for (int i = 0; i < n; i++) {
                var sum = Console.ReadLine().Split().Select(int.Parse).Sum();
                Console.WriteLine(sum);
            }
        }
    }
```

## [BufferedStream](https://www.notion.so/BufferedStream-70ebf73909844f84a8c9dfe286739f45)  → StreamReader와 조합해서 씀

- copies bytes from the current buffered stream to a byte span and advances the position within the buffered stream by the number of bytes read.
- 예시

    ```csharp
    using System;
    using System.IO;
    using System.Linq;

    namespace FastStdIO {
        class Program {
            static void Main(string[] args) {
                using (var inStream = new BufferedStream(Console.OpenStandardInput()))
                using (var outStream = new BufferedStream(Console.OpenStandardOutput()))
                using (var reader = new StreamReader(inStream))
                using (var writer = new StreamWriter(outStream)) {
                    int n = int.Parse(reader.ReadLine());

                    for (int i = 0; i < n; i++) {
                        var sum = reader.ReadLine().Split().Select(int.Parse).Sum();
                        writer.WriteLine(sum);
                    }
                }
            }
        }
    }
    ```

# Writer

## StreamWriter

- Console 자체에는 AutoFlush 등의 옵션이 없다. TextWriter 인 Console.Out 에도 없었고 대신 AutoFlush를 지원하는 StreamWriter를 쓰면 될 것

```csharp
using System;
using System.IO;
using System.Linq;

namespace FastStdIO {
    class Program {
        static void Main(string[] args) {
            using (var outStream = new BufferedStream(Console.OpenStandardOutput()))
            using (var writer = new StreamWriter(outStream)) {
                int n = int.Parse(Console.ReadLine());

                for (int i = 0; i < n; i++) {
                    var sum = Console.ReadLine().Split().Select(int.Parse).Sum();
                    writer.WriteLine(sum);
                }
            }
        }
    }
}
```

- auto flush

```csharp
using System;
using System.IO;
using System.Linq;

namespace FastStdIO {
    class Program {
        static void Main(string[] args) {
            int n = int.Parse(Console.ReadLine());
            var writer = new StreamWriter(Console.OpenStandardOutput());
            writer.AutoFlush = false;

            for (int i = 0; i < n; i++) {
                var line = Console.ReadLine();
                var sum = line.Split().Select(int.Parse).Sum();
                writer.WriteLine(sum.ToString());
            }

            writer.Close();
        }
    }
}
```

## BufferedWriter

- 자바에서 사용

[Scanner vs BufferedReader](https://www.notion.so/Scanner-vs-BufferedReader-713c7ca56ac24366af2d7c79a2ce9b13) 

## Console.WriteLine()

```csharp
using System;
using System.IO;
using System.Linq;

namespace FastStdIO {
    class Program {
        static void Main(string[] args) {
            var n = int.Parse(Console.ReadLine());
            for (int i = 0; i < n; i++) {
                var sum = Console.ReadLine().Split().Select(int.Parse).Sum();
                Console.WriteLine(sum);
            }
        }
    }
```

## StringBuilder

```csharp
using System;
using System.Linq;
using System.Text;

namespace FastStdIO {
    class Program {
        static void Main(string[] args) {
            int n = int.Parse(Console.ReadLine());
            var result = new StringBuilder();

            for (int i = 0; i < n; i++) {
                var sum = Console.ReadLine().Split().Select(int.Parse).Sum();
                result.AppendLine(sum.ToString());
            }

            Console.Write(result);
        }
    }
}
```