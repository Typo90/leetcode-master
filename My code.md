# 1.Arrays

## 704. Binary Search

Given an array of integers `nums` which is sorted in ascending order, and an integer `target`, write a function to search `target` in `nums`. If `target` exists, then return its index. Otherwise, return `-1`.

You must write an algorithm with `O(log n)` runtime complexity.

**Example 1:**

```
Input: nums = [-1,0,3,5,9,12], target = 9
Output: 4
Explanation: 9 exists in nums and its index is 4
```

**Example 2:**

```
Input: nums = [-1,0,3,5,9,12], target = 2
Output: -1
Explanation: 2 does not exist in nums so return -1
```

### Solution

```java
class Solution {
    public int search(int[] nums, int target) {
        if(nums[0]>target || nums[nums.length-1]<target){
            return -1;
        }
        
        int left = 0;
        int right = nums.length-1;
        int mid;
        while (left<=right){
            mid = left + (right-left)/2;
            if(nums[mid] < target){
                left++;
            }else if (nums[mid] > target){
                right--;
            }else{
                
                return mid;
            }
        }
        return -1;
    }
}
```

## 27. Remove Element

Given an integer array `nums` and an integer `val`, remove all occurrences of `val` in `nums` [**in-place**](https://en.wikipedia.org/wiki/In-place_algorithm). The relative order of the elements may be changed.

Since it is impossible to change the length of the array in some languages, you must instead have the result be placed in the **first part** of the array `nums`. More formally, if there are `k` elements after removing the duplicates, then the first `k` elements of `nums` should hold the final result. It does not matter what you leave beyond the first `k` elements.

Return `k` *after placing the final result in the first* `k` *slots of* `nums`.

Do **not** allocate extra space for another array. You must do this by **modifying the input array [in-place](https://en.wikipedia.org/wiki/In-place_algorithm)** with O(1) extra memory.

**Custom Judge:**

The judge will test your solution with the following code:

```
int[] nums = [...]; // Input array
int val = ...; // Value to remove
int[] expectedNums = [...]; // The expected answer with correct length.
                            // It is sorted with no values equaling val.

int k = removeElement(nums, val); // Calls your implementation

assert k == expectedNums.length;
sort(nums, 0, k); // Sort the first k elements of nums
for (int i = 0; i < actualLength; i++) {
    assert nums[i] == expectedNums[i];
}
```

If all assertions pass, then your solution will be **accepted**.

 

**Example 1:**

```
Input: nums = [3,2,2,3], val = 3
Output: 2, nums = [2,2,_,_]
Explanation: Your function should return k = 2, with the first two elements of nums being 2.
It does not matter what you leave beyond the returned k (hence they are underscores).
```

**Example 2:**

```
Input: nums = [0,1,2,2,3,0,4,2], val = 2
Output: 5, nums = [0,1,4,0,3,_,_,_]
Explanation: Your function should return k = 5, with the first five elements of nums containing 0, 0, 1, 3, and 4.
Note that the five elements can be returned in any order.
It does not matter what you leave beyond the returned k (hence they are underscores).
```

### Solution

```java
class Solution {
    public int removeElement(int[] nums, int val) {
        int fastIndex=0;
        int slowIndex=0;
        for(;fastIndex<nums.length;fastIndex++){
            if(nums[fastIndex]!=val){
                nums[slowIndex] = nums[fastIndex];  
                slowIndex++;
            }

        }
        
        return slowIndex;
    }
}
```

Input [0,1,3,3,5,7] val=3

1 fast = 0 nums[fast]=0, !=3

2 fast=1 slow=2

3 fast=2 skip slow =2

4 fast = 3 skip slow =2

5 fast = 4, 5->2

## 977. Squares of a Sorted Array

Given an integer array `nums` sorted in **non-decreasing** order, return *an array of **the squares of each number** sorted in non-decreasing order*.

**Example 1:**

```
Input: nums = [-4,-1,0,3,10]
Output: [0,1,9,16,100]
Explanation: After squaring, the array becomes [16,1,0,9,100].
After sorting, it becomes [0,1,9,16,100].
```

**Example 2:**

```
Input: nums = [-7,-3,2,3,11]
Output: [4,9,9,49,121]
```

### Solution

#### 暴力循环排序

```java
class Solution {
    public int[] sortedSquares(int[] nums) {
        for(int i=0; i< nums.length; i++){
            nums[i] = nums[i]*nums[i];
        }
        
        Arrays.sort(nums);
        return nums;
    }
}
```

#### 双指针

```java
class Solution {
    public int[] sortedSquares(int[] nums) {
        int[] res = new int[nums.length];
        int left = 0;
        int right = nums.length-1;
        
        
        for(int i=nums.length-1;i>=0;i--){
            if(nums[left]*nums[left]<=nums[right]*nums[right]){
                res[i]=nums[right]*nums[right];
                right--;
            }else if (nums[left]*nums[left]>nums[right]*nums[right]){
                res[i]=nums[left]*nums[left];
                left++;
            }
        }
        return res;
            
        
    }
}
```

## 209. Minimum Size Subarray Sum

Given an array of positive integers `nums` and a positive integer `target`, return the minimal length of a **contiguous subarray** `[numsl, numsl+1, ..., numsr-1, numsr]` of which the sum is greater than or equal to `target`. If there is no such subarray, return `0` instead.

 

**Example 1:**

```
Input: target = 7, nums = [2,3,1,2,4,3]
Output: 2
Explanation: The subarray [4,3] has the minimal length under the problem constraint.
```

**Example 2:**

```
Input: target = 4, nums = [1,4,4]
Output: 1
```

**Example 3:**

```
Input: target = 11, nums = [1,1,1,1,1,1,1,1]
Output: 0
```

### Solution

滑动窗口，核心思路 sum-=nums[left++]

return res == Integer.MAX_VALUE ? 0 : res;

```java
class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        
        int left = 0;
        int right = 0;
        int sum=0;
        int res= Integer.MAX_VALUE;
        
        for(;right<nums.length;right++){
            sum += nums[right];
            
            while(sum>=target){
                res = Math.min(res,right-left+1);
                sum -= nums[left++];
            }
                
        }
        
        return res == Integer.MAX_VALUE ? 0 : res;
    }
}
```

## *59. Spiral Matrix II

Given a positive integer `n`, generate an `n x n` `matrix` filled with elements from `1` to `n2` in spiral order.

 

**Example 1:**

![img](https://assets.leetcode.com/uploads/2020/11/13/spiraln.jpg)

```
Input: n = 3
Output: [[1,2,3],[8,9,4],[7,6,5]]
```

**Example 2:**

```
Input: n = 1
Output: [[1]]
```

### Solution

```java
class Solution {
    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];

        // 循环次数
        int loop = n / 2;

        // 定义每次循环起始位置
        int startX = 0;
        int startY = 0;

        // 定义偏移量
        int offset = 1;

        // 定义填充数字
        int count = 1;

        // 定义中间位置
        int mid = n / 2;
        while (loop > 0) {
            int i = startX;
            int j = startY;

            // 模拟上侧从左到右
            for (; j<startY + n -offset; ++j) {
                res[startX][j] = count++;
            }

            // 模拟右侧从上到下
            for (; i<startX + n -offset; ++i) {
                res[i][j] = count++;
            }

            // 模拟下侧从右到左
            for (; j > startY; j--) {
                res[i][j] = count++;
            }

            // 模拟左侧从下到上
            for (; i > startX; i--) {
                res[i][j] = count++;
            }

            loop--;

            startX += 1;
            startY += 1;

            offset += 2;
        }

        if (n % 2 == 1) {
            res[mid][mid] = count;
        }

        return res;
    }
}
```



# 2.Linked list

## 203. Remove Linked List Elements

Given the `head` of a linked list and an integer `val`, remove all the nodes of the linked list that has `Node.val == val`, and return *the new head*.

 

**Example 1:**

![img](https://assets.leetcode.com/uploads/2021/03/06/removelinked-list.jpg)

```
Input: head = [1,2,6,3,4,5,6], val = 6
Output: [1,2,3,4,5]
```

**Example 2:**

```
Input: head = [], val = 1
Output: []
```

**Example 3:**

```
Input: head = [7,7,7,7], val = 7
Output: []
```

###  Solution

#### 虚拟节点

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummynode = new ListNode(-1, head);
        ListNode pre = dummynode;
        ListNode cur = head;
        
        
        if(head == null){
            return head;
        }
        while(cur!=null){
            
            

            if(cur.val != val){
                pre = cur;
            }else if(cur.val == val){
                pre.next = cur.next;
            }
            
            cur = cur.next;
            
        }
        
        return dummynode.next;
    }
}
```

## 707. Design Linked List

Design your implementation of the linked list. You can choose to use a singly or doubly linked list.
A node in a singly linked list should have two attributes: `val` and `next`. `val` is the value of the current node, and `next` is a pointer/reference to the next node.
If you want to use the doubly linked list, you will need one more attribute `prev` to indicate the previous node in the linked list. Assume all nodes in the linked list are **0-indexed**.

Implement the `MyLinkedList` class:

- `MyLinkedList()` Initializes the `MyLinkedList` object.
- `int get(int index)` Get the value of the `indexth` node in the linked list. If the index is invalid, return `-1`.
- `void addAtHead(int val)` Add a node of value `val` before the first element of the linked list. After the insertion, the new node will be the first node of the linked list.
- `void addAtTail(int val)` Append a node of value `val` as the last element of the linked list.
- `void addAtIndex(int index, int val)` Add a node of value `val` before the `indexth` node in the linked list. If `index` equals the length of the linked list, the node will be appended to the end of the linked list. If `index` is greater than the length, the node **will not be inserted**.
- `void deleteAtIndex(int index)` Delete the `indexth` node in the linked list, if the index is valid.

 

**Example 1:**

```
Input
["MyLinkedList", "addAtHead", "addAtTail", "addAtIndex", "get", "deleteAtIndex", "get"]
[[], [1], [3], [1, 2], [1], [1], [1]]
Output
[null, null, null, null, 2, null, 3]

Explanation
MyLinkedList myLinkedList = new MyLinkedList();
myLinkedList.addAtHead(1);
myLinkedList.addAtTail(3);
myLinkedList.addAtIndex(1, 2);    // linked list becomes 1->2->3
myLinkedList.get(1);              // return 2
myLinkedList.deleteAtIndex(1);    // now the linked list is 1->3
myLinkedList.get(1);              // return 3
```

### Solution

```java
class ListNode{
    
    int val;
    ListNode next;
    
    ListNode(){}
    
    ListNode(int val){
        this.val = val;
    }
    ListNode(int val, ListNode next){
        this.val = val;
        this.next = next;
    }
    
}


class MyLinkedList {

    //dummy head
    ListNode head;
    int size;
    
    public MyLinkedList() {
        head = new ListNode(-1);
        size = 0;
    }
    
    public int get(int index) {
       
        if(index<0 || index >=size){
            return -1;
        }
        ListNode cur = head;
        for(int i=0; i<=index; i++){
            cur = cur.next;
        }
        return cur.val;
    }
    
    public void addAtHead(int val) {
        
        
        addAtIndex(0,val);
    }
    
    public void addAtTail(int val) {
        addAtIndex(size,val);
    }
    
    public void addAtIndex(int index, int val) {
        
        
        
        if( index < 0 || index > size ){
           return; 
        }
        
        size ++;
        
        ListNode pre = head;
        for(int i=0; i<index; i++){
            pre = pre.next;
        }
        
        ListNode addAt = new ListNode(val);
        addAt.next = pre.next;
        pre.next = addAt;
        
    }
    
    public void deleteAtIndex(int index) {
        
        if( index < 0 || index >= size ){
           return; 
        }
        
        size--;
        
        ListNode pre = head;
        for(int i=0; i<index; i++){
            pre = pre.next;
        }
        
        pre.next = pre.next.next;
    }
}

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */
```

## 206. Reverse Linked List

Given the `head` of a singly linked list, reverse the list, and return *the reversed list*.

 

**Example 1:**

![img](https://assets.leetcode.com/uploads/2021/02/19/rev1ex1.jpg)

```
Input: head = [1,2,3,4,5]
Output: [5,4,3,2,1]
```

**Example 2:**

![img](https://assets.leetcode.com/uploads/2021/02/19/rev1ex2.jpg)

```
Input: head = [1,2]
Output: [2,1]
```

**Example 3:**

```
Input: head = []
Output: []
```

### Solution:

#### 单链表反转

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        ListNode temp = null;
        
        
        while(cur != null){
            temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
            
        }
        
        return pre;
    }
}
```

## 24. Swap Nodes in Pairs

Given a linked list, swap every two adjacent nodes and return its head. You must solve the problem without modifying the values in the list's nodes (i.e., only nodes themselves may be changed.)

 

**Example 1:**

![img](https://assets.leetcode.com/uploads/2020/10/03/swap_ex1.jpg)

```
Input: head = [1,2,3,4]
Output: [2,1,4,3]
```

**Example 2:**

```
Input: head = []
Output: []
```

**Example 3:**

```
Input: head = [1]
Output: [1]
```



## 19. Remove Nth Node From End of List

Given the `head` of a linked list, remove the `nth` node from the end of the list and return its head.

 

**Example 1:**

![img](https://assets.leetcode.com/uploads/2020/10/03/remove_ex1.jpg)

```
Input: head = [1,2,3,4,5], n = 2
Output: [1,2,3,5]
```

**Example 2:**

```
Input: head = [1], n = 1
Output: []
```

**Example 3:**

```
Input: head = [1,2], n = 1
Output: [1]
```

###  Solution:

移除倒数第**2**个[3]

```mermaid
graph LR
A[-1]-->B[1]-->C[2]-->D[3]-->E[4]-->F[null]
```

fast先动**2**轮

```mermaid
graph LR
A[-1]-->B[1]-->C[2]-->D[3]-->E[4]-->F[null]

fast-->B
slow-->A
```

fast动到null slow动到要删除的位子 pre是slow前一个

```mermaid
graph LR
A[-1]-->B[1]-->C[2]-->D[3]-->E[4]-->F[null]

fast-->F
slow-->E
pre-->D
```



```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {

        ListNode dummyNode = new ListNode(-1, head);
        ListNode fast = dummyNode;
        ListNode slow = dummyNode;
        
        for(int i=0; i<n; i++){
            fast = fast.next;
        }        
        ListNode pre = null;
        while(fast != null){
            pre = slow;
            fast = fast.next;
            slow = slow.next;
        }
        
        
        pre.next = slow.next;
        slow.next = null;
        
        
        return dummyNode.next;
        
        
    }
}
```

## 160. Intersection of Two Linked Lists

Given the heads of two singly linked-lists `headA` and `headB`, return *the node at which the two lists intersect*. If the two linked lists have no intersection at all, return `null`.

For example, the following two linked lists begin to intersect at node `c1`:

![img](https://assets.leetcode.com/uploads/2021/03/05/160_statement.png)

The test cases are generated such that there are no cycles anywhere in the entire linked structure.

**Note** that the linked lists must **retain their original structure** after the function returns.

**Custom Judge:**

The inputs to the **judge** are given as follows (your program is **not** given these inputs):

- `intersectVal` - The value of the node where the intersection occurs. This is `0` if there is no intersected node.
- `listA` - The first linked list.
- `listB` - The second linked list.
- `skipA` - The number of nodes to skip ahead in `listA` (starting from the head) to get to the intersected node.
- `skipB` - The number of nodes to skip ahead in `listB` (starting from the head) to get to the intersected node.

The judge will then create the linked structure based on these inputs and pass the two heads, `headA` and `headB` to your program. If you correctly return the intersected node, then your solution will be **accepted**.

 

**Example 1:**

![img](https://assets.leetcode.com/uploads/2021/03/05/160_example_1_1.png)

```
Input: intersectVal = 8, listA = [4,1,8,4,5], listB = [5,6,1,8,4,5], skipA = 2, skipB = 3
Output: Intersected at '8'
Explanation: The intersected node's value is 8 (note that this must not be 0 if the two lists intersect).
From the head of A, it reads as [4,1,8,4,5]. From the head of B, it reads as [5,6,1,8,4,5]. There are 2 nodes before the intersected node in A; There are 3 nodes before the intersected node in B.
```

**Example 2:**

![img](https://assets.leetcode.com/uploads/2021/03/05/160_example_2.png)

```
Input: intersectVal = 2, listA = [1,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
Output: Intersected at '2'
Explanation: The intersected node's value is 2 (note that this must not be 0 if the two lists intersect).
From the head of A, it reads as [1,9,1,2,4]. From the head of B, it reads as [3,2,4]. There are 3 nodes before the intersected node in A; There are 1 node before the intersected node in B.
```

**Example 3:**

![img](https://assets.leetcode.com/uploads/2021/03/05/160_example_3.png)

```
Input: intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
Output: No intersection
Explanation: From the head of A, it reads as [2,6,4]. From the head of B, it reads as [1,5]. Since the two lists do not intersect, intersectVal must be 0, while skipA and skipB can be arbitrary values.
Explanation: The two lists do not intersect, so return null.
```

### Solution:

计算两个链表的差值，之后fast挪动差值，再找相同。

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode A = headA;
        ListNode B = headB;
        
        int lenA = 0;
        int lenB =0;
        
        while(A!=null){
            lenA++;
            A = A.next;
        }
        while(B!=null){
            lenB++;
            B = B.next;
        }
        
        int dif = lenA - lenB;
        if(dif<0){
            A = headB;
            B = headA;
            dif = dif*-1;
        }else{
            A = headA;
            B = headB;
        }

        for(int i=0; i<dif ;i++){
            A = A.next;
        }
        
        while(A!=null){
            
            if(A == B){
                return A;
            }
            A = A.next;
            B = B.next;
        }
        
        return null;
    }
}
```

## 142. Linked List Cycle II

Given the `head` of a linked list, return *the node where the cycle begins. If there is no cycle, return* `null`.

There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the `next` pointer. Internally, `pos` is used to denote the index of the node that tail's `next` pointer is connected to (**0-indexed**). It is `-1` if there is no cycle. **Note that** `pos` **is not passed as a parameter**.

**Do not modify** the linked list.

 

**Example 1:**

![img](https://assets.leetcode.com/uploads/2018/12/07/circularlinkedlist.png)

```
Input: head = [3,2,0,-4], pos = 1
Output: tail connects to node index 1
Explanation: There is a cycle in the linked list, where tail connects to the second node.
```

**Example 2:**

![img](https://assets.leetcode.com/uploads/2018/12/07/circularlinkedlist_test2.png)

```
Input: head = [1,2], pos = 0
Output: tail connects to node index 0
Explanation: There is a cycle in the linked list, where tail connects to the first node.
```

**Example 3:**

![img](https://assets.leetcode.com/uploads/2018/12/07/circularlinkedlist_test3.png)

```
Input: head = [1], pos = -1
Output: no cycle
Explanation: There is no cycle in the linked list.
```

 

**Constraints:**

- The number of the nodes in the list is in the range `[0, 104]`.
- `-105 <= Node.val <= 105`
- `pos` is `-1` or a **valid index** in the linked-list.

### Solution

fast指针比slow指针快一步，这样就能相遇

```mermaid
graph LR
1-->2-->3-->4-->5-->6-->3
```



```java
/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode detectCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        
        if( head == null ){
            return null;
        }
        
        while(fast!= null && fast.next !=null){
            
            fast = fast.next.next;
            slow = slow.next;
            if(fast == slow){
                ListNode index1 = head;
                ListNode index2 = fast;
                while(index1 != index2){
                    index1 = index1.next;
                    index2 = index2.next;
                }
                return index1;
            }
            
        }
    
        return null;
    }
}
```

# 3.Hash Table

##  242. Valid Anagram

Given two strings `s` and `t`, return `true` *if* `t` *is an anagram of* `s`*, and* `false` *otherwise*.

An **Anagram** is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.

## 1002.Find Common Characters

Given a string array `words`, return *an array of all characters that show up in all strings within the* `words` *(including duplicates)*. You may return the answer in **any order**.

**Example 1:**

```
Input: words = ["bella","label","roller"]
Output: ["e","l","l"]
Input: s = "anagram", t = "nagaram"
Output: true
```

**Example 2:**

```
Input: words = ["cool","lock","cook"]
Output: ["c","o"]
```

### Solution

```java
class Solution {
    public List<String> commonChars(String[] words) {
         
        int[] aph = new int[26];
        
        
        if(words == null){
            return null;
        }
        
        String firstStr = words[0];
        for(int i=0; i<firstStr.length();i++){
            aph[firstStr.charAt(i)-'a']++;  
        }     
        
        
        for(String str : words){
            
            int[] temp = new int[26];
            for(int i=0; i<str.length();i++){    
                temp[str.charAt(i)-'a']++;       
            }  
            
            for(int k=0; k<26; k++){
                aph[k] = Math.min(temp[k],aph[k]);
            }
            //aph[str.charAt(i)-'a'] = Math.min(temp[str.charAt(i)-'a'],aph[str.charAt(i)-'a']);
         }   
        
        List<String> result = new ArrayList<>();
        
        for( int i=0; i<26; i++){
            
            while(aph[i]!=0){
                char c = (char)(i+'a');
                String str = String.valueOf(c);
                result.add(str);
                aph[i] --;
            }
        }
        return result;
        
    }

```

## 202.Happy Number

Write an algorithm to determine if a number `n` is happy.

A **happy number** is a number defined by the following process:

- Starting with any positive integer, replace the number by the sum of the squares of its digits.
- Repeat the process until the number equals 1 (where it will stay), or it **loops endlessly in a cycle** which does not include 1.
- Those numbers for which this process **ends in 1** are happy.

Return `true` *if* `n` *is a happy number, and* `false` *if not*.

 

**Example 1:**

```
Input: n = 19
Output: true
Explanation:
12 + 92 = 82
82 + 22 = 68
62 + 82 = 100
12 + 02 + 02 = 1
```

**Example 2:**

```
Input: n = 2
Output: false
```

### Solution

```java
class Solution {
    public boolean isHappy(int n) {
        
        Set<Integer> record = new HashSet<>();
        
        while(!record.contains(n) ){
            
            record.add(n);
            n = cal(n);
            
            if(n == 1){
                return true;
            }
        }
        
        return false;
        
        

    }
    
        private int cal(int n){
            int sum = 0;
            int temp = 0;
            while(n!=0){
                temp = (n%10)*(n%10);
                sum += temp;
                n = n/10;
            }
            
            return sum;
        }
}
```

## 349.Intersection of Two Arrays

Given two integer arrays `nums1` and `nums2`, return *an array of their intersection*. Each element in the result must be **unique** and you may return the result in **any order**.

**Example 1:**

```
Input: nums = [2,7,11,15], target = 9
Output: [0,1]
Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].

Input: nums1 = [1,2,2,1], nums2 = [2,2]
Output: [2]
```

**Example 2:**

```
Input: nums = [3,2,4], target = 6
Output: [1,2]
```

**Example 3:**

```
Input: nums = [3,3], target = 6
Output: [0,1]
```

 

**Constraints:**

- `2 <= nums.length <= 104`
- `-109 <= nums[i] <= 109`
- `-109 <= target <= 109`
- **Only one valid answer exists.**



### Solution:

```java
class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> output = new HashSet<>();
        
        if(nums1 == null || nums2 == null){
            return null;
        }
        
        for(int i : nums1){
            set1.add(i);
        }
        
        for(int i : nums2){
            if(set1.contains(i)){
                output.add(i);
            }
        }
        
        int[] res = new int[output.size()];
        int j=0;
        for(int i : output){
            res[j] = i;
            j ++;
        }
        
        return res;
    }
}
```

## 1.Two Sum

Given an array of integers `nums` and an integer `target`, return *indices of the two numbers such that they add up to `target`*.

You may assume that each input would have ***exactly\* one solution**, and you may not use the *same* element twice.

You can return the answer in any order.

### Solution

```java
class Solution {
    public int[] twoSum(int[] nums, int target) {
        
        int[] res = new int[2];
        if( nums==null || nums.length==0){
            return res;
        }
        
        Map<Integer, Integer> map = new HashMap<>();
        
        for(int i=0; i<nums.length; i++){
            
            int temp = target - nums[i];
            
            if(map.containsKey(temp)){
                res[0] = i;
                res[1] = map.get(temp);
            }
            
            map.put(nums[i],i);
            
        }
        
        return res;
    }
}
```

## 454. 4Sum II

Given four integer arrays `nums1`, `nums2`, `nums3`, and `nums4` all of length `n`, return the number of tuples `(i, j, k, l)` such that:

- `0 <= i, j, k, l < n`
- `nums1[i] + nums2[j] + nums3[k] + nums4[l] == 0`

 

**Example 1:**

```
Input: nums1 = [1,2], nums2 = [-2,-1], nums3 = [-1,2], nums4 = [0,2]
Output: 2
Explanation:
The two tuples are:
1. (0, 0, 0, 1) -> nums1[0] + nums2[0] + nums3[0] + nums4[1] = 1 + (-2) + (-1) + 2 = 0
2. (1, 1, 0, 0) -> nums1[1] + nums2[1] + nums3[0] + nums4[0] = 2 + (-1) + (-1) + 0 = 0
```

**Example 2:**

```
Input: nums1 = [0], nums2 = [0], nums3 = [0], nums4 = [0]
Output: 1
```

 

**Constraints:**

- `n == nums1.length`
- `n == nums2.length`
- `n == nums3.length`
- `n == nums4.length`
- `1 <= n <= 200`
- `-228 <= nums1[i], nums2[i], nums3[i], nums4[i] <= 228`

### Solution:

nums1 = [1,2]

nums2 = [-2,-1]

1. temp = 1-2 = -1

   map{ [-1,1]  }

2. temp = 1-1=0

   Map{ [-1,1], [0, 1]}

   ``

接下来后面两个数字只和 temp

判断0-temp有几个加给count

```java a
class Solution {
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        
        Map<Integer, Integer> map = new HashMap<>();
        int count = 0;
        
        for(int i: nums1){
            for(int j : nums2){
                
                int temp = i+j;
                if(!map.containsKey(temp)){
                    
                    map.put(temp,1);
                }else{
                    map.put(temp,map.get(temp)+1);
                }
                
            }
        }
        
        for(int i : nums3){
            for(int j: nums4){
                
                int temp = i+j;
                if(map.containsKey(0-temp)){
                    count += map.get(0-temp);
                }
            }
        }
        
        return count;
    }
}
```

## 383. Ransom Note

Given two strings `ransomNote` and `magazine`, return `true` *if* `ransomNote` *can be constructed from* `magazine` *and* `false` *otherwise*.

Each letter in `magazine` can only be used once in `ransomNote`.

 

**Example 1:**

```
Input: ransomNote = "a", magazine = "b"
Output: false
```

**Example 2:**

```
Input: ransomNote = "aa", magazine = "ab"
Output: false
```

**Example 3:**

```
Input: ransomNote = "aa", magazine = "aab"
Output: true
```

 

**Constraints:**

- `1 <= ransomNote.length, magazine.length <= 105`
- `ransomNote` and `magazine` consist of lowercase English letters.

### Solution

```java
class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        
        int[] record = new int[26];
        
        for(int i=0; i<ransomNote.length(); i++){
            record[ransomNote.charAt(i)-'a']++;
        }
        
        for(int i=0; i<magazine.length(); i++){
            record[magazine.charAt(i)-'a']--;
        }
        
        for(int i=0; i<record.length; i++){
            if(record[i]>0){
                return false;
            }
        }
        return true;
      
```

## 15. 3Sum

Given an integer array nums, return all the triplets `[nums[i], nums[j], nums[k]]` such that `i != j`, `i != k`, and `j != k`, and `nums[i] + nums[j] + nums[k] == 0`.

Notice that the solution set must not contain duplicate triplets.

 

**Example 1:**

```
Input: nums = [-1,0,1,2,-1,-4]
Output: [[-1,-1,2],[-1,0,1]]
```

**Example 2:**

```
Input: nums = []
Output: []
```

**Example 3:**

```
Input: nums = [0]
Output: []
```

 

**Constraints:**

- `0 <= nums.length <= 3000`
- `-105 <= nums[i] <= 105`

### Solution

```java
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        
        List<List<Integer>> res = new ArrayList<>();
        
        if(nums.length == 0 || nums == null){
            return res;
        }
        
        Arrays.sort(nums);
        
        for(int i=0; i<nums.length; i++){
            
            if(nums[i]>0){
                return res;
            }
            
            if(i>0 && nums[i]==nums[i-1]){
                continue;
            }
            
            int left = i+1;
            int right = nums.length-1;
            
            while(left<right){
                
                int sum = nums[left] + nums[right] + nums[i];
                if(sum>0){
                    right --;
                }else if(sum<0){
                    left ++;
                }else{
                    res.add(Arrays.asList(nums[i],nums[left],nums[right]));
                     
                
                while (right > left && nums[right] == nums[right - 1]) right--;
                while (right > left && nums[left] == nums[left + 1]) left++;
                
                
                right --;
                left ++;
                }
            }
        }
        return res;
    }
}
```

## 18 4Sum

Given an array `nums` of `n` integers, return *an array of all the **unique** quadruplets* `[nums[a], nums[b], nums[c], nums[d]]` such that:

- `0 <= a, b, c, d < n`
- `a`, `b`, `c`, and `d` are **distinct**.
- `nums[a] + nums[b] + nums[c] + nums[d] == target`

You may return the answer in **any order**.

 

**Example 1:**

```
Input: nums = [1,0,-1,0,-2,2], target = 0
Output: [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
```

**Example 2:**

```
Input: nums = [2,2,2,2,2], target = 8
Output: [[2,2,2,2]]
```

 

**Constraints:**

- `1 <= nums.length <= 200`
- `-109 <= nums[i] <= 109`
- `-109 <= target <= 109`

### Solution

```java
class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        
        if(nums.length==0 || nums.length == 0){
            return res;
        }
        
        Arrays.sort(nums);
        
        for(int i=0; i<nums.length; i++){
            
            if(i>0 && nums[i]==nums[i-1]){
                continue;
            }
            
            for(int j=i+1; j<nums.length; j++){
                
                if(j>i+1 && nums[j]==nums[j-1]){
                    continue;
                }
                
                int left = j+1;
                int right = nums.length - 1;
                
                while(left < right){
                    
                    int sum = nums[i]+nums[j]+nums[left]+nums[right];
                    
                    if(sum>target){
                        right--;
                    }else if(sum<target){
                        left ++;
                    }else{
                        res.add(Arrays.asList(nums[i],nums[j],nums[left],nums[right]));
                        
                        while(left<right && nums[right]==nums[right-1]) right--;
                        while(left<right && nums[left]==nums[left+1]) left++;
                        
                        right --;
                        left ++;
                        
                    }
                    
                }
                
            }
            
        }
        
        return res;
        
    }
}
```

# 4. String

### 344. Reverse String

Write a function that reverses a string. The input string is given as an array of characters `s`.

You must do this by modifying the input array [in-place](https://en.wikipedia.org/wiki/In-place_algorithm) with `O(1)` extra memory.

 

**Example 1:**

```
Input: s = ["h","e","l","l","o"]
Output: ["o","l","l","e","h"]
```

**Example 2:**

```
Input: s = ["H","a","n","n","a","h"]
Output: ["h","a","n","n","a","H"]
```

 

**Constraints:**

- `1 <= s.length <= 105`
- `s[i]` is a [printable ascii character](https://en.wikipedia.org/wiki/ASCII#Printable_characters).

#### Solution

双指针过于简单不再赘述

```java
class Solution {
    public void reverseString(char[] s) {
        
        int left = 0;
        int right = s.length-1;
        
        while(left<right){
            
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            
            left++;
            right--;
            
        }
        
    }
}
```



### 20. Valid Parentheses

Given a string `s` containing just the characters `'('`, `')'`, `'{'`, `'}'`, `'['` and `']'`, determine if the input string is valid.

An input string is valid if:

1. Open brackets must be closed by the same type of brackets.
2. Open brackets must be closed in the correct order.

 

**Example 1:**

```
Input: s = "()"
Output: true
```

**Example 2:**

```
Input: s = "()[]{}"
Output: true
```

**Example 3:**

```
Input: s = "(]"
Output: false
```

**Constraints:**

- `1 <= s.length <= 104`
- `s` consists of parentheses only `'()[]{}'`.

#### Solution

```java
class Solution {
    public boolean isValid(String s) {
        
        Stack<Character> stack = new Stack<>();
        
        /*if(s==null){
            return false;
        }
        
        if(s.length()==0){
            return false;
        }*/
        
        for(int i = 0; i<s.length(); i++){
            
            /*if(i==0){
                stack.push(s.charAt(i)); 
                continue;
            }*/
            
            
            if(!stack.isEmpty()&&stack.peek()=='('&&s.charAt(i)==')'){
                stack.pop();
                continue;
            }else if(!stack.isEmpty()&&stack.peek()=='{'&&s.charAt(i)=='}'){
                stack.pop();
                continue;
            }else if(!stack.isEmpty()&&stack.peek()=='['&&s.charAt(i)==']'){
                stack.pop();
                continue;    
            }else{
               stack.push(s.charAt(i)); 
            }

            
        }
        
        if(stack.isEmpty()){
            return true;
        }else{
            return false;
        }
        
        
    }
}
```

# 5 Double pointers

## 42 Trapping Rain Water

Given `n` non-negative integers representing an elevation map where the width of each bar is `1`, compute how much water it can trap after raining.

 

**Example 1:**

![img](https://assets.leetcode.com/uploads/2018/10/22/rainwatertrap.png)

```
Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6
Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped.
```

**Example 2:**

```
Input: height = [4,2,0,3,2,5]
Output: 9
```

 

**Constraints:**

- `n == height.length`
- `1 <= n <= 2 * 104`
- `0 <= height[i] <= 105`

#### **Solution**

双指针法，确定每个位子的左右两边最大值，并且比较他的最小值

```java
class Solution {
    public int trap(int[] height) {
        
        int count = 0;
        for(int i = 0; i<height.length; i++){
            
            if(i>0 && i<height.length-1){
                
                //int left = i-1;
                //int right = i+1;
                int leftMax = 0;
                int rightMax = 0;
                
                for(int left = i-1; left>=0; left--){
                    if(height[left]>leftMax){
                        leftMax = height[left];
                    }
                }
                
                for(int right = i+1; right<height.length; right++){
                    if(height[right]>rightMax){
                        rightMax = height[right];
                    }
                }
                
                int temp = Math.min(leftMax,rightMax)-height[i];
                if(temp<0){
                    temp = 0;
                }
                count += temp;
                
                
            }
            
        }
        
        return count;
    }
}
```



# 6. Stack and Queue

## 232. Implement Queue using Stacks

Implement a first in first out (FIFO) queue using only two stacks. The implemented queue should support all the functions of a normal queue (`push`, `peek`, `pop`, and `empty`).

Implement the `MyQueue` class:

- `void push(int x)` Pushes element x to the back of the queue.
- `int pop()` Removes the element from the front of the queue and returns it.
- `int peek()` Returns the element at the front of the queue.
- `boolean empty()` Returns `true` if the queue is empty, `false` otherwise.

**Notes:**

- You must use **only** standard operations of a stack, which means only `push to top`, `peek/pop from top`, `size`, and `is empty` operations are valid.
- Depending on your language, the stack may not be supported natively. You may simulate a stack using a list or deque (double-ended queue) as long as you use only a stack's standard operations.

 

**Example 1:**

```
Input
["MyQueue", "push", "push", "peek", "pop", "empty"]
[[], [1], [2], [], [], []]
Output
[null, null, null, 1, 1, false]

