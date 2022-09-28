# OPP三大特点

## Boxing封装

封装好比你去银行取钱，你只需要提供卡号和密码，取的步骤，取得过程都不需要你操心

降低耦合

## Extend继承

## Polymorphism多态

# HashMap

# String

String底层实现是char[]数组，被final关键词修饰

修改string类型的值本质上是创建了一个新的对象，然后引用到新对象

## String Builder & String Buffer

| String                    | String Builder            | String Buffer           |
| ------------------------- | ------------------------- | ----------------------- |
| 不可变                    | 可变                      | 可变                    |
| 线程安全                  | 线程不安全                | 线程安全，性能低        |
| 操作数据量少/不需操作数据 | 操作数据多/不考虑线程安全 | 操作数据多/考虑线程安全 |

# 不同数据结构的长度

| Name                      | function  |
| ------------------------- | --------- |
| String                    | .length() |
| int[], double[], String[] | .length   |
|                           |           |

# Arrays.sort()



## Integer数组

```java
        Integer[] nums2 = new Integer[]{1,3,5,6};

        Arrays.sort(nums2, new Comparator<Integer>(){
            @Override
            public int compare(Integer a, Integer b){
                return a-b;
            }
        });
```



## 二维数组

数组a[0]从小到大; a[1]从小到大

```java
        int[][] nums = new int[][]{{1,3},{7,32},{3,5},{7,25}};

		Arrays.sort(nums, new Comparator<int[]>(){
            @Override
            public int compare(int[] a, int[] b) {
                if(a[0]==b[0]){
                    return a[1]-b[1];
                }else{
                    return a[0]-b[0];
                }
            }
        });
```

简洁写法

```java
        Arrays.sort(nums, (a,b)-> {
            if (a[0] == b[0]) {
                return b[1] - a[1];
            } else {
                return a[0] - b[0];
            }
        });
```

# 正则表达式

| 符号 | 意义                                             |
| ---- | ------------------------------------------------ |
| ?    | 匹配0个或1个                                     |
| *    | 匹配0个或多个                                    |
| +    | 匹配1个以上                                      |
| {}   | {6}指定出现6次， {2，6}出现2-6之间，{2，}2次以上 |
| ()   | (匹配括号中的多个字符) (ab)*                     |
| \|   | (cat \| dog)匹配cat 或dog                        |
| []   | [abc]只匹配abc [a-z] [A-Z] [0-9]                 |
| ^    | ^[0-9] 匹配0-9以外的字符                         |
| \d   | 等同于[0-9]                                      |
| \w   | 单词字符                                         |
| \s   | 空白字符                                         |
| \D   | 非数字字符                                       |
| \W   | 非单词字符                                       |







