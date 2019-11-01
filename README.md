# LTU14-GROUP03
BTL: Hệ thống ATM kết nối trao đổi thông qua RMI
Hướng dẫn cách chạy chương trình
# Chạy phía server trước
docker run -ti --rm -e DISPLAY=$DISPLAY -v /tmp/.X11-unix:/tmp/.X11-unix ltu14_group03:atmserver /bin/bash
./auto.sh
Chạy trên netbeans
# Chạy client 1
docker run -ti --rm -e DISPLAY=$DISPLAY -v /tmp/.X11-unix:/tmp/.X11-unix ltu14_group03:atm1
Chạy trên netbeans
# Chạy client 2
docker run -ti --rm -e DISPLAY=$DISPLAY -v /tmp/.X11-unix:/tmp/.X11-unix ltu14_group03:atm2
Chạy trên netbeans