Explanation
MyQueue myQueue = new MyQueue();
myQueue.push(1); // queue is: [1]
myQueue.push(2); // queue is: [1, 2] (leftmost is front of the queue)
myQueue.peek(); // return 1
myQueue.pop(); // return 1, queue is [2]
myQueue.empty(); // return false
```

### Solution:

```java
class MyQueue {

    
    Stack<Integer> in;
    Stack<Integer> out;
    
    public MyQueue() {
        
        in = new Stack<>();
        out = new Stack<>();
    }
    
    public void push(int x) {
        in.push(x);
    }
    
    public int pop() {
        
        copyIn();
        return out.pop();
        
    }
    
    public int peek() {
        copyIn();
        return out.peek();
        
    }
    
    public boolean empty() {
        if(in.empty()&& out.empty()){
            return true;
        }else{
            return false;
        }
    }
    
    public void copyIn(){
        
        if(!out.empty()){
            return;
        }
        
        while(!in.empty()){
            out.push(in.pop());
        }
        
    }
    
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */
```



### 1047 Remove All Adjacent Duplicates In String

You are given a string `s` consisting of lowercase English letters. A **duplicate removal** consists of choosing two **adjacent** and **equal** letters and removing them.

We repeatedly make **duplicate removals** on `s` until we no longer can.

Return *the final string after all such duplicate removals have been made*. It can be proven that the answer is **unique**.

 

**Example 1:**

```
Input: s = "abbaca"
Output: "ca"
Explanation: 
For example, in "abbaca" we could remove "bb" since the letters are adjacent and equal, and this is the only possible move.  The result of this move is that the string is "aaca", of which only "aa" is possible, so the final string is "ca".
```

**Example 2:**

```
Input: s = "azxxzy"
Output: "ay"
```

 

**Constraints:**

- `1 <= s.length <= 105`
- `s` consists of lowercase English letters.

#### Solution

```java
class Solution {
    public String removeDuplicates(String s) {
        
        Stack<Character> stack = new Stack<>();
        
        for(int i=0; i<s.length(); i++){
            
            char ch = s.charAt(i);
            
            if(!stack.isEmpty()&&stack.peek()==ch){
                stack.pop();
            }else{
                stack.push(ch);
            }
        }
        
        String res = "";
        while(!stack.isEmpty()){
            res = stack.pop() + res;
        }
        
        return res;
        
    }
}
```

更简洁的一种写法

```java
class Solution {
    public String removeDuplicates(String s) {
        
        Stack<Character> stack = new Stack<>();
        
        for(int i =0; i<s.length(); i++){
            
            char ch = s.charAt(i);
            
            if(stack.isEmpty()||stack.peek()!=ch){
                stack.push(ch);
            }else{
                stack.pop();
            }
        }
        
        String res = "";
        while(!stack.isEmpty()){
            res = stack.pop()+res;
        }
        return res;
    }
}
```

### 150 Evaluate Reverse Polish Notation

Evaluate the value of an arithmetic expression in [Reverse Polish Notation](http://en.wikipedia.org/wiki/Reverse_Polish_notation).

Valid operators are `+`, `-`, `*`, and `/`. Each operand may be an integer or another expression.

**Note** that division between two integers should truncate toward zero.

It is guaranteed that the given RPN expression is always valid. That means the expression would always evaluate to a result, and there will not be any division by zero operation.

 

**Example 1:**

```
Input: tokens = ["2","1","+","3","*"]
Output: 9
Explanation: ((2 + 1) * 3) = 9
```

**Example 2:**

```
Input: tokens = ["4","13","5","/","+"]
Output: 6
Explanation: (4 + (13 / 5)) = 6
```

**Example 3:**

```
Input: tokens = ["10","6","9","3","+","-11","*","/","*","17","+","5","+"]
Output: 22
Explanation: ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
= ((10 * (6 / (12 * -11))) + 17) + 5
= ((10 * (6 / -132)) + 17) + 5
= ((10 * 0) + 17) + 5
= (0 + 17) + 5
= 17 + 5
= 22
```

 

**Constraints:**

- `1 <= tokens.length <= 104`
- `tokens[i]` is either an operator: `"+"`, `"-"`, `"*"`, or `"/"`, or an integer in the range `[-200, 200]`.

#### **Solution**

```java
class Solution {
    public int evalRPN(String[] tokens) {
        Deque<Integer> stack = new LinkedList<>();
        
        for(int i=0; i<tokens.length; i++){
            
            
            if(tokens[i].equals("+") || tokens[i].equals("-")||tokens[i].equals("*")||tokens[i].equals("/")){
                
                int a = stack.peek();
                stack.pop();
                int b = stack.peek();
                stack.pop();

                
                if(tokens[i].equals("+")){
                    int c = b+a;
                    stack.push(c);
                }else if(tokens[i].equals("-")){
                    int c = b-a;
                    stack.push(c);
                }else if(tokens[i].equals("*")){
                    int c = b*a;
                    stack.push(c);
                }else if(tokens[i].equals("/")){
                    int c = b/a;
                    stack.push(c);
                }
                
            }else{
                String t = tokens[i];
                int temp = Integer.valueOf(t);
                stack.push(temp);
            }
            
        }
        
        return stack.peek();
    }
}
```

### 239 Sliding Window Maximus

暴力法

```java
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        
        int left = 0;
        int right = left+k-1;
        
