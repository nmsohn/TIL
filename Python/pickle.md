# 개념

- 파이썬 객체 구조를 시리얼화, 역시리얼화하는 binary 프로토콜을 제공
- Pickling
    - python 객체를 byte stream으로 변환하는 것
- Unpickling
    - byte stream을 python 객체로 변환
- The pickle module is not secure. Only unpickle data you trust
    - 출처가 명확하지 않을 때 unpickle하지 말 것

# 사용

```python
import pickle
my_list = ['a','b','c']
 
## Save pickle
with open("data.pickle","wb") as fw:
    pickle.dump(my_list, fw)
 
## Load pickle
with open("data.pickle","rb") as fr:
    data = pickle.load(fr)
print(data)
#['a', 'b', 'c']
```