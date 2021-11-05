<div style="font-family:'Fira Code','PingFang SC'">


<div style="font-family: 'Fira Code', 'PingFang SC'">


# OMS01

> 点餐管理系统 01

## 题目背景

点餐系统需要有基本用户，每位用户在系统内有个人信息，包括用户名、性别、手机号等。其中手机号需要满足一定的格式，因此需要对手机号进行校验。

根据我国手机号码的规定，长度为 11 位，由 0-9 阿拉伯数字组成，各段有不同的含义和编码规则：

第 1-3 位：网络识别号
第 4-7 位：地区编码
第 8-11 位：用户号码

**手机号码的格式：**

[![6u1PFs.png](OMS05.assets/6u1PFs.png)](https://imgtu.com/i/6u1PFs)

为了方便餐厅点餐系统的编写，对实际规定进行简化，即**合法的手机号码严格按照下面的规定给出**，如果不符合下面的规定，即认为该手机号码不合法。

**规则如下:**

1. 前三位“网络识别号”的范围为: $[130, 187]$
2. 中间四位“地区编码”的范围为: $[0000, 9999]$
3. 最后四位“用户号码”的组成较为特殊，用户的手机号码的最后一位可表示手机号码持有人的性别，规定 0 为男性、1 为女性，因此最后四位的范围为: $[031, 071] + 0/1$

**合法的手机号示例：**

- 13020210310
- 18501100451

**不合法的手机号码示例：**

- 10000000310
- 18900000311
- 17701210000
- 15600010452

## 题目描述

编写一个 Person 类：

- 属性至少包括用户名（仅可能由 26 个字母大小写和数字构成，不含空格）、性别（字符 M/F，分别表示男性/女性）、手机号（储存合法的格式，详见题目背景）

- 以上属性应为私有属性。需提供相应的 `getter` 和 `setter` 方法（通常 IDE 有支持自动生成的功能和快捷键，可自行探索）

- 实现静态方法 `static boolean checkSex(char sex)`，判断传入的性别是否为合法，合法为 true

- 实现静态方法 `static boolean checkNum(String phoneNum)`，判断传入的手机号是否合法，合法为 true

- 实现方法 `String toString()` 产生格式化的信息，具体要求如下：

  - 冒号为英文字符 `:`
  - 保证所有信息合法
  - 不包含多余的空格
  - 所有字符为全角字符

  以 ZhangSan，手机号 13766660310 为例：

  ```shell
  Name:ZhangSan
  Sex:M
  Phone:13766660310
  ```

- 实现静态方法 `addPerson(String name, char sex, String phoneNum)`，要求：

  - 输入参数为表示用户名的字符串 name，表示性别的字符 sex，和表示手机号的字符串 phoneNum
  - 检查参数是否合法。若非法，返回 `null`
  - 若合法，返回一个 Person 对象

在**上次的 Test 类基础上**（请先完成点餐系统 0），增加以下功能：

- 在命令行终端中输入以下命令，执行相应的操作：

基本格式为：

```shell
选项 [参数1] [参数2] [参数3]
```

| 选项 | [参数 1] | [参数 2] | [参数 3] | 功能描述                                                     |
| ---- | -------- | -------- | -------- | ------------------------------------------------------------ |
| np   | 用户名   | 性别     | 手机号   | 调用上文所述的 addPerson()方法。对于非法输入，终端输出相应的信息。对于合法输入，调用该对象的 `toString()` 方法。 |

具体要求如下：

- 在输入 np 命令时，并不保证参数数量严格对应，这也是一种非法情况，需要在终端输出：

  ```shell
  Arguments illegal
  ```

- 如果参数合法，按以下顺序依次进行信息检查

  - 用户名由 26 个字母和数字构成，保证测试点中一定合法
  - 性别必须为 F 或 M，其他情况请输出

  ```shell
  Sex illegal
  ```

  - 手机号需满足指定格式，非法（包括手机号尾号和sex不符的情况）请输出

  ```shell
  Phone number illegal
  ```

- 如果存在多种非法情况，按上述顺序只输出最先发生的非法信息。

- 如果输入合法，命令行按 `toString()` 输出格式打印。如：

  ```shell
  np ZhangSan M
  Arguments illegal
  np ZhangSan M 1376666123
  Phone number illegal
  np ZhangSan NB 1376666123
  Sex illegal
  np ZhangSan M 13766660310
  Name:ZhangSan
  Sex:M
  Phone:13766660310
  ```

# OMS02

> 点餐管理系统 02

## 题目背景

干饭人上线！既然是点餐系统，当然要有菜单啦~（大家在做实验之前记得先仔细阅读实验文档，在开始动手，以免返工）

## 提示

由于点餐系统逐渐复杂，输出也变得更多了起来，因此在大家完成本次任务时，请认真阅读任务部分以及功能部分，防止遗漏输出情况。

## 任务

注意：以下编写的方法是否为静态，可根据自己实现的方式进行选择，我们不作限制，但请选择最契合面向对象编程的逻辑方式进行。

### Dish

编写一个 Dish 类，作为该餐厅的菜品：

- 属性包括菜品名称（String 类型）、菜品编号 DID（String 类型，**菜品主键**，其组成后面会详细说明）、价格（Double / double 类型，**保留一位小数**）、总量（Integer / int 类型，因为食材是有限的）

- 编写方法 `String toString()`，输出 Dish 的格式化信息，要求格式如下：

  - 注意冒号和逗号使用英文字符 ':' 和 ','

  - 注意：输出时标点符号间不要有空格，例如：

    ```shell
    DID:H000011,DISH:FISH,PRICE:38.5,TOTAL:100
    ```

  - 保证所有信息合法，比如：该菜品的总量不能为负数等

- 添加任何你认为所需要的属性和方法

- 以上所有属性均为**私有**属性，需提供相应的 getter 和 setter 方法

### Menu

编写一个 Menu 类，作为该餐厅的菜单，包括所有的菜品（封装对菜品的操作），并且菜单分为热菜 H、凉菜 C 和其他 O 三个部分（菜品编号组成为：菜品类型 + 6 位整数编号，并且类型字母为大写）：

```shell
例如：H000000
```

- 提供默认构造方法，构造一个空的菜单

- 编写查询菜品相关信息的方法

  - #### Dish getDishById(String did)

    - 根据菜品的编号 DID ，查询对应的菜品（需要自行判断 DID 的合法性）
    - 如果查找到指定的菜品，返回目标 Dish 对象，并输出该菜品的格式化信息
    - 如果不存在该菜品，则返回 `null`，输出对应信息

  - #### Vector\<Dish\> getDishByKeyWord(String keyword)

    - 根据关键字检索，返回所有菜品名字中包含有该关键字的菜品（注意：检索时不区分大小写）
    - 返回类型为 Dish 的容器（数组、List、Map 等），同时输出格式化信息
    - 如果不存在这样的菜品，则返回一个空容器，并输出对应信息
    - 合法的关键字是一个连续的没有空白符的字符串

- 添加新的菜品 `nd`

  - 如果这种菜品不存在于列表中（用菜品编号 DID 进行检验），则将其所有的信息添加进菜单
  - 如果这种菜品已经存在（DID 或名称存在），则输出对应信息
  - 请保证录入的数据是合法的，例如：
    - 修改后菜单中的菜品总数不能为负数
    - 菜品的价格不能为负数
    - 信息不合法时，要保证数据不被修改，并输出对应信息

- 修改指定的菜品信息 `udd`

  - 修改的菜品应当是已经存在于菜单中的
  - 请保证修改的信息是合法的
  - 如果输入的修改信息不合法，请输出对应信息
  - 如果被修改的是能唯一标识某一种菜品的属性（比如菜品的编号、名称）,那么需要保证修改这个属性之后不与已经存在的其他菜品重复，如果信息重复则判定为不合法，输出对应信息
  - 关于修改的信息的合法状态的详细情况请**查看表格**

- 概览整本菜单的信息 `pm`

  - 打印目前菜单里所有菜品的信息
  - 打印的顺序有两个优先级，首先是 H>C>O，其次是 6 位编码从小到大
  - 打印序号从 1 开始，注意形式为`"1." + [空格] + 具体信息`
  - 如果当前菜单内容为空，则输出`Empty Menu`

- 以上功能都视作菜单 Menu 对外提供的获取/修改数据的 API，是后续实验的基础

### 功能新增

在**上次的 Test 类基础上**（请先完成点餐系统 1），增加以下功能：

- 在 main 方法中创建⼀个空的 Menu 对象

- 在 main 方法中实现⼀个简易命令行终端对上述 Menu 对象进行操作，要求：

  输入的基本格式如下

  ```shell
  选项 [参数1] [参数2] [参数3] [参数4]
  ```

  注意：所有输出中存在的标点符号都是英文符号，且各种情况的优先判断次序按照说明进行

  | 选项 | [参数 1] | [参数 2] | [参数 3]     | [参数 4] | 功能描述                                                     |
  | ---- | -------- | -------- | ------------ | -------- | ------------------------------------------------------------ |
  |      |          |          |              |          | <div style="width: 300px">若输入指令不存在（选项名或者选项中的选择性参数不存在均认为指令不存在，例如 _gd -not_ 不存在），输出`Command not exist`；若参数数量不正确，输出`Params' count illegal`；对于所有未提及的其他输入格式错误以及不合法的情况，输出`Input illegal`。</div> |
  | gd   | -id      | 菜品编号 |              |          | <div style="width: 300px">在参数（数量）合法的前提下，根据菜品编号查找菜品，如果 Did 格式错误，输出`Did input illegal`；如果菜品不存在，则输出`Dish does not exist`；如果存在，则调用该菜品的 toString 方法进行输出。</div> |
  | gd   | -key     | 关键字   |              |          | <div style="width: 300px">在参数（数量）合法的前提下，根据菜品名字查找菜品（测试保证 key 合法），如果菜品不存在，则输出`Dish does not exist`；如果存在，则根据菜品类型热菜 H、凉菜 C 和其他 O 的顺序输出，并且这三个分类中的编号应按照从小到大的格式输出，编号从 1 开始，具体格式请看文末示例。</div> |
  | udd  | -n       | 菜品编号 | 名字         |          | <div style="width: 300px">在参数（数量）合法的前提下，修改指定菜品编号的名称，如果 Did 格式错误，输出`Did input illegal`；如果没有菜品存在，则输出`Dish does not exist`。注意，名称的**合法情况仅包括`数字 + 英文字母大小写`（位置不作强制要求）**，其他情况均非法，输出`New name input illegal`；如果新名称与原名称/已存在菜名重复，输出`New name repeated`；成功修改后输出`Update dish's name success`。</div> |
  | udd  | -t       | 菜品编号 | 该菜品的总量 |          | <div style="width: 300px">在参数（数量）合法的前提下，修改指定菜品编号的总量，如果 Did 格式错误，输出`Did input illegal`；如果没有菜品存在，则输出`Dish does not exist`；如果输入修改后总量非法（**合法情况仅为非负整数**），输出`Change dish's total illegal`；成功后输出`Update dish's total success`。</div> |
  | udd  | -p       | 菜品编号 | 该菜品的价格 |          | <div style="width: 300px">在参数（数量）合法的前提下，修改指定菜品编号的价格，如果 Did 格式错误，输出`Did input illegal`；如果没有菜品存在，则输出`Dish does not exist`；如果输入修改后价格非法（**合法情况仅为非负浮点数**），输出`Change dish's price illegal`；成功后输出`Update dish's price success`。</div> |
  | nd   | 菜品编号 | 菜品名字 | 菜品价格     | 菜品总量 | <div style="width: 300px">在参数（数量）合法的前提下，添加⼀个菜品到对应类型的 Menu 中，如果 Did 格式错误，输出`Did input illegal`；如果菜品已经存在,则输出`Dish exists`。如果不存在则判断数据是否合法，非法输出`New dish's attributes input illegal`，若均合法则判断菜品名称是否重复，若重复则输出`Name repeated`；成功则添加至列表并输出`Add dish success`。</div> |
  | pm   |          |          |              |          | <div style="width: 300px">打印当前菜单的所有菜品情况，具体的打印顺序参考 Menu 类的编写说明；注意，如果当前 Menu 没有菜品，打印`Empty Menu`。</div> |

说明一： 关于数字属性（例如菜品价格、总量）的格式错误和数据合法性，可以理解为

> 如果该属性错误输入为了其他类型例如字符串，则视为格式错误；如果该属性为数值类型（int 或 double 等）但是数值大小不符合实际情况（比如菜品价格为负数）则认为数据不合法。
>
> 如果没有特殊的说明，在 oms 中格式错误和非法认为是一种情况，按指示输出

说明二：`在参数（数量）合法的前提下` - 这句话是指已经完成了参数数量的合法检查，因此优先级更高的是指令名称存在与否、参数数量是否合法

说明三：

> 单级选项指令（例如`nd`）的检查过程：`第一位选项是否存在` → `参数数量是否正确`
>
> 多级选项指令（例如`gd`）的检查过程：`第一位选项是否存在` → `第二位选项是否存在` → ... → `第n位选项是否存在` → `参数数量是否正确`

### 注意

今后若无具体说明，则 oms 对每一条指令的合法性判断顺序应为：**指令（选项/参数 0）名称在环境中存在与否 > 参数数量是否正确 > 具体说明的操作或输出**；在本次的输出中，参数调用失败以及参数数量的错误输出如上表最后所示。

```
{我们建议的指令编写逻辑框架（非java代码，请勿复制使用，以 nd 为例）}

/**
* 在参数（数量、格式）合法的前提下，添加⼀个菜品到对应类型的 Menu 中
* 1. 如果 Did 格式错误，输出Did input illegal
* 2. 如果菜品已经存在,则输出Dish exists
* 3. 如果不存在则判断数据是否合法，非法输出New dish's attributes input illegal
* 4. 若均合法则判断菜品名称是否重复，若重复则输出Name repeated
* 5. 成功则添加至列表并输出Add dish success
*/

[-] you get a string as an operational statement
|
↓
String[] / other vector<String> ops
|
| // {当然为了防止ops访问越界，我们建议你确保ops.len合适后再进行下面的操作}
↓
// 指令（选项/参数0）名称在环境中存在与否
if (ops[0] === new String("nd")) {
  // 参数数量是否正确
  if (ops.len == 4) {
    // 参数合法性
    if (checkDID(ops[1])) {
      if (!dishExisted(ops[1])) {
        // 这里使用 && 是为了展示这些方法是短路判断的，存在一处不合法就认为不合法
        if (checkDishName(ops[2]) &&
            checkDishPrice(ops[3]->int) &&
            checkDishTotal(ops[4]->int)) {
          call: addDish(...);
          print("Add dish success");
        } else {
          print("New dish's attributes input illegal\n");
        }
      } else {
        print("Dish exists\n");
      }
    } else {
      print("Did input illegal");
    }
  } else {
    print("Params' count illegal\n");
  }
} else {
  print("Command not exist\n");
}

```

输入[-]输出[+]示例：

`[+]/[-]仅仅是提示作用，实际输出并不包括`

```
----------pm----------
[-] pm
[+] Empty Menu
[-] nd H000000 FanQieChaoDan 5.0 30
[+] Add dish success
[-] pm
[+] 1. DID:H000000,DISH:FanQieChaoDan,PRICE:5.0,TOTAL:30

----------nd----------
[-] nd H00000a
[+] Params' count illegal
[-] nd H00000a DishA 15.0 10
[+] Did input illegal
[-] nd H000099 $$ 15.0 10
[+] New dish's attributes input illegal
[-] nd H000099 Dish99 price 10
[+] New dish's attributes input illegal
[-] nd H000099 Dish99 15.0 total
[+] New dish's attributes input illegal
[-] nd H000099 Dish99 15.0 -1
[+] New dish's attributes input illegal
[-] nd H000099 Dish99 15.0 5.2
[+] New dish's attributes input illegal
[-] nd H000000 MaoXueWang 15.0 10
[+] Dish exists
[-] nd O000000 Cola 5.0 200
[+] Add dish success
[-] nd C000000 IceCream 4.0 100
[+] Add dish success
[-] pm
[+] 1. DID:H000000,DISH:FanQieChaoDan,PRICE:5.0,TOTAL:30
    2. DID:C000000,DISH:IceCream,PRICE:4.0,TOTAL:100
    3. DID:O000000,DISH:Cola,PRICE:5.0,TOTAL:200

----------gd----------
[-] gd
[+] Command not exist
[-] gd -where H000000
[+] Command not exist
[-] gd -here H000000 del
[+] Command not exist
[-] gd -id H000001 del
[+] Params' count illegal
[-] gd -id H000001
[+] Dish does not exist
[-] gd -id H000000
[+] DID:H000000,DISH:FanQieChaoDan,PRICE:5.0,TOTAL:30
[-] gd -key c
[+] 1. DID:H000000,DISH:FanQieChaoDan,PRICE:5.0,TOTAL:30
    2. DID:C000000,DISH:IceCream,PRICE:4.0,TOTAL:100
    3. DID:O000000,DISH:Cola,PRICE:5.0,TOTAL:200

----------udd----------
[-] udd -n H000000 @@
[+] New name input illegal
[-] udd -n H000000 FanQieChaoDan
[+] New name repeated
[-] udd -n H000000 Cola
[+] New name repeated
[-] udd -n H000000 XiHongShiChaoJiDan
[+] Update dish's name success
[-] pm
[+] 1. DID:H000000,DISH:XiHongShiChaoJiDan,PRICE:5.0,TOTAL:30
    2. DID:C000000,DISH:IceCream,PRICE:4.0,TOTAL:100
    3. DID:O000000,DISH:Cola,PRICE:5.0,TOTAL:200

[-] dish
[+] Command not exist
[-] quit
[+] Command not exist
[-] QUIT
[+] ----- Good Bye! -----
```

## 提交格式注意

务必按照规定格式提交，特别是 src 文件夹以及其中的 Test.java 文件，这两者是不可或缺的，是测评时拉取的核心资源，如果按照非合法格式提交而导致的**最终测评**得分较低，自行负责。

# OMS03

> 点餐管理系统 03

## 题目背景

由于饭店经营得当，饭店进行了一次很大规模的扩建，招聘了更多的厨师，也能做出更多种类的菜品，饭店老板雄心壮志，甚至打算把菜单出成一本比新华字典还要厚的书。那么此时，点餐管理系统就需要进行功能的更新了，具体的更新如下：

## 任务

### Test

在 oms02 中，大家编写了 Menu 类，由于这次饭店的扩建，一个菜单中的菜品种类的远远多于 oms02，无论是打印菜单还是关键字查找都有可能得到好多结果，打印出来长长的一串，十分不利于顾客的查看以及点餐。

为了解决这个问题，oms 系统对打印的结果进行截断显示的处理，把输出的结果以“页”为单位进行显示，同时加入若干的指令进行当前页的控制（前进/后退/退出/返回首页）。

### 功能新增

在上次点餐系统的基础上（务必完成 oms02），我们为指令终端加入了一些新的指令，方便按页读取菜单信息，基本格式如下：

```shell
选项 [参数1] [参数2] [参数3] [参数4]
```

| 选项 | [参数 1] |   [参数 2]    | [参数 3] |   [参数 4]    |                           功能描述                           |
| :--: | :------: | :-----------: | :------: | :-----------: | :----------------------------------------------------------: |
|      |          |               |          |               | <div style="width: 300px">和 oms02 类似，若输入指令不存在，输出`Command not exist`；若参数数量不正确，输出`Params' count illegal`；**对于所有未提及的其他输入不合法的情况，输出`Input illegal`**。</div> |
|      |          |               |          |               | <div style="width: 300px">补充：和 oms2 相同，每一个指令的判断都是**指令（选项/参数 0）名称在环境中存在与否 > 参数数量是否正确 > 具体说明的操作或输出**，注意具体说明的操作或输出是按照从左往右的顺序进行的</div> |
|  gd  |   -key   |    关键字     | 第 n 页  | 每页 m 个记录 | <div style="width: 300px">详见功能 1 描述（有内嵌指令，请仔细阅读）</div> |
|  pm  | 第 n 页  | 每页 m 个记录 |          |               | <div style="width: 300px">详见功能 2 描述（有内嵌指令，请仔细阅读</div> |

#### 功能 1

新增的功能 1 沿用了 oms02 的`gd`指令，由于参数数量的区别，功能上与其并不冲突，因此你的 oms03 版本也应该支持上一个版本的 gd 测试（也即不要直接修改/删除上一个版本的 gd）。新的功能 1 要实现**在关键字查找的基础上**进行分页的操作：

- 参数 2：测试数据保证关键字不含有空白符号，查找时**模糊大小写（大小写无关）**
- 参数 3：n（int）表示输出的菜品列表属于当前的第 n 页的内容
- 参数 4：m（int）表示当前的分页操作是按照每页 m 个菜品划分的
- 如果 n 的大小小于 1，输出第一页的格式化信息，如果 n 的大小大于最后一页的页码，输出最后一页的信息
  - 请保证 n 为整数、m 为正整数，如果不是则输出`Page slice method's params input illegal`
- 不保证菜单中的菜品总种类数 C 可以被 m 整除，因此最后一页的菜品数 lm ≤ m
- 如果没有查找到关键词匹配的菜品，按照 oms2 的方式输出并退出内嵌指令环境
- 页码的计算从 1 开始

**内嵌指令操作 1**： 当调用了按页关键字查找菜品功能时，进入操作 1 的内嵌环境，接下来的输入均按照如下的指令处理：

| 内嵌指令 |                           功能描述                           |
| :------: | :----------------------------------------------------------: |
|    n     | 输出当前页的下一页的内容，如果当前页已经是最后一页，请输出`This is the last page` |
|    l     | 输出当前页的上一页的内容，如果当前页已经是第一页，请输出`This is the first page` |
|    f     |                转到第一页（首页）并打印其内容                |
|    q     | 退出内嵌指令环境，返回到上一层指令环境，同时打印退出提示`Exit page check mode`，该指令是**唯一**退出内嵌环境的指令，不可调用其他指令退出当前环境 |
|          | 如果当前菜单的内容为空，则直接输出`Menu is empty, exit page check mode`，并退出内嵌环境 |
|          | 如果调用了内嵌环境不存在的指令（包括参数不对应），输出`Call inner method illegal` |

##### 打印格式

请注意，在输出每一页的信息后，为了方便顾客使用，会接着打印一行指令说明文字，如下：

> n-next page,l-last page,f-first page,q-quit

```
[#] 此处已存在Dish实例，见下两页输出
[-] gd -key C -1 -1
[+] Page slice method's params input illegal
[-] gd -key abcdefg 1 -1
[+] Dish does not exist
[-] gd -key C 2 3
[+] Page: 2
    1. DID:H000111,DISH:XiHongShiChaoJiDan,PRICE:5.0,TOTAL:30
    2. DID:C000000,DISH:IceCream,PRICE:4.0,TOTAL:100
    3. DID:O000000,DISH:Cola,PRICE:5.0,TOTAL:200
    n-next page,l-last page,f-first page,q-quit
[-] n
[+] This is the last page
[-] l
[+] Page: 1
    1. DID:H000000,DISH:C1,PRICE:1.0,TOTAL:130
    2. DID:H000001,DISH:C2,PRICE:2.0,TOTAL:100
    3. DID:H000002,DISH:C3,PRICE:3.0,TOTAL:100
    n-next page,l-last page,f-first page,q-quit
[-] l
[+] This is the first page
[-] f
[+] Page: 1
    1. DID:H000000,DISH:C1,PRICE:1.0,TOTAL:130
    2. DID:H000001,DISH:C2,PRICE:2.0,TOTAL:100
    3. DID:H000002,DISH:C3,PRICE:3.0,TOTAL:100
    n-next page,l-last page,f-first page,q-quit
[-] gd -id H000000
[+] Call inner method illegal
[-] q
[+] Exit page check mode
[-] QUIT
[+] ----- Good Bye! -----
```

#### 功能 2

新增的功能 2 沿用了 oms02 的`pm`指令，由于参数数量的区别，功能上与其并不冲突，因此你的 oms03 版本也应该支持上一个版本的 pm 测试（也即不要直接修改/删除上一个版本的 pm）。新的功能 2 要实现**打印菜单的基础上**进行分页的操作：

- 参数 1：n（int）表示输出的菜品列表属于当前的第 n 页的内容
- 参数 2：m（int）表示当前的分页操作是按照每页 m 个菜品划分的
- 如果 n 的大小小于 1，输出第一页的格式化信息，如果 n 的大小大于最后一页的页码，输出最后一页的信息
  - 请保证 n 为整数、m 为正整数，如果不是则输出`Page slice method's params input illegal`
- 不保证菜单中的菜品总种类数 C 可以被 m 整除，因此最后一页的菜品数 lm ≤ m
- 页码的计算从 1 开始

**内嵌指令操作 2**：当调用了按页打印菜品功能时，进入操作 2 的内嵌环境，接下来的输入均按照如下的指令处理：

| 内嵌指令 |                           功能描述                           |
| :------: | :----------------------------------------------------------: |
|    n     | 输出当前页的下一页的内容，如果当前页已经是最后一页，请输出`This is the last page` |
|    l     | 输出当前页的上一页的内容，如果当前页已经是第一页，请输出`This is the first page` |
|    f     |                转到第一页（首页）并打印其内容                |
|    q     | 退出内嵌指令环境，返回到上一层指令环境，同时打印退出提示`Exit page check mode`，该指令是**唯一**退出内嵌环境的指令，不可调用其他指令退出当前环境 |
|          | 如果当前菜单的内容为空，则直接输出`Menu is empty, exit page check mode`，并退出内嵌环境 |
|          | 如果调用了内嵌环境不存在的指令（包括参数不对应），输出`Call inner method illegal` |

##### 打印格式

请注意，在输出每一页的信息后，为了方便顾客使用，会接着打印一行指令说明文字，如下：

> n-next page,l-last page,f-first page,q-quit

```
[#] 此处已存在Dish实例，见下输出
[-] pm
[+] 1. DID:H000000,DISH:H1,PRICE:1.0,TOTAL:10
    2. DID:H000001,DISH:H2,PRICE:1.0,TOTAL:10
    3. DID:H000002,DISH:H3,PRICE:1.0,TOTAL:10
    4. DID:H000003,DISH:D1,PRICE:1.0,TOTAL:10
    5. DID:C000002,DISH:C1,PRICE:1.0,TOTAL:10
[-] pm -1 0
[+] Page slice method's params input illegal
[-] pm 2 3
[+] Page: 2
    1. DID:H000003,DISH:D1,PRICE:1.0,TOTAL:10
    2. DID:C000002,DISH:C1,PRICE:1.0,TOTAL:10
    n-next page,l-last page,f-first page,q-quit
[-] q
[+] Exit page check mode
[-] QUIT
[+] ----- Good Bye! -----
```

### 提示

虽然测试的数据量不会太⼤，但是对于菜单（菜品列表）等建议使⽤动态存储⽅式，以防爆栈

# OMS04

> 点餐管理系统 04

## 写在前面

大家对Java编程已经逐渐熟悉，现在我们开始减少对于实现方式的要求，只对测试接口进行严格要求。所有方法我们只规定需要实现，实现方式等不做要求，但请将代码写规范，因为**有⼀部分分会给在代码风格上**。接口和文件格式一定要写正确，无法测试会直接导致分数较低。

## 题目背景

经过大家辛苦的开发，目前我们的点餐系统已经实现了较为成熟的菜单的查看以及菜品的查找，当然，只有菜品菜单是不够的，饭店的正常运营，一些角色是无法或缺的，此次oms的实现主要侧重于完善这些类的编写。

## 任务

### Person

此次功能新增会对oms01已经编写的`Person`类进行修改，说明如下：

该类是下面几个角色类的父类。为Person类新增主键属性PID（String类型），对应的密码为PWD（String类型），两者的组成结构有严格的要求，具体要求如下：

- 对于PID：

```
PID = 两位身份符号（Cu/Wa/Co） + 5位整数编号
例如：Cu00000、Wa01000
```

- 对于PWD：

```
  1. PWD的长度限制在[8,18]
  2. PWD中至少含有英文字母以及阿拉伯数字（即不允许只出现数字或字母）
  3. 只允许出现ASCII码表中序号在[33,126]中的字符
  4. 在角色被新创建时，为其生成默认密码oms1921SE
  
```

### Customer

`顾客 - 继承Person类`

- 添加属性：isVIP（boolean类型），表示当前顾客是否为VIP用户`（default: false)`
- 添加属性：balance（double类型），表示当前该顾客账户的余额`（**保留一位小数**）`
- 添加属性：isDining（boolean类型），表示当前顾客是否在店用餐
- 添加属性：当前点餐清单（列表）
- 添加属性：当前未上菜清单（列表）

### Waiter

`服务员 - 继承Person类`

- 添加属性：当前所服务的顾客清单（列表，具体存储内容请自行设计，但必须保证含有Person类的主键以方便相关方法的编写）

### Cook

`厨师 - 继承Person类`

- 添加属性：当前完成菜品清单（Dish对象列表）

### PersonList

编写PersonList类存放当前点餐系统的所有人的信息，包括当前系统中所有的Customer、Waiter、Cook。

### DishDoneList

编写DishDoneList类存放当前由厨师完成而未被服务员取走的Dish对象

## 功能新增

此次功能新增依旧是在前面的oms的基础上进行，请务必完成oms03后再编写此次实验。本次实验将会检查项目结构的合理性，要求除了自定义排序等方法类外，一个java文件中的类不应该超过1个，也即每一个java文件所负责的功能应该是单一的。**如果出现一个项目所有的功能全部放在Test类（例如Person等类全部写在Test.java中）中或者类似的类冗余的情况，会进行大额度地扣分**。

### SUDO模式的引入

为了引入权限模式，此次功能更新会引入SUDO指令，来模拟有权限的终端系统（老板角色）。
在初始的情况下，输入指令`SUDO`进入超级用户模式（现实中只有经理等角色才可以使用，这里省略了权限的认证阶段），进入SUDO模式后，首先输出`Enter sudo mode`，接着输入sudo模式下的指令，如下所示：

基本格式：

```shell
选项 [参数1] [参数2] [参数3] [参数4]
```

| 选项 |    [参数 1]    |    [参数 2]    |     [参数 3]     |   [参数 4]    |                       功能描述                       |
| :--: | :------------: | :------------: | :--------------: | :-----------: | :--------------------------------------------------: |
| sncu |  新增顾客姓名  |  新增顾客性别  |  新增顾客手机号  |  新增顾客PID  |                  详见新增功能描述1                   |
| snwa | 新增服务员姓名 | 新增服务员性别 | 新增服务员手机号 | 新增服务员PID |                  详见新增功能描述1                   |
| snco |  新增厨师姓名  |  新增厨师性别  |  新增厨师手机号  |  新增厨师PID  |                  详见新增功能描述1                   |
| dcu  |  目标顾客PID   |                |                  |               |                  详见新增功能描述2                   |
| dwa  | 目标服务员PID  |                |                  |               |                  详见新增功能描述2                   |
| dco  |  目标厨师PID   |                |                  |               |                  详见新增功能描述2                   |
|  pp  |                |                |                  |               |                  详见新增功能描述3                   |
|  SQ  |                |                |                  |               | 退出超级用户状态，输出`Quit sudo mode`，返回初始环境 |

#### 新增功能描述1

本组新增功能的目的是实现超级用户向点餐系统中录入各类角色信息（顾客、服务员、厨师），在录入信息时，需要对各参数的合法性进行检查，同时保证主键的不重复性（**目前可以认为姓名、手机号、PID均不可重复**）。

| 指令名 |         成功输出         |
| :----: | :----------------------: |
|  sncu  | Add new customer success |
|  snwa  |  Add new waiter success  |
|  snco  |   Add new cook success   |

错误情况的输出如下，且相同指令的错误判断优先级由上到下进行

|     指令名     |        错误情况        |            错误输出            |
| :------------: | :--------------------: | :----------------------------: |
|    错误指令    |  SUDO模式不存在该指令  |    Call sudo method illegal    |
| sncu\snwa\snco |    参数（数量）错误    |     Params' count illegal      |
| sncu\snwa\snco | 输入性别错误（非M/F）  |          Sex illegal           |
| sncu\snwa\snco |   输入手机号格式错误   |      Phone number illegal      |
| sncu\snwa\snco | 输入性别与手机号不匹配 | Phone number doesn't match sex |
| sncu\snwa\snco |    输入手机号已存在    |      Phone number exists       |
|      sncu      |  新增顾客PID格式错误   |      Customer PID illegal      |
|      sncu      |   新增顾客PID已存在    |      Customer PID exists       |
|      snwa      | 新增服务员PID格式错误  |       Waiter PID illegal       |
|      snwa      |  新增服务员PID已存在   |       Waiter PID exists        |
|      snco      |  新增厨师PID格式错误   |        Cook PID illegal        |
|      snco      |   新增厨师PID已存在    |        Cook PID exists         |

> 不考虑SUDO情况下的指令参数中名称的错误或重复

*建议大家把重复的判断封装成一个方法，进行调用而不是简单的if-else的复制粘贴。*

#### 新增功能描述2

在一个管理餐厅的系统中，除了能够添加用户，也可以移除用户、开除员工，这就需要我们给系统添加删除操作。所以本组新增功能的目的是实现超级用户对系统角色信息的删除操作，在删除时需要指定删除对象的PID，自然也需要对该PID进行合法性的检查（格式是否正确、是否存在）。

| 指令名 |        成功输出         |
| :----: | :---------------------: |
|  dcu   | Delete customer success |
|  dwa   |  Delete waiter success  |
|  dco   |   Delete cook success   |


|   指令名    |       错误情况       |           错误输出           |
| :---------: | :------------------: | :--------------------------: |
|  错误指令   | SUDO模式不存在该指令 |   Call sudo method illegal   |
| dcu\dwa\dco |   参数（数量）错误   |    Params' count illegal     |
|     dcu     |     指定PID错误      |    D-Customer PID illegal    |
|     dcu     |    指定PID不存在     | D-Customer PID doesn't exist |
|     dwa     |     指定PID错误      |     D-Waiter PID illegal     |
|     dwa     |    指定PID不存在     |  D-Waiter PID doesn't exist  |
|     dco     |     指定PID错误      |      D-Cook PID illegal      |
|     dco     |    指定PID不存在     |   D-Cook PID doesn't exist   |

#### 新增功能描述3

为了方便超级用户查看当前系统的所有用户信息，新增指令pp用来输出系统角色信息列表（格式化）。输出的顺序依旧有两层优先级，首先按照`Customer > Waiter > Cook`排列，其次则是PID的排序（PID从小到大），**序号从1开始**。

**注意，如果当前角色列表为空，则输出`Empty person list`，不退出SUDO模式，继续接收指令。**

输出的格式如下所示：

```
[-] SUDO
[+] Enter sudo mode
[-] pp
[+] Empty person list
[-] sncu ZhangSan M 13766660310 Cu00000
[+] Add new customer success
[-] pp
[+] 1.PID:Cu00000,Name:ZhangSan,Sex:M,Phone:13766660310,PWD:oms1921SE
```

### 角色部分基本方法的实现

#### 登录 login

有了账户以及用户密码，接下来就需要实现系统用户的登录功能。用户登录需要在初始环境下进行（**非SUDO权限环境**），同样也需要通过指令进行：

```
选项 [参数1] [参数2] [参数3]
```

| 指令名 | [参数1] |    [参数2]    | [参数3]  |                             功能                             |
| :----: | :-----: | :-----------: | :------: | :----------------------------------------------------------: |
| login  |   -i    | 用户ID（PID） | 用户密码 | 通过ID登录，如果当前PID格式错误，则输出`PID illegal`；若PersonList不含有该PID，则输出`Pid not exist`；登录成功则输出`Login success`，密码错误输出`Password not match` |
| login  |   -n    |   用户姓名    | 用户密码 | 通过姓名登录，如果输入的姓名格式非法（姓名仅可能由 26 个字母大小写和数字构成，不含空格)，则输出`Pname illegal`；若姓名合法，但当前PersonList不含有该姓名，则输出`Pname not exist`；登录成功则输出`Login success`，密码错误输出`Password not match` |
|        |         |               |          | 和oms02类似，若输入指令不存在，输出`Command not exist`；若参数数量不正确，输出`Params' count illegal`；对于所有未提及的其他输入不合法的情况，输出`Input illegal`。 |