        int[]res = new int[nums.length-k+1];
        
        while(right<nums.length){
            
            int max = nums[left];
            for(int i =left; i<=right; i++){
                
                if(nums[i]>=max){
                    max = nums[i];
                }
            }
            res[left] = max;
            
            
            left++;
            right++;
        }

        return res;
    }
}
```

# 7. Binary Tree

## [144. 二叉树的前序遍历](https://leetcode-cn.com/problems/binary-tree-preorder-traversal/)

难度简单775收藏分享切换为英文接收动态反馈

给你二叉树的根节点 `root` ，返回它节点值的 **前序** 遍历。

 

**示例 1：**

![img](https://assets.leetcode.com/uploads/2020/09/15/inorder_1.jpg)

```
输入：root = [1,null,2,3]
输出：[1,2,3]
```

**示例 2：**

```
输入：root = []
输出：[]
```

**示例 3：**

```
输入：root = [1]
输出：[1]
```

**示例 4：**

![img](https://assets.leetcode.com/uploads/2020/09/15/inorder_5.jpg)

```
输入：root = [1,2]
输出：[1,2]
```

**示例 5：**

![img](https://assets.leetcode.com/uploads/2020/09/15/inorder_4.jpg)

```
输入：root = [1,null,2]
输出：[1,2]
```

 

**提示：**

- 树中节点数目在范围 `[0, 100]` 内
- `-100 <= Node.val <= 100`

### Solution

前序遍历从root到left， left视为root，直到走完，再走right

![图1 前序遍历](https://bkimg.cdn.bcebos.com/pic/3c6d55fbb2fb4316e5bfe05020a4462309f7d37c?x-bce-process=image/resize,m_lfit,w_440,limit_1/format,f_auto)

首先访问A

之后访问B

再访问D

之后E

之后C

之后F

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {

        ArrayList<Integer> res = new ArrayList<>();
        preOrder(root, res);

        return res;



    }
            void preOrder(TreeNode root, ArrayList<Integer> res){

            if(root == null){
                return;
            }
            res.add(root.val);
            preOrder(root.left, res);
            preOrder(root.right, res);

        }
}
```

