# checkin_test
拔剑班如何评价  
盘点一下11.4干了哈:  
实际上不只今天，昨天和今天都在被那个编辑（更新）员工给卡住，主要是上传文件and那个日期格式，
Java8不支持LocalDateTime和LocalDate，还以为是字段写错的问题来着， 去搜了挺久才改完
这个bug。至于那个上传文件，原本是想按照两个请求分开控制更新员工和上传图片，但是这样感觉
有点麻烦，再次chat了之后遍只用update了。后来在postman确实轻而易举地成功发送请求并上传
图片，但在前端那儿仍然得花不少功夫才解决，以至于我现在还不太懂...关于后端的那些地址或路径
配置项，现在仍然没去真正地配置，还是得找个机会看看改一下才行。先至此吧，编辑板块完成后，新增
板块相信很快就完成。