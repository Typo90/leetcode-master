# Union-Find

## quick-find

数组中存放值，相同的值代表相连

id[1,2,2,3,4,1]

node1 连接 node6

node2 连接 node3

## quick-union

数组中存放父亲的值

id[1,3,4,4]

node1的root是自己

node2的root是node3

node3的root是node4

node4的root是自己

## weighted QU

多一个额外的数组存放每个tree的大小

union的时候永远是小的tree插到大的

## path compression

每个子tree可以直接连到根节点

## 性能

| algorithm                     | time      |
| ----------------------------- | --------- |
| quick-find                    | M N       |
| quick-union                   | M N       |
| weighted QU                   | N + MlogN |
| QU + path compression         | N + MlogN |
| weighted QU+ path compression | N + MlgN  |

2