## [145. 二叉树的后序遍历](https://leetcode-cn.com/problems/binary-tree-postorder-traversal/)

难度简单794收藏分享切换为英文接收动态反馈

给你一棵二叉树的根节点 `root` ，返回其节点值的 **后序遍历** 。

 

**示例 1：**

![img](https://assets.leetcode.com/uploads/2020/08/28/pre1.jpg)

```
输入：root = [1,null,2,3]
输出：[3,2,1]
```

**示例 2：**

```
输入：root = []
输出：[]
```

**示例 3：**

```
输入：root = [1]
输出：[1]
```

 

**提示：**

- 树中节点的数目在范围 `[0, 100]` 内
- `-100 <= Node.val <= 100`

### Solution

后序遍历

![图1](https://bkimg.cdn.bcebos.com/pic/4034970a304e251f1510e448a586c9177e3e539e?x-bce-process=image/resize,m_lfit,w_440,limit_1/format,f_auto)

DEBFCA

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        
        ArrayList<Integer> res = new ArrayList<>();
        postOrder(root, res);

        return res;

    }

    void postOrder(TreeNode root, ArrayList<Integer> res){

        if(root==null){
            return;
        }

        postOrder(root.left, res);
        postOrder(root.right, res);
        res.add(root.val);
    }
}
```

## [94. 二叉树的中序遍历](https://leetcode-cn.com/problems/binary-tree-inorder-traversal/)

难度简单1343收藏分享切换为英文接收动态反馈

给定一个二叉树的根节点 `root` ，返回 *它的 **中序** 遍历* 。

 

**示例 1：**

![img](https://assets.leetcode.com/uploads/2020/09/15/inorder_1.jpg)

```
输入：root = [1,null,2,3]
输出：[1,3,2]
```

**示例 2：**

```
输入：root = []
输出：[]
```

**示例 3：**

```
输入：root = [1]
输出：[1]
```

 

**提示：**

- 树中节点数目在范围 `[0, 100]` 内
- `-100 <= Node.val <= 100`

### Solution

![图1](https://bkimg.cdn.bcebos.com/pic/4034970a304e251f1510e448a586c9177e3e539e?x-bce-process=image/resize,m_lfit,w_440,limit_1/format,f_auto)

DBEAFC

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<>();
        inOrder(root, res);
        return res;
    }

    void inOrder(TreeNode root, ArrayList<Integer> res){

        if(root == null){
            return;
        }

        inOrder(root.left, res);
        res.add(root.val);
        inOrder(root.right, res);


    }
}
```