#### 二级指令环境 - login状态

在成功登录后，用户进入用户登录状态，同时可以使用二级指令，具体指令如下：

```
选项 [参数1] [参数2]
```

|  指令名  | [参数1] |  [参数2]   |                             功能                             |
| :------: | :-----: | :--------: | :----------------------------------------------------------: |
|  chgpw   | 新密码  | 确认新密码 | 修改当前用户的密码，首先判断新密码是否合法，非法输出`New password illegal`，合法则与确认新密码进行比较，二者一致则修改密码，输出`Change password success`，否则输出`Not match` |
|  myinfo  |         |            |              输出当前账号的格式化信息，格式示例              |
|   back   |         |            |              退出登录状态，输出`Logout success`              |
|   QUIT   |         |            |          退出系统（同oms03的基本环境中的QUIT指令）           |
| 角色方法 |         |            |  每一个角色在登陆后都有该角色独有的方法，具体在下面进行说明  |
|          |         |            |           调用错误等情况的输出和login一级指令相同            |

当前账户格式化信息如下：

```
[info]
| name:	Tom
| Sex:	M
| Pho:	13766660310
| PID:	Cu00000
| Pwd:	oms1921plus
| Type:	Customer
```

为了方便大家辨识，这里直接给出格式化信息代码，请大家自行适配：

