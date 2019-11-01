# LTU14-GROUP03
BTL: Hệ thống ATM kết nối trao đổi thông qua RMI
# Đặc điểm của chương trình
- Chương trình phân tán
- Sử dụng ngôn ngữ lập trình Java										
- Sử dụng JDBC kết nối cơ sở dữ liệu MySQL										
- Phương pháp mã hóa mật khẩu(PIN) là Bcrypt										
- Sử dụng docker
- Sử dụng RMI
- Truyền thông bảo mật bằng SSL
- Xác thực người dùng bằng JWT
- Nếu nhập sai quá 3 lần mã PIN tài khoản sẽ bị khóa
- Tại một thời điểm chỉ đăng nhập được 1 lần
- Thời gian hiệu lực tính từ khi đăng nhập thành công là 10 phút, sau 10 phút chương trình sẽ tự tắt
- Tại cùng thời điểm đăng nhập sinh JWToken là khác nhau(Tính đến phần tích tắc)
- Kết thúc phiên là kết thúc token, chống lại tấn công phát lại
# *Chức năng phía client*
- Đăng nhập
- Vấn tin tài khoản
- Chuyển khoản
- Rút tiền
- Đổi mật khẩu
- Đăng xuất
# *Chức năng phía server*
- Giám sát giao dịch
- Tạo tài khoản
- Đổi mật khẩu
- Nạp tiền
- Xem danh sách tài khoản

# Hướng dẫn cách chạy chương trình
Mở XHOST

*xhost +*
# Chạy phía server trước

*docker pull hoangkien1020/ltu14_group03:atmserver*

*docker run -ti --rm -e DISPLAY=$DISPLAY -v /tmp/.X11-unix:/tmp/.X11-unix ltu14_group03:atmserver /bin/bash*

*./auto.sh*

Chạy trên netbeans
# Chạy client 1
*docker push hoangkien1025/ltu14_group03:atm1*

*docker run -ti --rm -e DISPLAY=$DISPLAY -v /tmp/.X11-unix:/tmp/.X11-unix ltu14_group03:atm1*

Chạy trên netbeans
# Chạy client 2
Clone atm1:

*docker commit "container ID atm1" ltu14_group03:atm2*

*docker run -ti --rm -e DISPLAY=$DISPLAY -v /tmp/.X11-unix:/tmp/.X11-unix ltu14_group03:atm2*

Chạy trên netbeans

# Kết quả sau khi chạy chương trình
<a href="https://imgur.com/lyA7obN"><img src="https://i.imgur.com/lyA7obN.png" title="source: imgur.com" /></a>