# 层序遍历合集

Given the `root` of a binary tree, return *the level order traversal of its nodes' values*. (i.e., from left to right, level by level).

 

**Example 1:**

![img](https://assets.leetcode.com/uploads/2021/02/19/tree1.jpg)

```
Input: root = [3,9,20,null,null,15,7]
Output: [[3],[9,20],[15,7]]
```

**Example 2:**

```
Input: root = [1]
Output: [[1]]
```

**Example 3:**

```
Input: root = []
Output: []
```



**Constraints:**

- The number of nodes in the tree is in the range `[0, 2000]`.
- `-1000 <= Node.val <= 1000`

## 102. Binary Tree Level Order Traversal

### Solution

BFS，通过que代替stack，每层遍历后加入数组

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
                
        Queue<TreeNode> que = new LinkedList<>(); 
        
        if(root == null){
            
            return res;
        }
        
        que.add(root);
        
        while(!que.isEmpty()){
            
            ArrayList<Integer>level = new ArrayList<>();
                
            for(int len = que.size(); len>0; len--){
                
                TreeNode node = que.peek();
                que.poll();
                level.add(node.val);
                
                if(node.left != null){
                    que.add(node.left);
                }
                
                if(node.right != null){  
                   que.add(node.right); 
                }
                
                
            }   
            
            res.add(level);
            
        }
        
        return res;
    }
}
```

## 107.Binary Tree Level Order Traversal II

### Solution:

和102完全一致，多一步反转结果list

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        
        List<List<Integer>> res = new ArrayList<>();
        
        Queue<TreeNode> que = new LinkedList<>();
        
        if(root == null){
            return res;
        }
        
        que.add(root);
        
        while(!que.isEmpty()){
            
            ArrayList<Integer> level = new ArrayList<>();
            
            for(int len = que.size(); len>0; len--){
                
                TreeNode node = que.peek();
                que.poll();
                level.add(node.val);
                
                if(node.left != null){
                    que.add(node.left);
                }
                
                if(node.right != null){
                    que.add(node.right);
                }
                
                
            }
            
            res.add(level);      
            
        }
        
       Collections.reverse(res);
        
        return res;
        
    }
}
```

