import socket
import sys
server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
server.bind(("127.0.0.1",1234))
server.listen(2)

client,(c_ip,c_port) = server.accept()
data = client.recv(1024)
while data != None:
    print(data)
    try:
        msg = input()
        msg = msg.encode('utf-8')
    except KeyboardInterrupt:
        sys.exit(0)

    client.send(msg)
    data = client.recv(1024)
print(data)
client.close()
server.close()