```
System.out.println(
        "[info]\n" +
        "| name:\t" + name + "\n" +
        "| Sex:\t" + sex + "\n" +
        "| Pho:\t" + phone + "\n" +
        "| PID:\t" + PID + "\n" +
        "| Pwd:\t" + Pwd + "\n" +
        "| Type:\t" + Type
);
```

##### login子环境中的角色方法

###### 顾客 Customer

`充值`

> 顾客为了方便在餐厅内用餐，所以会选择在账户内预存一些现金，转为余额并进行消费

由于是在login的子环境中，因此充值时直接将余额加入到该用户的余额中

| 指令名 | [参数1] |                         功能                         |
| :----: | :-----: | :--------------------------------------------------: |
|   rc   |  金额m  |                    增加用户的余额                    |
|   gb   |         | 查看用户的余额，输出`"Balance: " + customer.balance` |

**说明：**

- 金额m为无前缀正浮点数类型，且单笔交易范围为 `100.0 <= m < 1000.0` 如果输入不符合要求，输出`Recharge input illegal`
- 增加余额成功后无输出，继续等待下一个指令的读取

```text
# 已进入用户Cu00000账户的login子环境

[-] rc 50.0
[+] Recharge input illegal
[-] rc 1000.0
[+] Recharge input illegal
[-] rc 101
[-] gb
[+] Balance: 101.0
[-] rc 999.9
[-] gb
[+] Balance: 1100.9
```