## 637. Average of Levels in Binary Tree

### Solution:

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public List<Double> averageOfLevels(TreeNode root) {
        
        ArrayList<Double> res = new ArrayList<>();
        
        Queue<TreeNode> que = new LinkedList<>();
        
        if(root == null){
            return res;
        }
        
        que.add(root);
        
        while(!que.isEmpty()){
            
            double sum = 0;
            int length = que.size();
            
            for(int len = que.size(); len>0; len--){
                TreeNode node = que.peek();
                que.poll();
                sum += node.val;
                
                if(node.left != null){
                    que.add(node.left);
                }
                if(node.right != null){
                    que.add(node.right);
                }
            }
            
            res.add(sum/=length);
            
        }
        
        return res;
    }
}
```

## 429.N-ary Tree Level Order Traversal

### Solution:

```java
/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/

class Solution {
    public List<List<Integer>> levelOrder(Node root) {
    
        List<List<Integer>> res = new ArrayList<>();
        
        Queue<Node> que = new LinkedList<>();
        
        if(root == null){
            return res;
        }
        
        que.add(root);
        
        
        while(!que.isEmpty()){
            
            ArrayList<Integer> level = new ArrayList<>();
            
            for(int len= que.size(); len>0; len--){
                Node node = que.peek();
                que.poll();
                
                level.add(node.val);
                
                int count = 0;
                while(node.children != null && count != node.children.size()){
                    //node.children.get(1);
                    que.add(node.children.get(count));
                    count ++;
                }

            }
            
            res.add(level);
            
            
        }
        
        return res;
    }
}
```

## 515. Find Largest Value in Each Tree Row

### Solution:

记住最小值是-Integer.MAX_VALUE-1

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public List<Integer> largestValues(TreeNode root) {
        
        ArrayList<Integer> res = new ArrayList<>();
        
        Queue<TreeNode> que = new LinkedList<>();
        
        if(root == null){
            return res;
        }
        
        que.add(root);
        
        while(!que.isEmpty()){
            
            int min = -Integer.MAX_VALUE-1;
            
            for(int len=que.size(); len>0; len--){
                
                TreeNode node = que.peek();
                que.poll();
                
                if(node.val>min){
                    min = node.val;
                }
                if(node.left != null){
                    que.add(node.left);
                }
                
                if(node.right != null){
                    que.add(node.right);
                }
                               
            }
            
            res.add(min);
            
        }
        
        return res;
    }
}
```

## 116. Populating Next Right Pointers in Each Node

### Solution

