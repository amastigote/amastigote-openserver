# amastigote openserver reference

This server is only for amastigote browser open version.

It implements some basic functions of amastigote and is easy for deploying and private data handling.

It is a RESTful server which means all the response are encapsulated in `Response` then converted to JSONs.

## Response

### model

```java
int stat;   // server inner status code (must)
String msg; // description (optional)
Object obj; // data bundle (optional)
```

The following Status enumerations are converted to `stat`s.
```java
public enum Status {
        COMPLETE,  // 0x00 logic complete as expected
        ERROR,     // 0x01 logic uncomplete or result not as expected
        EXCEPTION  // 0x02 server internal error
}
```

## Tag

One tag can be referred by multiple items.

### model

```java
long id; // DBMS auto-generated
String name;
```

### end point

- **`[GET] /tag?serial=`**

  通过上传本地serial至服务器以验证浏览器端tag列表是否过期
  
    - stat-0x01: tags up-to-date
    - stat-0x00: tags out-of-date
    
      obj:
      ```java
      List<Tag> tags;
      ```

## Item

One item can refer multiple tags.

### model

```java
  long id; // DBMS auto-generated
  String title;
  String url;
  List<Tag> tags;
```

### end point

- **`[POST] /item`**

  carrying:
  ```java
  String title;
  String url;
  List<Tag> tags;
  ```
  添加新的item
  
    - stat-0x00: item saved
    - stat-0x01: item already exists

- **`[PUT] /item`**

  carrying:  
  ```java
  String title;
  String url;
  List<Tag> tags;
  ```
  更新现有的item
  
    - stat-0x00: item updated
    - stat-0x01: item not found
    
- **`[GET] /item?url=`**

  通过url获取特定item
  
    - stat-0x00: item found
    
      obj:
      ```java
      Item item;
      ```
    - stat-0x01: item not found
    
- **`[DELETE] /item`**

  carrying:
  ```java
  String title;
  String url;
  List<Tag> tags;
  ```
  更新现有的item
  
    - stat-0x00: item deleted
    - stat-0x01: item not found
    
- **`[GET] /item/list?page(optional, default "0")=&tags(opptional, default "")=`**

  以分页的方式获取item列表，每页20项
  
    - stat-0x00: items listed
    
      obj:
      ```java
      class ItemPageObj {
        boolean isFirst;
        boolean isLast;
        int currentPage;
        List<Item> items;
      }
      ```
    - stat-0x01: nothing to list
    
