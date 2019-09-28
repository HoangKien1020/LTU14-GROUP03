# LTU14-GROUP03
BTL: Hệ thống ATM kết nối trao đổi thông qua RMI

Hướng dẫn cấu hình và cài đặt

`git clone https://github.com/HoangKien1020/LTU14-GROUP03.git`

`git checkout origin/khanhhh-ltu14`

Sử dụng InteliJ IDE. Download: https://www.jetbrains.com/idea/download/#section=windows

Stack: `Java RMI, Java SE, Java Swing, MySQL, JWT, Bcrypt`

Gradle dependencies: <br/>
`implementation 'com.auth0:java-jwt:3.8.3'`<br/>
`compile group: 'mysql', name: 'mysql-connector-java', version: '8.0.17'`<br/>
`compile group: 'org.mindrot', name: 'jbcrypt', version: '0.3m'`<br/>

MySQL card Table:
![alt text](https://i.imgur.com/LOcPL1O.png)

Cách chạy app:

`Run file RMIServer sau đó Run file RMIClient`

![alt text](https://i.imgur.com/gsgXo0a.png)

`Hiện mới chỉ có chức năng login với check số dư, còn update thêm 😴... `

Về bảo mật: <br/>

`Tại sao sử dụng Bcrypt để mã hóa mật khẩu?` <br/>
Xem video này: https://www.youtube.com/watch?v=ZwKrMKzd5J8 <br/>

`Về xác thực người dùng:`<br/>
Thư viện JWT cung cấp 2 cách xác thực sử dụng khóa đối xứng và bất đối xứng. Vì độ phức tạp của khóa bất đối xứng nên trong project này chỉ sử dụng khóa đối xứng.<br/>
Tìm hiểu thêm về JWT https://github.com/auth0/java-jwt

`Thiết lập SSL?` <br/>
Hiện chưa project này chưa cài đặt được SSL với Java RMI. <br/>
Thảm khảo thêm cách cài đặt: https://stackoverflow.com/questions/21586660/simple-rmi-server-with-ssl