```java
/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}
    
    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
*/

class Solution {
    public Node connect(Node root) {
        
        Queue<Node> que = new LinkedList<>();
        
        if(root != null){
            que.add(root);
        }
        
        while(!que.isEmpty()){
            
            
            for(int len=que.size(); len>0; len--){
                Node tmpNode = que.peek();
                que.poll();
                
                if(tmpNode.left != null){
                    que.add(tmpNode.left);
                }
                
                if(tmpNode.right != null){
                    que.add(tmpNode.right);
                }
                
                if(len==1){
                    tmpNode.next = null;
                }else{
                    tmpNode.next = que.peek();
                }
            }
            
            
        }
        
        
        return root;
    }
}
```

## 117. Populating Next Right Pointers in Each Node II

### Solution

```java
/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}
    
    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
*/

class Solution {
    public Node connect(Node root) {
        
        Queue<Node> que = new LinkedList<>();
        if(root != null){
            que.add(root);
        }else{
            return root;
        }
        
        while(!que.isEmpty()){
            
            for(int len = que.size(); len>0; len--){
                
                Node node = que.poll();
                
                if(node.left != null){
                    que.add(node.left);
                }
                
                if(node.right != null){
                    que.add(node.right);
                }
                
                if(len==1){
                    node.next = null;
                }else{
                    node.next = que.peek();
                }
                
            }
            
        }
        
        return root;
        
    }
}
```

## 111. Minimum Depth of Binary Tree

### Solution

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public int minDepth(TreeNode root) {
        
        Queue<TreeNode> que = new LinkedList<>();
        
        
        int res = 1;
        
        if(root==null){
            return 0;
        }
        
        if(root!=null){
            que.add(root);
        }
        
        while(!que.isEmpty()){
            
            for(int len=que.size(); len>0; len--){
                
                TreeNode node = que.poll();
                
                if(node.left == null && node.right == null){
                    return res;
                }
                
                if(node.left != null){
                    que.add(node.left);
                }
                
                if(node.right != null){
                    que.add(node.right);
                }
                
            }
            
            res +=1;
        }
        
        
        return res;
        
    }
}
```

## 559. Maximum Depth of N-ary Tree

### Solution

```java
/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/

class Solution {
    public int maxDepth(Node root) {
        
        int res = 0;
        
        if(root==null){
            return res;
        }  
        
        Queue<Node> que = new LinkedList<>();
        
        que.add(root);
        
        while(!que.isEmpty()){
            
            for(int len = que.size(); len>0; len--){
                
                Node node = que.poll();
                
                
                for(Node n : node.children){
                    
                    if(n!=null){
                        que.add(n);
                    }
                }

            }
            
            res++;
            
        }
        
        return res;
    }
}
```



# 后序遍历判断树是否相同

## 101. Symmetric Tree

**Solution:**

后续遍历，BFS

判断左右节点是否相等

leftNode.val == rightNode.val

que里加leftNode.left, rightNode.right

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public boolean isSymmetric(TreeNode root) {
        
        Queue<TreeNode> que = new LinkedList<>();
        que.add(root.left);
        que.add(root.right);
        
        while(!que.isEmpty()){
            
            TreeNode leftNode = que.poll();
            TreeNode rightNode = que.poll();
            
            if(leftNode == null && rightNode == null){
                continue;
            }
            
            if(leftNode == null && rightNode != null){
                return false;
            }
            
            if(leftNode != null && rightNode == null){
                return false;
            }
            
            if(leftNode.val != rightNode.val){
                return false;
            }
            
            que.add(leftNode.left);
            que.add(rightNode.right);
            que.add(leftNode.right);
            que.add(rightNode.left);
            
        }
        
        return true;
        
    }
}
```

## 100. Same Tree

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        
        Queue<TreeNode> que = new LinkedList<>();
        que.add(p);
        que.add(q);
        
        while(!que.isEmpty()){
            
            TreeNode leftNode = que.poll();
            TreeNode rightNode = que.poll();
            
            if(leftNode == null && rightNode == null){
                continue;
            }
            
            if(leftNode == null && rightNode != null){
                return false;
            }            
            
            if(leftNode != null && rightNode == null){
                return false;
            }
            
            if(leftNode.val != rightNode.val){
                return false;
            }
            

            
            que.add(leftNode.left);
            que.add(rightNode.left);
            que.add(leftNode.right);
            que.add(rightNode.right);
            
        }
        
        return true;
    }
}
```

## 572. Subtree of Another Tree

先找到origin和subroot相同的值

之后和symmertri tree判断一样

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
    
        Queue<TreeNode> que = new LinkedList<>();
        que.add(root);
        
        while(!que.isEmpty()){
            
            TreeNode origin = que.poll();
            
            if(origin!= null && origin.val == subRoot.val ){
                
                if(isSame(origin, subRoot)==true){
                    return true;
                }
                
            }
            
            if(origin.left != null){
                que.add(origin.left);
            }
            if(origin.right!=null){
                que.add(origin.right);
            }
            
        }
        
        return false;
        
    }
    
    public boolean isSame(TreeNode origin, TreeNode subRoot){
        Queue<TreeNode> q = new LinkedList<>();
        q.add(origin);
        q.add(subRoot);
                
        while(!q.isEmpty()){

            TreeNode leftNode = q.poll();
            TreeNode rightNode = q.poll();

            if(leftNode == null && rightNode == null){
                continue;
            }

            if(leftNode == null && rightNode != null){
                return false;
            }            

            if(leftNode != null && rightNode == null){
                return false;
            }

            if(leftNode.val != rightNode.val){
                return false;
            }

            q.add(leftNode.left);
            q.add(rightNode.left);
            q.add(leftNode.right);
            q.add(rightNode.right);

        }
        return true;
    }
}
```

# 

# -------

## 257 Binary Tree Paths

带一丢丢回溯思想？

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public List<String> binaryTreePaths(TreeNode root) {
    
        List<String> res = new ArrayList<>();
        
        Stack<TreeNode> stack = new Stack<>();
        Stack<String> path = new Stack<>();
        
        if(root == null){
            return res;
        }
        
        stack.push(root);
        path.push(root.val+"");
        
        while(!stack.isEmpty()){
            
            TreeNode node = stack.pop();
            String s = path.pop();
            
            if(node.left==null&&node.right==null){
                res.add(s);
            }
            
            if(node.right!=null){
                stack.push(node.right);
                path.push(s+"->"+node.right.val);
            }
            
            if(node.left!=null){
                stack.push(node.left);
                path.push(s+"->"+node.left.val);
            }            
            
            
        }
        
        return res;
    }
}
```

## 404. Sum of Left Leaves

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public int sumOfLeftLeaves(TreeNode root) {
        int sum = 0;
        if(root == null){
            return sum;
        }
        
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        
        while(!stack.isEmpty()){
            
            TreeNode node = stack.pop();
            
            if(node.left!=null&&node.left.left==null&&node.left.right==null){
                sum+=node.left.val;
            }
            
            if(node.right!=null){
                stack.push(node.right);
            }
            if(node.left!=null){
                stack.push(node.left);
            }
        }
        
        return sum;
    }
}
```

## 513. Find Bottom Left Tree Value

取值 从right 到left入que

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public int findBottomLeftValue(TreeNode root) {
        int res = 0;
        
        if(root == null){
            return res;
        }
        
        res = root.val;
        Queue<TreeNode> que = new LinkedList<>();
        que.offer(root);
        while(!que.isEmpty()){
            
            for(int len =que.size(); len>0; len--){
                
                TreeNode node = que.poll();
                res = node.val;
                
                
                if(node.right!=null){
                    que.offer(node.right);
                }
                if(node.left!=null){
                    que.offer(node.left);
                    res = node.left.val;
                }
                
            }
            
            
        }
        return res;
    }
}
```

## 112. Path Sum

（深度遍历）前序遍历

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public boolean hasPathSum(TreeNode root, int targetSum) {
            
        
        if(root == null){
            return false;    
        }
        
        Stack<TreeNode> stack = new Stack<>();
        Stack<Integer> path = new Stack<>();
        
        stack.push(root);
        path.push(root.val);

        
        while(!stack.isEmpty()){
            
            TreeNode node = stack.pop();
            int sum = path.pop();

            
            if(node.left==null&node.right==null&&sum==targetSum){
                return true;
            }
            
            if(node.right!=null){
                stack.push(node.right);
                path.push(sum+node.right.val);
            }
            
            if(node.left!=null){
                stack.push(node.left);
                path.push(sum+node.left.val);
            }
            
        }
        
        return false;
    }
}
```

