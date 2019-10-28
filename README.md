# LTU14-GROUP03
BTL: Hệ thống ATM kết nối trao đổi thông qua RMI

Hướng dẫn cấu hình và cài đặt

`git clone https://github.com/HoangKien1020/LTU14-GROUP03.git`

Sử dụng Netbeans IDE

Stack: `Java RMI, Java SE, Java Swing, MySQL, Bcrypt`

Gradle dependencies: <br/>
`compile group: 'mysql', name: 'mysql-connector-java', version: '8.0.17'`<br/>
`compile group: 'org.mindrot', name: 'jbcrypt', version: '0.3m'`<br/>

MySQL card Table:

![alt text](https://i.imgur.com/pyhRS3Q.png)

![alt text](https://i.imgur.com/f3bB84f.png)

![alt text](https://i.imgur.com/BgFq7Nj.png)
Giao diện Server:

![alt text](https://i.imgur.com/1gtcRmk.png)
![alt text](https://i.imgur.com/piAjIFq.png)
![alt text](https://i.imgur.com/I8gr4jI.png)
![alt text](https://i.imgur.com/I8gr4jI.png)

Về bảo mật: <br/>

`Tại sao sử dụng Bcrypt để mã hóa mật khẩu?` <br/>
Xem video này: https://www.youtube.com/watch?v=ZwKrMKzd5J8 <br/>

`Về xác thực người dùng:`<br/>
Thư viện JWT cung cấp 2 cách xác thực sử dụng khóa đối xứng và bất đối xứng. Vì độ phức tạp của khóa bất đối xứng nên trong project này chỉ sử dụng khóa đối xứng.<br/>
Tìm hiểu thêm về JWT https://github.com/auth0/java-jwt