`会员`

> 由于餐厅对会员（VIP）有优惠，因此顾客可以申请成为VIP的资格

由于是在login的子环境中，因此申请会员会对当前账户进行修改操作

| 指令名 |      功能       |
| :----: | :-------------: |
| aplVIP | 用户申请成为VIP |

**说明：**

- 申请成为VIP是有要求的（得充钱），需要用户账户余额数` >= 200.0 `（当前状态）
- 在余额满足的前提下，申请成功并输出`Apply VIP success`，同时用户账户属性`isVIP`赋值为`true`
- 在余额不满足的前提下，申请失败并输出`Please recharge more`，同时用户账户属性`isVIP`赋值为`false`

`oms5预告`：

- *每一次结账时，都进行余额的判断，如果余额不足200.0，自动取消VIP身份，需要再次申请才可以成为VIP*
- *成为VIP后，每一餐打八五折哦~*

```text
# 已进入用户Cu00000账户的login子环境，当前余额为0

[-] aplVIP
[+] Please recharge more
[-] rc 800.0
[-] aplVIP
[+} Apply VIP success
```

# OMS05

> 点餐管理系统 05

## 任务前瞻

在 oms04 中，大家编写了点餐系统中十分重要的一些角色类，同时完成了一些指令的实现，此时，我们就可以开始着手实现具体的点餐、服务等环节了。 