## *113

# 构建二叉树，后序转前序

## 106. Construct Binary Tree from Inorder and Postorder Traversal

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
/*
* 第一步：如果数组大小为零的话，说明是空节点了。

* 第二步：如果不为空，那么取后序数组最后一个元素作为节点元素。

* 第三步：找到后序数组最后一个元素在中序数组的位置，作为切割点

* 第四步：切割中序数组，切成中序左数组和中序右数组 （顺序别搞反了，一定是先切中序数组）

* 第五步：切割后序数组，切成后序左数组和后序右数组

* 第六步：递归处理左区间和右区间

*/
class Solution {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
    
        return build(inorder, 0, inorder.length, postorder, 0, postorder.length);
        
    }
    public TreeNode build(int[] inorder, int inLeft, int inRight, int[]postorder, int postLeft, int postRight){
        
        if(inRight - inLeft <1){
            return null;
        }
        
        if(inRight - inLeft == 1){
            return new TreeNode(inorder[inLeft]);
        }
        
        int rootVal = postorder[postRight-1];
        TreeNode root = new TreeNode(rootVal);
        int index = 0;
        for(int i =inLeft; i<inRight; i++){
            
            if(inorder[i]==root.val){
                index = i;
                break;
            }
        }
        
        root.left = build(inorder, inLeft, index, postorder, postLeft, postLeft+(index-inLeft) );
        root.right = build(inorder, index+1, inRight, postorder, postLeft+(index-inLeft), postRight-1 );
        return root;
    }
}
```

## *105 Construct Binary Tree from Preorder and Inorder Traversal

**二叉树前序遍历的顺序为：**

先遍历根节点；

随后递归地遍历左子树；

最后递归地遍历右子树。

**二叉树中序遍历的顺序为：**

先递归地遍历左子树；

随后遍历根节点；

最后递归地遍历右子树。

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return helper(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    public TreeNode helper(int[] preorder, int preLeft, int preRight,
                           int[] inorder, int inLeft, int inRight) {
        // 递归终止条件
        if (inLeft > inRight || preLeft > preRight) return null;

        // val 为前序遍历第一个的值，也即是根节点的值
        // idx 为根据根节点的值来找中序遍历的下标
        int idx = inLeft, val = preorder[preLeft];
        TreeNode root = new TreeNode(val);
        for (int i = inLeft; i <= inRight; i++) {
            if (inorder[i] == val) {
                idx = i;
                break;
            }
        }

        // 根据 idx 来递归找左右子树
        root.left = helper(preorder, preLeft + 1, preLeft + (idx - inLeft),
                         inorder, inLeft, idx - 1);
        root.right = helper(preorder, preLeft + (idx - inLeft) + 1, preRight,
                         inorder, idx + 1, inRight);
        return root;
    }
}
```

## 654. Maximum Binary Tree

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        
        return build(nums, 0, nums.length);
    }
    
    public TreeNode build(int[]nums, int left, int right){
        
        if(right-left<1){
            return null;
        }
        if(right-left==1){
            return new TreeNode(nums[left]);
        }
        
        
        int index = 0;
        int maxVal = 0;
        for(int i = left; i<right; i++){
            if(nums[i]>maxVal){
                maxVal = nums[i];
                index = i;
            }    
        }
        
        TreeNode root = new TreeNode(maxVal);
        
        root.left = build(nums, left, index);
        root.right = build(nums,index+1, right);
        
        return root;
        
        
    }
}
```

# 单调栈

## 739. Daily Temperatures

```java
class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        int len = temperatures.length;
        int[] res = new int[len];
        
        Stack<Integer> stack = new Stack<>();
        
        for(int i = 0; i<len; i++){
            
            
            while(!stack.isEmpty()&&temperatures[i]>temperatures[stack.peek()]){
                
                int index = stack.pop();
                res[index] = i-index;
                
            }
            
            stack.push(i);
            
        }
        
        return res;
        
    }
}
```

# 递归

## 77. Combinations

for循环遍历层数

递归遍历深度

```java
class Solution {
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> path = new ArrayList<>(); 
    
    public List<List<Integer>> combine(int n, int k) {
        
        cal(n, k, 1);
        return res;
        
    }
    
    public void cal(int n, int k, int startIndex){
        
        if(path.size()==k){
            res.add(new ArrayList<>(path));
            return ;
        }
        
        for(int i=startIndex; i<=n-(k-path.size())+1; i++){
            path.add(i);
            cal(n, k, i+1);
            path.remove(path.size()-1);
        }
    }
}class Solution {
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> path = new ArrayList<>(); 
    
    public List<List<Integer>> combine(int n, int k) {
        
        cal(n, k, 1);
        return res;
        
    }
    
    public void cal(int n, int k, int startIndex){
        
        if(path.size()==k){
            res.add(new ArrayList<>(path));
            return ;
        }
        
        for(int i=startIndex; i<=n-(k-path.size())+1; i++){
            path.add(i);
            cal(n, k, i+1);
            path.remove(path.size()-1);
        }
    }
}
```

## 17. Letter Combinations of a Phone Number

```java
class Solution {
    
    String[] letterMap = {"","","abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv","wxyz"};
    List<String> res = new ArrayList<>();
    StringBuilder sb = new StringBuilder();
    
    public List<String> letterCombinations(String digits) {
        
        if(digits==null||digits.length()==0){
            return res;
        }
        
        int len = digits.length();
        cal(digits, len, 0);
        return res;
    }
    
    public void cal(String digits, int len, int startIndex){
        
        if(sb.length() == len){
            res.add(sb.toString());
            return;
        }
        
        String s = letterMap[digits.charAt(startIndex)-'0'];
        for(int i=0; i<s.length(); i++){
            
            sb.append(s.charAt(i));
            cal(digits, len,startIndex+1);
            sb.deleteCharAt(sb.length()-1);
            
        }
        
    }
}
```

## 39 Combination Sum

```java
class Solution {
    
    //HashSet<Integer> res = new HashSet<Integer>;
    List<List<Integer>> res = new ArrayList<>();
    LinkedList<Integer> path = new LinkedList<>();
    //int sum = 0;
    
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        
        Arrays.sort(candidates);
        cal(candidates, target, 0, 0);
        return res;
    }
    
    public void cal(int[] candidates, int target, int sum, int startIndex){
        
        
        if(sum>target){
            return;
        }
        
        if(sum==target){
            res.add(new ArrayList<>(path));
            return;
        }

        for(int i =startIndex; i<candidates.length; i++){
            
            path.add(candidates[i]);
            sum+=candidates[i];
            cal(candidates, target, sum, i);
            path.removeLast();
            sum-=candidates[i];
        }
    }
}
```

## 40 Combination Sum Ⅱ

```java
class Solution {
    List<List<Integer>> res = new ArrayList<>();
    LinkedList<Integer> path = new LinkedList<>();
    
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        cal(candidates, target, 0, 0);   
        return res;
    }
    
    public void cal(int[] arr, int target, int startIndex, int sum){
        
        if(sum>target){
            return;
        }
        
        if(sum==target){
            res.add(new ArrayList<>(path));
        }
        
        for(int i = startIndex; i<arr.length; i++){
            
            if(i>startIndex&&arr[i]==arr[i-1]){
                continue;
            }
            
            path.add(arr[i]);
            sum+=arr[i];
            cal(arr,target, i+1, sum);
            sum-=arr[i];
            path.removeLast();
            
        }
        
    }
}
```



# 切割问题

## 131 Palindrome Partitioning

切字符串，切完回溯

```java
class Solution {
    
    List<List<String>> res = new ArrayList<>();
    LinkedList<String> path = new LinkedList<>();
    
    public List<List<String>> partition(String s) {
        
        
        cal(s, 0);
        return res;
        
    }
    
    private void cal(String s, int startIndex){
        
        if(startIndex >= s.length()){
            res.add(new ArrayList<>(path));
            return;
        }
        
        for(int i = startIndex; i<s.length(); i++){
            
            if(isPal(startIndex,i,s)){
                String str = s.substring(startIndex,i+1);
                path.add(str);
            }else{
                continue;
            }
            
            cal(s,i+1);
            path.removeLast();
            
        }
    }
    
    private boolean isPal(int start, int end, String s){
        
        int left = start;
        int right = end;
        
        while(left<right){
            
            if(s.charAt(left)==s.charAt(right)){
                left ++;
                right --;
            }else{
                return false;
            }
            
            
        }
        
        return true;
    }
}
```