当然，由于 oms并不采用多线程的写法，所以也无法还原一个真实的场景（例如用户点餐的同时厨师在做菜），所以本次 oms 需要频繁切换角色的登录并进行相关的操作。且对于现实世界中一些复杂的对应关系做了适当简化。

本次 oms 的编写计划完成所有的功能，因此也是有编码需求的最后一次 oms，望大家认真对待。

## 任务

- 将所有 List 类（`PersonList、DishList等`）改写为单例模式
- 实现用户点餐、厨师做菜、服务员上菜的功能
- 新建所需要的一些单例模式类

### 说明

> 由于本次涉及到多个对象间的关系和数据交互，因此需要大家以面向对象的角度和关系型数据库存放数据使用的关系表的角度来自行设计一些存储类，题中不会给出详细说明，但可能暗含需要新建存储类的信息，建议借助类图、顺序图、状态图等工具进行梳理分析。

### 单例模式改写

本次实验需要大家把前几次实现功能时引入的 List 类（存储对象）选择性地修改为单例模式，如果你觉得这个存储类十分契合单例模式的情形，那就可以按照下面的方式进行修改。

> Java Singleton 模式主要作用是保证在 Java 应用程序中，一个类 Class 只有一个实例存在。 使用 Singleton 好处还在于可以节省内存，因为它限制了实例的个数，有利于 Java 垃圾回收（garbage collection）
>
> [实现单例模式的常见方法](http://www.blogjava.net/kenzhh/archive/2013/03/15/357824.html)

```java
// 以Menu类为例
class Menu {
  private static Menu instance;

  private Menu() {
  }

  // 由于oms不需要考虑线程安全问题，因此可以使用不保证线程安全的懒汉式写法
  public static Menu getInstance() {
	if (instance == null) instance = new Menu();
	return instance;
  }
}
```

### 新增

> 为了完善餐厅的运营流程，使得整个流程更加贴近现实，需要增加一些类

#### Order 类

> 大家在学数据库的时候应该已经接触过两个类之间的关系表，Order 就是这样一个的存在，作为`Customer`和`Menu`的一个关系表，存储着顾客本次的点菜

Order 的基本属性如下：

- orderID `Order的主键，具体格式见下[order指令]`
- CustomerID `表明是哪位顾客所点的菜品`
- WaiterID `表明本次是由哪位服务员进行接单以及送餐服务的（类似海底捞）`
- DishList `顾客所点菜品列表`
- isConfirmed `顾客是否确认菜单(这个属性很重要)`
- isDelivered `服务员是否已送单`

**注意：**

- 当顾客在**初始情况**选择开始点餐时（见下，调用order指令），系统自动生成一张 Order 关系表，存储顾客的点餐情况（oms 餐厅具备数据长期存储功能）
- 我们**几乎所有的命令（login除外）**都是在 `SUDO` 环境下进行的，包括 `pm，gd` 等

#### Customer

> 在 oms4 中，顾客可以通过`login -i`和`login -n`两种方式进入 login 的环境，但是当前的环境中功能需要进一步扩展，在保留 oms4 的相关功能的前提下，增加下列模块：
>
> - 顾客在进行点餐前可以进行菜品和菜单的查询，因此继承了前些版本的查询功能

|   指令名    | [参数 1/子指令名] |   [参数 2]    | [参数 3] |   [参数 4]    | 功能                                                         |
| :---------: | :---------------: | :-----------: | :------: | :-----------: | :----------------------------------------------------------- |
|  错误输出   |                   |               |          |               | 如果输入的指令名不属于该环境，输出`Command not exist`；若参数数量不正确，输出`Params' count illegal`；对于所有未提及的其他输入不合法的情况，输出`Input illegal` |
|     rc      |      金额 m       |               |          |               | 同 oms4                                                      |
|     gb      |                   |               |          |               | 同 oms4                                                      |
|   aplVIP    |                   |               |          |               | 同 oms4                                                      |
|     gd      |        -id        |   菜品编号    |          |               | 同 oms2                                                      |
|     gd      |       -key        |    关键字     |          |               | 同 oms2                                                      |
|     gd      |       -key        |    关键字     | 第 n 页  | 每页 m 个记录 | 同 oms3                                                      |
|     pm      |                   |               |          |               | 同 oms2                                                      |
|     pm      |      第 n 页      | 每页 m 个记录 |          |               | 同 oms3                                                      |
|  **order**  |                   |               |          |               | 进入点餐环境，详情见下                                       |
|   **co**    |                   |               |          |               | 查看已点菜品(check Order)，详情见下                          |
| **confirm** |                   |               |          |               | 提交菜单，详情见下                                           |

`order点餐环境`

> 和 login、sudo 等环境的组成类似，该环境是隶属于 login 的子环境，用户在该环境下进行点餐，并使用该环境下的指令

**在该环境中，循环读取指令，只接受 add 和 finish**

|  指令名  | [参数 1/子指令名] |         [参数 2]          |         [参数 3]          | [参数 4] | 功能                                                         |
| :------: | :---------------: | :-----------------------: | :-----------------------: | :------: | :----------------------------------------------------------- |
| 错误输出 |                   |                           |                           |          | 如果输入的指令名不属于该环境，输出`Command not exist`；若参数数量不正确，输出`Params' count illegal`；对于所有未提及的其他输入不合法的情况，输出`Input illegal`。 |
|   add    |        -i         | Dish 主键 DID（保证合法） | m（正整数且保证数据合法） |          | 点餐，将选择的菜品（m 道）加入到对应的 Order 实例中，如果不存在这道菜，输出`Dish selected not exist`；如果菜品售空，输出`Dish selected is sold out`；如果点餐数量超过菜品数量，输出`Dish is out of stock`，本次点餐无效 |
|   add    |        -n         |      Dish 名称 name       | m（正整数且保证数据合法） |          | 点餐，将选择的菜品（m 道）加入到对应的 Order 实例中，如果不存在这道菜，输出`Dish selected not exist`；如果菜品售空，输出`Dish selected is sold out`；如果点餐数量超过菜品数量，输出`Dish is out of stock`，本次点餐无效 |
|  finish  |                   |                           |                           |          | 结束选菜，**检查此时 Order 的菜品数量**，如果菜品数量为 0，输出`Please select at least one dish to your order`，继续接收指令；否则退出到上一层环境中 |

##### 注意

> 在用户调用add的时候，会优先检查一下当前是否存在自己未confirm的Order，如果存在则可以继续追加菜品，否则系统需要自动生成新的order实例，分配对应的orderID
>
> - 在点菜的时候，不考虑余额是否足够的问题
> - 在点菜的同时，add成功一次，就需要在相应的位置把当前该Dish的总量减m（不存在取消订单的api）

##### orderID 的格式

`orderID: CustomerID + '_' + (orderCount+1)`

> 这里的`orderCount`是指`Order-Customer关系表`中该用户已经完成（confirm）的 order 数量

示例：

```text
· Tom的CustomerID为: Cu00001
· 已经完成了3次order
       ↓
  那么他这次调用order指令生成的orderID为: Cu00001_4
```

##### 情景提示

- Samshui 走进你的餐厅，点了一餐，confirm
- Tony 走进你的餐厅，点了一餐，**没有 confirm**，co(checkOrder) 了一下，觉得不够吃，接着追加了一份披萨，confirm
- Ami 前天来过了你的餐厅吃了一顿，觉得不错，今天又来点了一餐，confirm

##### 点餐示例

```text
[Menu]
1. DID:H000000,DISH:FanQieChaoDan,PRICE:5.0,TOTAL:2
2. DID:H000001,DISH:ChaoTuDouSi,PRICE:16.0,TOTAL:80
3. DID:H000213,DISH:MaoXueWang,PRICE:15.0,TOTAL:10
4. DID:C000000,DISH:IceCream,PRICE:4.0,TOTAL:100
5. DID:C000001,DISH:PiDan,PRICE:15.0,TOTAL:150
6. DID:C000002,DISH:BoBoJi,PRICE:55.0,TOTAL:70
7. DID:O000000,DISH:Cola,PRICE:5.0,TOTAL:200
8. DID:O000100,DISH:Cake,PRICE:74.0,TOTAL:50

# 当前顾客已登录
[-] order
[-] finish
[+] Please select at least one dish to your order
[-] add -i H000000 1 => 成功点菜后，菜品的余量就发生了变化，这也是数据一致性的体现
[-] add -i H000009 2
[+] Dish selected not exist
[-] add -n FanQieChaoDan 2
[+] Dish is out of stock
[-] add -n FQCD 1
[+] Dish selected not exist
[-] add -n FanQieChaoDan 1
[-] add -i H000000 1
[+] Dish selected is sold out
[-] finish
```

`co指令：checkOrder`

> 顾客查看当前已经点的菜品列表，并输出当前菜品的总额（这里不考虑打折，保留 1 位小数）

| 指令名 |                             情况                             |
| :----: | :----------------------------------------------------------: |
|   co   | 输出该顾客的当前订单。输出格式见下文示例。若当前顾客没有下单，输出`No order` |

输出还是和原先针对菜品的输出顺序相同（打印的顺序有两个优先级，首先是 H>C>O，其次是 6 位编码从小到大）

```text
[Menu]
1. DID:H000000,DISH:FanQieChaoDan,PRICE:5.0,TOTAL:30
2. DID:H000001,DISH:ChaoTuDouSi,PRICE:16.0,TOTAL:80
3. DID:H000213,DISH:MaoXueWang,PRICE:15.0,TOTAL:10
4. DID:C000000,DISH:IceCream,PRICE:4.0,TOTAL:100
5. DID:C000001,DISH:PiDan,PRICE:15.0,TOTAL:150
6. DID:C000002,DISH:BoBoJi,PRICE:55.0,TOTAL:70
7. DID:O000000,DISH:Cola,PRICE:5.0,TOTAL:200
8. DID:O000100,DISH:Cake,PRICE:74.0,TOTAL:50

# 点餐Order输出示例

[-] co
[+]
1.DID:H000000,DISH:FanQieChaoDan,PRICE:5.0,NUM:1
2.DID:H000001,DISH:ChaoTuDouSi,PRICE:16.0,NUM:1
3.DID:C000000,DISH:IceCream,PRICE:4.0,NUM:2
4.DID:C000002,DISH:BoBoJi,PRICE:55.0,NUM:1
5.DID:O000100,DISH:Cake,PRICE:74.0,NUM:1
|
SUM:158.0
```

`confirm`

| 指令名  | 情况                                                         |
| :-----: | :----------------------------------------------------------- |
| confirm | 如果当前菜单有效（该Customer有未confirm的菜单），输出`Order Confirmed`；如果不存在有效菜单，输出`No order can be confirmed` |

**方式**：当顾客confirm时，系统为当前订单（Order）自动分配一名服务员。分配算法为：系统检索所有的 Waiter 对象，按照该对象当前负责的 Order 数量（由小到大）以及 WID（由小到大）进行排序并选择第一个 Waiter 来服务当前订单，并将其WID填入订单属性中。

```text
[status : 排序后]
----[WID]----   ----[Order_Count]----
   Wa00009                5
   Wa00011                5
   Wa00007                6
   Wa00004                8

=> 选择Wa00009填入order.WaiterID
```

#### 一种可能的设计方案

```text
# 由于仅初始情况下调用order指令时系统才会生成Order的实例，因此建议：

order指令 -> return Order实例
                   ↓
                   confirm指令: 参数(Order 实例） 
```

#### Waiter

> 在 oms4 中，服务员可以通过`login -i`和`login -n`两种方式进入 login 的环境，但是当前的环境中服务员功能需要进一步扩展，在保留 oms4 的服务员登录后的相关功能前提下，增加下列模块：

|  指令名  | [参数 1/子指令名] |       [参数 2]       | [参数 3] | [参数 4] | 功能                                                         |
| :------: | :---------------: | :------------------: | :------: | :------: | :----------------------------------------------------------- |
| 错误输出 |                   |                      |          |          | 如果输入的指令名不属于该环境，输出`Command not exist`；若参数数量不正确，输出`Params' count illegal`；对于所有未提及的其他输入不合法的情况，输出`Input illegal` |
|    gl    |                   |                      |          |          | 获取当前所服务的 Order 清单(get order list)，单个订单所有菜品的输出还是和原先针对菜品的输出顺序相同（打印的顺序有两个优先级，首先是 H>C>O，其次是 6 位编码从小到大）。详情见下 |
|    mo    |                   |                      |          |          | 分配订单(manage order)，详情见下                             |
|    sr    |   OID（订单ID）   |                      |          |          | 送餐并且收钱(serve and receive money)，完成后该服务员负责的订单数量减一，详情见下 |
|    rw    |   CID（顾客ID）   | money （充入的金额） |          |          | 充值(recharge by waiter)。具体逻辑参见 oms4。**注意**当这次充值达到 VIP 标准后，总金额应该按照八折重新计算 |

`gl指令：getOrderList`

> 查看服务员当前未送到厨房的各个订单（order），以列表形式输出

|  指令名  | 说明                                                         |
| :------: | :----------------------------------------------------------- |
| 错误输出 | 如果输入的指令名不属于该环境，输出`Command not exist`；若参数数量不正确，输出`Params' count illegal`；对于所有未提及的其他输入不合法的情况，输出`Input illegal`。若当前服务员所服务的清单为空，则输出 `No serving order`；若清单不为空，按照**顾客confirm的顺序**进行输出（类似队列），具体输出格式见下 |

```
# 点餐Order输出示例

[-] co
[+]
1.DID:H000000,DISH:FanQieChaoDan,PRICE:5.0,NUM:1
2.DID:H000001,DISH:ChaoTuDouSi,PRICE:16.0,NUM:2
|
SUM:37.0

# 顾客登出，对应的服务员登录

# 服务员getOrderList输出示例
[-] gl
[+]
1. OID:Cu00000_1,DISH:[1 FanQieChaoDan,2 ChaoTuDouSi]

# 输出的DISH列表中的元素组成：点餐的份数 + 空格 + 菜名，以逗号分隔
```

`mo指令：manageOrder`

> 由于可能有多名顾客分配到当前服务员，因此一个waiter对象负责的order可能不止一个。考虑到服务员可能根据厨房忙碌程度进行流量控制，不会一次将所有订单送到厨房，而是每次一个order，按顾客的confirm的时间顺序送到厨房，即一次命令只能送一个 。Order。

| 指令名 | 说明                                                         |
| :----: | :----------------------------------------------------------- |
|   mo   | 若当前服务员所服务的顾客清单不为空，则将 Order 列表中的第一个 Order送到厨房，输出`Manage order success` |

| 错误输出 | 说明                                                         |
| :------: | :----------------------------------------------------------- |
|          | 如果输入的指令名不属于该环境，输出`Command not exist`；若参数数量不正确，输出`Params' count illegal`；对于所有未提及的其他输入不合法的情况，输出`Input illegal` |
|    mo    | 若当前服务员所服务的顾客清单为空，则输出`No serving order`   |

```
# 顾客查看当前订单(check order)
[-] co
[+]
1.DID:H000000,DISH:FanQieChaoDan,PRICE:5.0,NUM:1
2.DID:H000001,DISH:ChaoTuDouSi,PRICE:16.0,NUM:1
|
SUM:21.0

# 顾客登出，对应的服务员登录

# mo(manageOrder)输出示例
[-] mo
[+] Manage order success

# 服务员查看Order订单列表(get order list)
[-] gl
[+] No serving order
```



`sr指令：serve and receive money`

> 厨房完成菜品制作后，由服务员送餐，并结账（顾客一拿到菜就要付钱）

若 Order 的状态为未送餐，则送餐成功，否则失败；每一次执行该命令，则向顾客打印订单明细，并结账。至此该订单终结，可从各个表中删除。

| 指令名 |        [参数 1]         | 说明                                                         |
| :----: | :---------------------: | :----------------------------------------------------------- |
|   sr   | OID 订单 ID（保证合法） | 若 Order 的状态为未送餐状态且余额充足，则为正常情况。具体输出格式见下 |

| 错误输出 | 说明                                                         |
| :------: | :----------------------------------------------------------- |
|          | 如果输入的指令名不属于该环境，输出`Command not exist`；若参数数量不正确，输出`Params' count illegal`；对于所有未提及的其他输入不合法的情况，输出`Input illegal`。 |
|          | 若waiter并不负责该订单，输出`Order serve illegal`            |
|          | 若当前订单未被cook，输出`Order not cooked`                   |
|          | 请确保本订单的状态是已完成了cook但未进行结账，若订单的状态为已结账，输出`Order already checkout` |
|          | 计算总价格（普通用户全价，VIP 顾客**打八折**）。若余额不足，则输出`Insufficient balance` |

```
# 点餐Order输出示例
[-] co
[+]
1.DID:H000000,DISH:FanQieChaoDan,PRICE:5.0,NUM:1
2.DID:H000001,DISH:ChaoTuDouSi,PRICE:16.0,NUM:2
|
SUM:37.0

# 顾客登出，服务员登录，serve and receive money输出示例
[-] sr Cu00000_1
[+] OID:Cu00000_1,DISH:[FanQieChaoDan 5.0,ChaoTuDouSi 32.0],TOTAL:37.0,BALANCE:50.0

# 其中输出DISH中元素组成为：菜名 + 该菜名对应的总价格（菜的单价 * 菜的份数）
# BALANCE表示结账后的余额
```

`rw指令：recharge by waiter`

> 若用户结账时发现自己余额不足，可通过服务员帮助自己充钱

| 指令名 |        [参数 1]         |      [参数 2]      |                             说明                             |
| :----: | :---------------------: | :----------------: | :----------------------------------------------------------: |
|   rw   | CID 顾客 ID（保证合法） | money 具体充入的钱 | 具体逻辑与输出参见 oms4，注意：当这次充钱达到 VIP 标准后，总金额应该按照八折重新计算 |

```
# 点餐Order输出示例
[-] checkOrder
[+]
1.DID:H000000,DISH:FanQieChaoDan,PRICE:5.0,NUM:1
2.DID:H000001,DISH:ChaoTuDouSi,PRICE:16.0,NUM:2
|
SUM:37.0

# 顾客登出，服务员登录，receiveMoney输出示例
[-] sr Cu00000_1
[+] Insufficient balance

# 余额不足(假设只有20.0元)，服务员帮助充钱
[-] rw Cu00000 100.0
[-] sr Cu00000_1
[+] OID:Cu00000_1,DISH:[FanQieChaoDan 5.0,ChaoTuDouSi 32.0],TOTAL:37.0,BALANCE:83.0
```

#### Kitchen类

~~在实际的餐厅中，Order中每道菜与具体的厨师存在对应关系。也就是Cook对象与Order对象是多对一关系，且上菜顺序、烹饪时间存在差异，可能涉及到优先级调度等复杂问题。~~为了简化问题，降低实现难度， 我们将前面oms中构造的Cook类保留，但仅用于存储每位厨师的账户，不再与做菜直接联系。我们将全体厨师团队视为一个整体，不妨称之为Kitchen类，用于菜品制作。同样地，Order中的菜品不进行拆分，而是将所有菜一起送入厨房，烹饪完成后一起送给顾客。

简而言之，Kitchen的作用是将服务员送来的Order进行处理，**按照order送来的顺序，依次逐个进行处理**。处理后的order由服务员送给顾客，进行其他操作。

同样地，kitchen类通过Cook的账号以`login -i`或`login -n`两种方式进入 login 的环境进行登录。登录后添加以下指令：

> cook指令
>
> 从送来的一串order中拿取一个最早的进行制作。制作完成后，按以下格式输出。若当前厨房没有任务，则什么都不做。

| 指令名 |           成功输出            |
| :----: | :---------------------------: |
|  cook  | Finish order:[某个order的OID] |

```
# 厨房cook指令输出示例

# 假设当前waiter只向厨房送了一个order，为：OID:Cu00000_1,DISH:[1 FanQieChaoDan,2 ChaoTuDouSi]
# 厨房登录
[-] cook
[+] Finish order:Cu00000_1
[-] cook
# 此时厨房已经没有任务，无输出
```
